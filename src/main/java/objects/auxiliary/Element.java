package objects.auxiliary;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2018-03-21.
 */
public class Element<A> {
	private A element;
	private Element<A> nextElement;

	Element(A element) {
		this.element = element;
	}

	A getElement() {
		return element;
	}

	Element<A> getNextElement() {
		return nextElement;
	}

	void setNextElement(Element<A> nextElement) {
		this.nextElement = nextElement;
	}
}