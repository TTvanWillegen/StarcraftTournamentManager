package objects;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import objects.auxiliary.CircularLinkedList;
import objects.auxiliary.PeekableIterator;
import objects.match.Match;
import objects.team.Team;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A Round object contains one or multiple {@link Poule}s
 *
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public class Round {
	@Nullable
	private Round nextRound = null;
	@NonNull
	private CircularLinkedList<Poule> pouleList;
	@NonNull
	private PeekableIterator<Poule> pouleIterator;

	public Round() {
		pouleList = new CircularLinkedList<>();
		pouleIterator = pouleList.getIterator();
	}

	public Round(List<Poule> pouleList) {
		this();
		this.pouleList.addAll(pouleList);
	}

	/**
	 * Returns the next {@link Poule} in the sequence.
	 */
	public Poule getNextPoule() {
		return pouleIterator.next();
	}

	/**
	 * Checks if there is a next {@link Poule} in the sequence.
	 */
	public boolean hasNextPoule() {
		return pouleIterator.hasNext();
	}

	/**
	 * Returns the next {@link Match} in the sequence.
	 */
	public Match getNextMatch() throws IndexOutOfBoundsException {
		return pouleIterator.next().getNextMatch();
	}

	/**
	 * Peeks the next {@link Match} in the sequence.
	 */
	public Match peekNextMatch() throws IndexOutOfBoundsException {
		return pouleIterator.peek().peekNextMatch();
	}

	/**
	 * Checks if there is a next {@link Match} in the sequence.
	 */
	public boolean hasNextMatch() {
		return pouleIterator.hasNext() && pouleIterator.peek().hasNextMatch();
	}

	public Collection<Team> getTeams() {
		Poule startingPoule = pouleIterator.next();
		HashSet<Team> teamList = new HashSet<>(startingPoule.getTeamList());

		while (!pouleIterator.peek().equals(startingPoule)) {
			Poule poule = pouleIterator.next();
			teamList.addAll(poule.getTeamList());
		}
		return teamList;
	}

	public Collection<Team> getTopTeams(int topN) {
		//TODO: make sure that the round has a way to get the top X
	}

	/**
	 * Removes a {@link objects.Poule} from the list and returns *true* if
	 * it is removed.
	 *
	 * @param pouleToRemove
	 * 	The {@link objects.Poule} to remove from the {@link objects.Poule}
	 * @return (boolean) *True* if removed, *False* if not.
	 */
	public boolean removePoule(Poule pouleToRemove) {
		return pouleList.remove(pouleToRemove);
	}

	public void addPoules(List<Poule> pouleToAdd) {
		pouleList.addAll(pouleToAdd);
	}

	public void addPoule(Poule pouleToAdd) {
		pouleList.add(pouleToAdd);
	}

	@Nullable
	public Round getNextRound() {
		return this.nextRound;
	}

	public void setNextRound(Round round) {
		this.nextRound = round;
	}
}
