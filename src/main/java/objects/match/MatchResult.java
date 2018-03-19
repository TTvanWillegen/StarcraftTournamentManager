package objects.match;

import java.util.List;
import objects.team.TeamScore;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Object contains a {@link List} of {@link TeamScore}s, also contains methods to check winner of
 * match.
 * @author Toby T. van Willegen
 * @version 1.1, 2017-09-14.
 */
public class MatchResult {
    @NonNull
    private List<TeamScore> teamScores;

    public MatchResult(List<TeamScore> teamScores) {
        this.teamScores = teamScores;
    }

    /**
     * Adds the other {@link MatchResult} to this {@link MatchResult}.
     *
     * @param matchResult {@link MatchResult} The other MatchResult to add to this results.
     * @return The combined {@link MatchResult}
     */
    public MatchResult addMatchResult(MatchResult matchResult) {
        this.teamScores.addAll(matchResult.getTeamScores());
        return this;
    }

    /**
     * Returns the highest {@link TeamScore} from the {@link MatchResult}.
     * Note: If multiple {@link TeamScore}s have the same score, will return
     * either of them, chosen at random.
     *
     * @return {@link TeamScore} The highest {@link TeamScore}. If multiple, chose one randomly.
     */
    @NonNull
    public TeamScore getHighestScore() {
        final TeamScore[] highestScore = {new TeamScore(null,
                                                        Double.MIN_VALUE)};
        teamScores.forEach(teamScore -> {
            if (teamScore.hasHigherScoreThan(highestScore[0])) {
                highestScore[0] = teamScore;
            }
        });
        return highestScore[0];
    }

    /**
     * Adds a {@link objects.team.TeamScore} to the current MatchResult.
     *
     * @param teamScore {@link TeamScore} The {@link TeamScore} to add to this {@link
     *                  MatchResult}
     * @return {@link MatchResult} This {@link MatchResult} with the provided
     * {@link TeamScore} added.
     */
    public MatchResult addTeamScore(TeamScore teamScore) {
        this.teamScores.add(teamScore);
        return this;
    }


    private List<TeamScore> getTeamScores() {
        return teamScores;
    }
}
