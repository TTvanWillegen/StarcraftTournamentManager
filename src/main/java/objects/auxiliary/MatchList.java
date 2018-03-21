package objects.auxiliary;

import java.util.ArrayList;
import objects.Order;
import objects.match.Match;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2018-03-21.
 */
public class MatchList extends ArrayList<Match> {
	private Order order;

	@Override
	public PeekableIterator<Match> iterator() {
		return new PeekableIterator<Match>() {
			private int currentIndex = 0;

			@Override
			public boolean hasNext() {
				return currentIndex < size() - 1;
			}

			@Override
			public Match next() {
				Match next = get(currentIndex);
				currentIndex++;
				return next;
			}

			public Match peek() {
				return get(currentIndex + 1);
			}
		};
	}
}
