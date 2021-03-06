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



/**
 * A Pool object contains multiple {@link Match}es aswell as an {@link Order} in which to traverse
 * the {@link Match}es.
 *
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public class Pool {
	private String poolName;

    private Collection<Team> teamList;

    private MatchList matchList;

    private PeekableIterator<Match> matchIterator;

    /**
     * Create a Pool object with the specified MatchOrder.
     *
     * @param matchOrder {@link Order} The order to traverse the matches through.
     */
    public Pool(String poolName, Order matchOrder) {
    	this.poolName = poolName;
        this.matchList = new MatchList(matchOrder);
        this.matchIterator = matchList.iterator();
        this.teamList = new ArrayList<>();
    }

    public Pool(String poolName, Order matchOrder,  List<Team> teams) {
        this(poolName, matchOrder);
        this.teamList = teams;
    }

    public boolean addTeam( Team team) {
        return teamList.contains(team) || this.teamList.add(team);
    }

    public Collection<Team> getTeamList() {
        return this.teamList;
    }

    /**
     * Returns the next {@link Match} in the sequence.
     *
     * @return Next {@link Match} in line as defined by the {@link objects.Order}, will always be a
     *     {@link Match} with {@link MatchState#IN_LINE}
     */
    public Match getNextMatch() {
        return matchIterator.next();
    }

    /**
     * Peeks the next {@link Match} in the sequence.
     *
     * @return Next {@link Match} in line as defined by the {@link objects.Order}, will always be a
     *     {@link Match} with {@link MatchState#IN_LINE}
     */
    public Match peekNextMatch() {
        return matchIterator.peek();
    }

    /**
     * Checks if there is a next {@link Match} in the sequence.
     *
     * @return *True* iff there is a next {@link Match} in line, *False* otherwise
     */
    public boolean hasNextMatch() {
        return matchIterator.hasNext();
    }

    /**
     * Removes a {@link Match} from the list and returns *True* if it is removed.
     *
     * @param matchToRemove The {@link Match} to remove from the {@link Pool}
     * @return (boolean) *True* iff removed, *False* if not.
     */
    public boolean removeMatch( Match matchToRemove) {
        return matchList.remove(matchToRemove);
    }

    public void addMatches( List< Match> matchesToAdd) {
        matchList.addAll(matchesToAdd);
    }

    @Override
    public boolean equals( Object o) {
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
