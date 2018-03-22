package objects;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import objects.auxiliary.MatchList;
import objects.auxiliary.PeekableIterator;
import objects.match.Match;
import objects.match.MatchState;
import objects.team.Team;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * A Pool object contains multiple {@link Match}es aswell as an
 * {@link Order} in which to
 * traverse the {@link Match}es.
 *
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public class Pool {
	private Collection<Team> teamList;
	private MatchList matchList;
	private PeekableIterator<Match> matchIterator;

	public Pool(@NonNull Order matchOrder) {
		this.matchList = new MatchList(matchOrder);
		this.matchIterator = matchList.iterator();
		this.teamList = new ArrayList<>();
	}

	public Pool(@NonNull Order matchOrder, @NonNull List<Team> teams) {
		this(matchOrder);
		this.teamList = teams;
	}

	public boolean addTeam(Team team) {
		return teamList.contains(team) || this.teamList.add(team);
	}

	public Collection<Team> getTeamList() {
		return this.teamList;
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
	 * 	The {@link Match} to remove from the {@link Pool}
	 * @return (boolean) *True* iff removed, *False* if not.
	 */
	public boolean removeMatch(Match matchToRemove) {
		return matchList.remove(matchToRemove);
	}

	public void addMatches(List<Match> matchesToAdd) {
		matchList.addAll(matchesToAdd);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Pool pool = (Pool) o;
		return Objects.equals(teamList, pool.teamList) &&
			Objects.equals(matchList, pool.matchList) &&
			Objects.equals(matchIterator, pool.matchIterator);
	}

	@Override
	public int hashCode() {

		return Objects.hash(teamList, matchList, matchIterator);
	}
}
