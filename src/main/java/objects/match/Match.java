package objects.match;

import java.util.List;
import java.util.Objects;
import objects.Map;
import objects.team.Team;



/**
 * Match object, contains the teams playing a match, the match state, the map
 * it is played on and
 * the result once played.
 *
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public class Match {

	private List<Team> teams;

	private MatchState matchState;

	private MatchResult matchResult = null;

	private Map map;

	/**
	 * Creates a {@link Match} object, containing a {@link List} of teams
	 * competing and a {@link
	 * Map} object on which the {@link Match} is played.
	 *
	 * @param teams
	 * 	{@link List} The {@link Team}s that are playing
	 * @param map
	 * 	{@link Map} The {@link Map} on which the {@link Match} is played.
	 */
	public Match( List<Team> teams,  Map map) {
		this.teams = teams;
		this.matchState = MatchState.IN_LINE;
		this.map = map;
	}

	/**
	 * Creates a {@link Match} object, containing a {@link List} of teams
	 * competing and a {@link
	 * Map} object on which the {@link Match} is played. Provided
	 * {@link MatchState} will be added
	 * to the {@link Match} aswell. Sets the {@link MatchResult} to a new
	 * empty result.
	 *
	 * @param teams
	 * 	{@link List} The {@link Team}s that are playing
	 * @param map
	 * 	{@link Map} The {@link Map} on which the {@link Match} is played.
	 * @param matchState
	 * 	{@link MatchState} The {@link MatchState} which the {@link Match}
	 * 	should
	 * 	have.
	 */
	public Match( List<Team> teams,  Map map,
	              MatchState matchState) {
		this(teams, map);
		this.matchState = matchState;
	}

	/**
	 * Creates a {@link Match} object, containing a {@link List} of teams
	 * competing and a {@link
	 * Map} object on which the {@link Match} is played. Uses provided
	 * {@link MatchResult} for the
	 * {@link Match}. Sets the {@link MatchState} to *IN_LINE*.
	 *
	 * @param teams
	 * 	{@link List} The {@link Team}s that are playing
	 * @param map
	 * 	{@link Map} The {@link Map} on which the {@link Match} is played.
	 */
	public Match( List<Team> teams,  Map map,
	              MatchResult matchResult) {
		this(teams, map);
		this.matchResult = matchResult;
	}

	/**
	 * Creates a {@link Match} object, containing a {@link List} of teams
	 * competing and a {@link
	 * Map} object on which the {@link Match} is played. Uses provided
	 * {@link MatchResult} for the
	 * {@link Match}, aswell as the provided {@link MatchState}
	 *
	 * @param teams
	 * 	{@link List} The {@link Team}s that are playing
	 * @param map
	 * 	{@link Map} The {@link Map} on which the {@link Match} is played.
	 */
	public Match( List<Team> teams,  Map map,
	              MatchState matchState,
	              MatchResult matchResult) {
		this(teams, map);
		this.matchState = matchState;
		this.matchResult = matchResult;
	}

	/**
	 * Returns *True* iff the {@link Match} is still in line to be played.
	 *
	 * @return {@link boolean} *True* iff the {@link Match} is still in line,
	 * *False* otherwise.
	 */
	public boolean hasToBePlayed() {
		return matchState == MatchState.IN_LINE;
	}

	public List<Team> getTeams() {
		return this.teams;
	}

	public MatchState getMatchState() {
		return matchState;
	}

	public void setMatchState(MatchState newMatchState) {
		this.matchState = newMatchState;
	}


	public MatchResult getMatchResult() {
		return matchResult;
	}

	public void setMatchResult( MatchResult matchResult) {
		this.matchResult = matchResult;
	}

	public Map getMap() {
		return map;
	}

	@SuppressWarnings("nullness")
	@Override
	public boolean equals( Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		 Match match = (Match) o;
		return Objects.equals(teams, match.teams) &&
			matchState == match.matchState &&
			Objects.equals(matchResult, match.matchResult) &&
			Objects.equals(map, match.map);
	}

	@SuppressWarnings("nullness")
	@Override
	public int hashCode() {
		return Objects.hash(teams, matchState, matchResult, map);
	}

	@Override
	public String toString() {
		return "Match{" + "teams=" + teams + ", matchState=" + matchState +
			", matchResult=" + matchResult + ", map=" + map + '}';
	}
}
