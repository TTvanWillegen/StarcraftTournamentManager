package objects.auxiliary;

import java.util.Objects;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2018-03-21.
 */
public class Element<A> {
	@NonNull
	private A element;
	@Nullable
	private Element<A> nextElement;

	Element(@NonNull A element) {
		this.element = element;
	}

	Element(@NonNull A element, @NonNull Element<A> nextElement) {
		this.element = element;
		this.nextElement = nextElement;
	}

	public A getElement() {
		return element;
	}

	@NonNull public Element<A> getNextElement() {
		if(nextElement == null){
			return this;
		}else {
			return nextElement;
		}
	}

	void setNextElement(@NonNull Element<A> nextElement) {
		this.nextElement = nextElement;
	}

	@Override
	public boolean equals(@Nullable Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Element<?> element1 = (Element<?>) o;
		return nextElement == null && element1.nextElement == null ||
			nextElement != null && element1.nextElement != null &&
				element.equals(element1.element) &&
				nextElement.element.equals(element1.nextElement.element);
	}

	@Override
	public int hashCode() {
		return Objects.hash(element, nextElement);
	}

	@Override
	public String toString() {
		return "Element{" + "element=" + element +
			(nextElement == null ? ", nextElement = null" :
				", nextElement=" + nextElement.element + '}');
	}
}