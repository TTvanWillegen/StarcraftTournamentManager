package objects.match;

import com.sun.istack.internal.NotNull;
import objects.Map;
import objects.team.Team;

import java.util.List;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public class Match {
	@NotNull
	private List<Team> teams;
	@NotNull
	private MatchState matchState;
	@NotNull
	private MatchResult matchResult;
	@NotNull
	private Map map;

	public Match(@NotNull List<Team> teams, @NotNull Map map) {
		this.teams = teams;
		this.matchState = MatchState.IN_LINE;
		this.matchResult = MatchResultFactory.getInstance().newResult(teams);
		this.map = map;
	}

	public Match(@NotNull List<Team> teams, @NotNull Map map,
				 @NotNull MatchState matchState) {
		this(teams, map);
		this.matchState = matchState;
	}

	public Match(@NotNull List<Team> teams, @NotNull Map map,
				 @NotNull MatchResult matchResult) {
		this(teams, map);
		this.matchResult = matchResult;
	}

	public Match(@NotNull List<Team> teams, @NotNull Map map,
				 @NotNull MatchState matchState,
				 @NotNull MatchResult matchResult) {
		this(teams, map);
		this.matchState = matchState;
		this.matchResult = matchResult;
	}

	/**
	 * Returns *True* iff the {@link Match} is still in line to be played
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

	public void setMatchResult(MatchResult matchResult) {
		this.matchResult = matchResult;
	}

	public Map getMap() {
		return map;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		Match match = (Match) o;

		if (!teams.equals(match.teams)) {
			return false;
		}
		if (matchState != match.matchState) {
			return false;
		}
		if (!matchResult.equals(match.matchResult)) {
			return false;
		}
		return map.equals(match.map);
	}

	@Override
	public int hashCode() {
		int result = teams.hashCode();
		result = 31 * result + matchState.hashCode();
		result = 31 * result + matchResult.hashCode();
		result = 31 * result + map.hashCode();
		return result;
	}

	@Override
	public String toString() {
		return "Match{" + "teams=" + teams + ", matchState=" + matchState +
			", matchResult=" + matchResult + ", map=" + map + '}';
	}
}
