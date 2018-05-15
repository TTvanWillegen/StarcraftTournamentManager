package objects.auxiliary;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2018-05-15.
 */
public class PeekableCircularIterator<A> implements PeekableIterator<A> {
	private final Node<A> head;
	private Node<A> currentElement;
	private boolean looping = false;

	PeekableCircularIterator(Node<A> head) {
		this.currentElement = null;
		this.head = head;
	}


	PeekableCircularIterator(Node<A> head, boolean looping) {
		this.currentElement = null;
		this.head = head;
		this.looping = looping;
	}

	private boolean isLooping() {
		return looping;
	}

	public void setLooping(boolean looping) {
		this.looping = looping;
	}

	@Override
	public boolean hasNext() {
		return (head != null && (currentElement == null || (looping ||
			!currentElement.getNext().getData().equals(head.getData()))));
	}

	@Override
	public A next() throws IndexOutOfBoundsException {
		if (hasNext()) {
			if (currentElement == null) {
				currentElement = head;
			} else {
				currentElement = currentElement.getNext();
			}
			return currentElement.getData();
		} else {
			throw new IndexOutOfBoundsException();
		}
	}

	public A peek() throws IndexOutOfBoundsException {
		if (!hasNext()) {
			throw new IndexOutOfBoundsException();
		}
		return currentElement.getNext().getData();
	}
}