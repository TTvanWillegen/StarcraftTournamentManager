package objects.match;

import java.util.ArrayList;
import java.util.List;
import objects.team.Team;
import objects.team.TeamScore;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * Generates a {@link List} of {@link MatchResult}s, from provided {@link List} of {@link Team}s.
 * Applies Singleton pattern.
 *
 * @author Toby T. van Willegen
 * @version 1.1, 2017-09-14.
 */
public class MatchResultFactory {
    @Nullable
    private static MatchResultFactory matchResultFactory = null;

    private MatchResultFactory() {
    }

    /**
     * Returns an instance of the {@link MatchResultFactory}, on first call, creates an instance.
     *
     * @return {@link MatchResultFactory} A single instance of the MatchResultFactory.
     */
    public static MatchResultFactory getInstance() {
        synchronized (MatchResultFactory.class) {
            if (matchResultFactory == null) {
                matchResultFactory = new MatchResultFactory();
            }
        }
        return matchResultFactory;
    }

    /**
     * Returns a {@link objects.match.MatchResult} containing a score of 0 for
     * the {@link objects.team.Team} provided.
     *
     * @param team {@link objects.team.Team} The team to generate a new {@link TeamScore}
     *             for.
     * @return {@link MatchResult} A {@link MatchResult} containing a {@link
     * List} of {@link TeamScore}s of the {@link Team} provided.
     */
    public MatchResult newResult(Team team) {
        List<Team> teams = new ArrayList<>();
        teams.add(team);
        return newResult(teams);
    }

    /**
     * Returns a {@link objects.match.MatchResult} containing a score of 0 for
     * the {@link List} of {@link objects.team.Team}s provided.
     *
     * @param teams {@link List} of {@link objects.team.Team}s The teams to generate a new
     *              {@link TeamScore} for.
     * @return {@link MatchResult} A {@link MatchResult} containing a {@link
     * List} of {@link TeamScore}s of the {@link Team}s provided.
     */
    public MatchResult newResult(List<Team> teams) {
        MatchResult matchResult = new MatchResult(new ArrayList<>());
        teams.forEach(team -> {
            TeamScore matchScore = new TeamScore(team, 0);
            matchResult.addTeamScore(matchScore);
        });
        return matchResult;
    }
}
