package objects;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import objects.auxiliary.CircularLinkedList;
import objects.auxiliary.PeekableIterator;
import objects.match.Match;
import objects.team.Team;


/**
 * A Round object contains one or multiple {@link Pool}s.
 *
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public class Round {

	private Round nextRound = null;
	private String name;

	private CircularLinkedList<Pool> pouleList;

	private PeekableIterator<Pool> pouleIterator;

	public Round(String name) {
		this.name = name;
		pouleList = new CircularLinkedList<>();
		pouleIterator = pouleList.iterator();
	}

	public Round(String name, List<Pool> poolList) {
		this(name);
		this.pouleList.addAll(poolList);
	}

	/**
	 * Returns the next {@link Pool} in the sequence.
	 */
	public Pool getNextPool() {
		return pouleIterator.next();
	}

	/**
	 * Checks if there is a next {@link Pool} in the sequence.
	 */
	public boolean hasNextPool() {
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

	/**
	 * Returns a {@link java.util.Collection} of all the {@link Team}s that
	 * occur in all the {@link
	 * Pool}s in this Round.
	 *
	 * @return A collection containing unique instances of the {@link Team}s
	 * of all the {@link
	 * Pool}s
	 */
	public Collection<Team> getTeams() {
		Pool startingPool = pouleIterator.next();
		HashSet<Team> teamList = new HashSet<>(startingPool.getTeamList());

		while (!pouleIterator.peek().equals(startingPool)) {
			Pool pool = pouleIterator.next();
			teamList.addAll(pool.getTeamList());
		}
		return teamList;
	}

	public Collection<Team> getTopTeams(int topN) {
		//TODO: make sure that the round has a way to get the top X
		return new HashSet<>();
	}

	/**
	 * Removes a {@link Pool} from the list and returns *true* if it is
	 * removed.
	 *
	 * @param poolToRemove
	 * 	The {@link Pool} to remove from the {@link Pool}
	 * @return (boolean) *True* if removed, *False* if not.
	 */
	public boolean removePool(Pool poolToRemove) {
		return pouleList.remove(poolToRemove);
	}

	public void addPools(List<Pool> poolToAdd) {
		pouleList.addAll(poolToAdd);

		pouleIterator = pouleList.iterator();
	}

	public void addPool(Pool poolToAdd) {
		pouleList.add(poolToAdd);

		pouleIterator = pouleList.iterator();
	}


	public Round getNextRound() {
		return this.nextRound;
	}

	public void setNextRound(Round round) {
		this.nextRound = round;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Round round = (Round) o;
		return Objects.equals(name, round.name);
	}

	@Override
	public int hashCode() {

		return Objects.hash(nextRound, name, pouleList, pouleIterator);
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Round{ name='" + name + "\', nextRound=" + nextRound + '}';
	}
}
