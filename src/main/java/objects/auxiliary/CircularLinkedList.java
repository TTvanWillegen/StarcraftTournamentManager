package objects.auxiliary;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class CircularLinkedList<T> implements List<T> {

	private int size = 0;
	private Node<T> head = null;
	private Node<T> tail = null;

	private int getLowestValidIndex(int index) {
		while (index < 0) {
			index += size;
		}
		if (size == 0) {
			throw new IndexOutOfBoundsException();
		}
		index = index % size;
		return index;
	}

	@Override
	public void add(int index, T data) {
		Node<T> newNode = new Node<>(data);
		if (index == 0) {
			add(data);
		} else if (index > 0) {
			Node<T> node = nodeAt(index);
			newNode.setNext(node);

			Node<T> lastNode = nodeAt(index - 1);
			lastNode.setNext(node);
		}
	}

	@Override
	public T remove(int index) {
		T removedData;
		if (index == 0) {
			removedData = this.head.getData();
			this.head = this.head.getNext();
			this.tail.setNext(this.head);
			this.size--;
			return removedData;
		} else {
			Node<T> lastNode = nodeAt(index - 1);
			removedData = nodeAt(index).getData();
			Node<T> nextNode = nodeAt(index + 1);
			lastNode.setNext(nextNode);
		}
		return removedData;
	}

	@Override
	public int indexOf(Object o) {
		Node temp = head;
		int index = 0;
		if (size > 0) {
			do {
				if (Objects.equals(o, temp.getData())) {
					return index;
				}
				temp = temp.getNext();
				index++;
			} while (temp != head);
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		Node temp = head;
		int index = 0;
		int lastIndex = -1;
		if (size > 0) {
			do {
				if (Objects.equals(o, temp.getData())) {
					lastIndex = index;
				}
				temp = temp.getNext();
				index++;
			} while (temp != head);
		}
		return lastIndex;
	}

	@Override
	public ListIterator<T> listIterator() {
		throw new NotImplementedException();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		throw new NotImplementedException();
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		fromIndex = getLowestValidIndex(fromIndex);
		toIndex = getLowestValidIndex(toIndex);
		int remaining = getLowestValidIndex(toIndex - fromIndex);

		Node<T> node = nodeAt(fromIndex);
		List<T> list = new CircularLinkedList<>();
		do {
			list.add(node.getData());
			node = node.getNext();
			remaining--;
		} while (remaining != 0);

		return list;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean contains(Object o) {
		return indexOf(o) != -1;
	}

	@Override
	public PeekableIterator<T> iterator() {
		throw new NotImplementedException();
	}

	@Override
	public Object[] toArray() {
		Object[] objects = new Object[size];
		int index = 0;
		Node<T> node = head;
		do {
			objects[index] = node.getData();
		} while (!node.getNext().equals(head));
		return objects;
	}

	@Override
	@SuppressWarnings("unchecked") //You cannot instantiate a Generic
	// directly, therefore you need the hacky a.getClass method. This then
	// needs to be cast which results in an unchecked warning.
	public <T1> T1[] toArray(T1[] a) {
		if (a.length < size) {
			a = (T1[]) Array.newInstance(a.getClass().getComponentType(),
			                             size);
		}
		int index = 0;
		Node<T> node = head;
		do {
			a[index] = (T1) node.getData();
		} while (!node.getNext().equals(head));
		return a;
	}

	public boolean add(T data) {
		Node<T> n = new Node<>(data);
		if (this.size == 0) {
			this.head = n;
			this.tail = n;
			n.setNext(this.head);
			this.size++;
			return true;
		} else {
			this.tail.setNext(n);
			this.tail = n;
			this.tail.setNext(this.head);
			this.size++;
			return true;
		}
	}

	@Override
	public boolean remove(Object o) {
		int index = indexOf(o);
		if (index >= 0) {
			return remove(index) != null;
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		for (Object o : c) {
			if ((indexOf(o) == -1)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean addAll(Collection<? extends T> c) {
		boolean hasChanged = false;
		for (T o : c) {
			hasChanged = hasChanged || add(o);
		}
		return hasChanged;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> c) {
		for (T o : c) {
			add(index, o);
		}
		return true;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		boolean hasChanged = false;
		for (Object o : c) {
			hasChanged = hasChanged || remove(o);
		}
		return hasChanged;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		this.removeIf(item -> !c.contains(item));
		return false;
	}

	@Override
	public void clear() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}

	@Override
	public T set(int index, T element) {
		Node<T> node = nodeAt(index);
		T previous = node.getData();
		node.setData(element);
		return previous;
	}

	@Override
	public T get(int index) {
		return nodeAt(index).getData();
	}

	private Node<T> nodeAt(int index) {
		index = getLowestValidIndex(index);
		Node<T> n = head;
		while (index - 1 != 0) {
			n = n.getNext();
			index--;
		}
		return n;
	}

	@Override
	public String toString() {
		String finalString = "";
		Node temp = head;
		if (size > 0) {
			do {
				finalString = "Node{" + temp.getData() + "}";
				temp = temp.getNext();
			} while (temp != head);
		}

		return finalString;
	}
}

class Node<A> {
	private A data;
	private Node<A> next;

	Node(A data) {
		this.data = data;
	}

	A getData() {
		return data;
	}

	void setData(A data) {
		this.data = data;
	}

	Node<A> getNext() {
		return next;
	}

	void setNext(Node<A> next) {
		this.next = next;
	}
}