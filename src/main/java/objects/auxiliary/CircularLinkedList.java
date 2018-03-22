package objects.auxiliary;

import java.util.Collection;
import java.util.Objects;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2018-03-21.
 */
public class CircularLinkedList<T> {
	private Element<T> head = null;

	public CircularLinkedList() {
	}

	public void add(T element) {
		if (head == null) {
			head = new Element<>(element);
			head.setNextElement(head);
		} else {
			Element<T> nextElement = head.getNextElement();
			Element<T> newElement = new Element<>(element);
			newElement.setNextElement(nextElement);
			head.setNextElement(newElement);
		}
	}

	public void addAll(Collection<T> elements) {
		for (T element : elements) {
			add(element);
		}
	}

	public T get(int index) throws IndexOutOfBoundsException {
		int i = index;
		Element<T> element = head;

		if (element == null || index < 0) {
			throw new IndexOutOfBoundsException();
		}

		while (i > 0) {
			element = element.getNextElement();
			i--;
		}
		return element.getElement();
	}

	public boolean remove(int index) throws IndexOutOfBoundsException {
		//TODO: add remove
		throw new NotImplementedException();
	}

	public boolean remove(T element) {
		//TODO: add remove
		throw new NotImplementedException();
	}

	public PeekableIterator<T> getIterator() {
		return new PeekableCircularIterator<>(head);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		CircularLinkedList<?> that = (CircularLinkedList<?>) o;
		return this.head == null && that.head == null ||
			(this.head != null && this.head.equals(that.head));
	}

	@Override
	public int hashCode() {

		return Objects.hash(head);
	}

	private class PeekableCircularIterator<A> implements PeekableIterator<A> {
		private Element<A> currentElement;

		PeekableCircularIterator(Element<A> head) {
			this.currentElement = head;
		}


		@Override
		public boolean hasNext() {
			return currentElement != null;

		}

		@Override
		public A next() throws IndexOutOfBoundsException {
			if (!hasNext()) {
				throw new IndexOutOfBoundsException();
			}
			currentElement = currentElement.getNextElement();
			return currentElement.getElement();
		}

		public A peek() throws IndexOutOfBoundsException {
			if (!hasNext()) {
				throw new IndexOutOfBoundsException();
			}
			return currentElement.getNextElement().getElement();
		}
	}

}
