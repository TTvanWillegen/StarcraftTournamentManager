package objects.auxiliary;

import java.util.Collection;
import java.util.Objects;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Implements a Circularly Linked List.
 *
 * @author Toby T. van Willegen
 * @version 1.1, 2018-03-21.
 */
public class CircularLinkedList<T> {
    @Nullable
    private Element<T> head = null;

    /** Adds an element to this CLL.
     *
     * @param element The element to add
     */
    public void add(@NonNull T element) {
        if (this.head != null) {
            Element<T> nextElement = this.head.getNextElement();
            Element<T> newElement = new Element<>(element);
            newElement.setNextElement(nextElement);

            this.head.setNextElement(newElement);
        } else {
            head = new Element<>(element);
            head.setNextElement(head);
        }
    }

    /** Adds all of the elements in provided collection to this Circularly Linked List.
     *
     * @param elements A {@link Collection} to add to this CLL
     */
    public void addAll(@NonNull Collection<@NonNull T> elements) {
        for (T element : elements) {
            add(element);
        }
    }

    /** Returns the element at provided index.
     *      Note: Is circular, so index > size will result in the same element as index % size
     * @param index The index to search for.
     * @return  (T) Element at index % size.
     * @throws IndexOutOfBoundsException If there are no elements in this list, or index < 0
     */
    public T get(@NonNull int index) throws IndexOutOfBoundsException {
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

    public boolean remove(@NonNull int index) throws
        IndexOutOfBoundsException {
        //TODO: add remove
        return false;
    }

    public boolean remove(@NonNull T element) {
        //TODO: add remove
        return false;
    }

    public PeekableIterator<T> getIterator() {
        return new PeekableCircularIterator<>(head);
    }

    @Override
    public boolean equals(@Nullable Object o) {
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
        @Nullable
        private Element<A> currentElement;

        PeekableCircularIterator(@Nullable Element<A> head) {
            this.currentElement = head;
        }


        @Override
        public boolean hasNext() {
            return currentElement != null;
        }

        @Override
        public A next() throws IndexOutOfBoundsException {
            if (currentElement != null) {
                currentElement = currentElement.getNextElement();
                return currentElement.getElement();
            } else {
                throw new IndexOutOfBoundsException();
            }
        }

        public A peek() throws IndexOutOfBoundsException {
            if (currentElement == null) {
                throw new IndexOutOfBoundsException();
            }
            return currentElement.getNextElement().getElement();
        }
    }

}
