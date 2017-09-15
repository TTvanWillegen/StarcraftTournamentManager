package objects.team;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2017-09-14.
 */
public class TeamScore {
	private Team team;
	private double score;

	public TeamScore(Team team, double score) {
		this.team = team;
		this.score = score;
	}

	public Team getTeam() {
		return team;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	/**
	 * Returns True iff the score in the provided
	 * {@link objects.team.TeamScore} is lower than this {@link TeamScore}
	 *
	 * @param teamScore
	 * 	{@link TeamScore} the other {@link TeamScore} to check against
	 * @return {@link boolean} *True* iff this {@link TeamScore} is higher than
	 * the provided {@link TeamScore}, *False* otherwise
	 */
	public boolean hasHigherScoreThan(TeamScore teamScore) {
		return this.score > teamScore.getScore();
	}

	/**
	 * Returns True iff the score in the provided
	 * {@link objects.team.TeamScore} is equal to this {@link TeamScore}
	 *
	 * @param teamScore
	 * 	{@link TeamScore} the other {@link TeamScore} to check against
	 * @return {@link boolean} *True* iff the score in this
	 * {@link TeamScore} is
	 * equal to the provided {@link TeamScore}, *False* otherwise
	 */
	public boolean hasSameScoreAs(TeamScore teamScore) {
		return this.score == teamScore.getScore();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		TeamScore teamScore1 = (TeamScore) o;

		if (Double.compare(teamScore1.score, score) != 0) {
			return false;
		}
		return
			team != null ? team.equals(teamScore1.team) :
				teamScore1.team == null;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		result = team != null ? team.hashCode() : 0;
		temp = Double.doubleToLongBits(score);
		result = 31 * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public String toString() {
		return "TeamScore{" + "team=" + team + ", score=" + score + '}';
	}
}
