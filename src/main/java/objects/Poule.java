package objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import objects.auxiliary.MatchList;
import objects.auxiliary.PeekableIterator;
import objects.match.Match;
import objects.match.MatchState;
import objects.team.Team;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * A Poule object contains multiple {@link Match}es aswell as an
 * {@link Order} in which to
 * traverse the {@link Match}es.
 *
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public class Poule {
	private List<Team> teamList = new ArrayList<>();
	private MatchList matchList = new MatchList();
	private PeekableIterator<Match> matchIterator = matchList.iterator();
	private Order matchOrder;

	public Poule(@NonNull Order matchOrder) {
		this.matchOrder = matchOrder;
	}

	public Poule(@NonNull Order matchOrder, @NonNull List<Team> teams) {
		this(matchOrder);
		this.teamList = teams;
	}

	/**
	 * Returns the next {@link Match} in the sequence.
	 *
	 * @return Next {@link Match} in line as defined by the
	 * {@link objects.Order}, will always be a
	 * {@link Match} with {@link MatchState#IN_LINE}
	 */
	public Match getNextMatch() {
		return matchIterator.next();
	}

	/**
	 * Peeks the next {@link Match} in the sequence.
	 *
	 * @return Next {@link Match} in line as defined by the
	 * {@link objects.Order}, will always be a
	 * {@link Match} with {@link MatchState#IN_LINE}
	 */
	public Match peekNextMatch() {
		return matchIterator.peek();
	}

	/**
	 * Checks if there is a next {@link Match} in the sequence.
	 *
	 * @return *True* iff there is a next {@link Match} in line, *False*
	 * otherwise
	 */
	public boolean hasNextMatch() {
		return matchIterator.hasNext();
	}

	/**
	 * Removes a {@link Match} from the list and returns *True* if it is
	 * removed.
	 *
	 * @param matchToRemove
	 * 	The {@link Match} to remove from the {@link objects.Poule}
	 * @return (boolean) *True* iff removed, *False* if not.
	 */
	public boolean removeMatch(Match matchToRemove) {
		return matchList.remove(matchToRemove);
	}

	public void addMatch(List<Match> matchesToAdd) {
		if (this.matchOrder == Order.RANDOM) {
			Collections.shuffle(matchesToAdd);
		}
		matchList.addAll(matchesToAdd);
	}
}
