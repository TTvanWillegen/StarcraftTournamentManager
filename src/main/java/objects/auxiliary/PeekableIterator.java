package objects.auxiliary;

import java.util.Iterator;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2018-03-21.
 */
public interface PeekableIterator<A> extends Iterator<A> {
	@Override
	boolean hasNext();

	@Override
	A next() throws IndexOutOfBoundsException;

	A peek() throws IndexOutOfBoundsException;
}