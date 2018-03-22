package objects.auxiliary;

import java.util.ArrayList;
import objects.Order;
import objects.match.Match;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2018-03-21.
 */
public class MatchList extends ArrayList<Match> {
	private Order order;

	public MatchList(@NonNull Order order) {
		this.order = order;
	}

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
				//TODO: Implement the order in this method.
				//TODO: Make sure a list can be generated from this.
				Match next = get(currentIndex);
				currentIndex++;
				return next;
			}

			public Match peek() {
				//TODO: Implement the order in this method.
				return get(currentIndex + 1);
			}
		};
	}
}
