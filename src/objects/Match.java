package objects;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public class Match {
	private Team teamA;
	private Team teamB;
	private MatchState matchState;
	private MatchWinner matchWinner;
	private Map map;

	public Match(Team teamA, Team teamB, Map map){
		this.teamA = teamA;
		this.teamB = teamB;
		this.matchState = MatchState.IN_LINE;
		this.matchWinner = MatchWinner.UNPLAYED;
		this.map = map;
	}
	public Match(Team teamA, Team teamB, Map map, MatchState matchState){
		this(teamA, teamB, map);
		this.matchState = matchState;
	}
	public Match(Team teamA, Team teamB, Map map, MatchWinner matchWinner){
		this(teamA, teamB, map);
		this.matchWinner = matchWinner;
	}
	public Match(Team teamA, Team teamB, Map map, MatchState matchState, MatchWinner matchWinner){
		this(teamA, teamB, map);
		this.matchState = matchState;
		this.matchWinner = matchWinner;
	}

	public boolean hasToBePlayed(){
		return matchState != null && matchState == MatchState.IN_LINE;
	}

	public void updateMatchState(MatchState newMatchState){
		this.matchState = newMatchState;
	}

	public Team getTeamA() {
		return teamA;
	}

	public Team getTeamB() {
		return teamB;
	}

	public MatchState getMatchState() {
		return matchState;
	}

	public MatchWinner getMatchWinner() {
		return matchWinner;
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

		if (teamA != null ? !teamA.equals(match.teamA) : match.teamA != null) {
			return false;
		}
		if (teamB != null ? !teamB.equals(match.teamB) : match.teamB != null) {
			return false;
		}
		if (matchState != match.matchState) {
			return false;
		}
		if (matchWinner != match.matchWinner) {
			return false;
		}
		return map != null ? map.equals(match.map) : match.map == null;
	}

	@Override
	public int hashCode() {
		int result = teamA != null ? teamA.hashCode() : 0;
		result = 31 * result + (teamB != null ? teamB.hashCode() : 0);
		result = 31 * result + (matchState != null ? matchState.hashCode() : 0);
		result = 31 * result + (matchWinner != null ? matchWinner.hashCode() : 0);
		result = 31 * result + (map != null ? map.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		return "Match{" +
			"teamA=" + teamA +
			", teamB=" + teamB +
			", matchState=" + matchState +
			", matchWinner=" + matchWinner +
			", map=" + map +
			'}';
	}
}
