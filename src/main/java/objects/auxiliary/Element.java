package objects.auxiliary;

import java.util.Objects;

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

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Element<?> element1 = (Element<?>) o;
		return element.equals(element1.element) &&
			nextElement.element.equals(element1.nextElement.element);
	}

	@Override
	public int hashCode() {

		return Objects.hash(element, nextElement);
	}

	@Override
	public String toString() {
		return "Element{" + "element=" + element + ", nextElement=" +
			nextElement.element + '}';
	}
}