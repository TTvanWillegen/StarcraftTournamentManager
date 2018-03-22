package objects.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

import objects.team.Team;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Object contains a {@link Map} of {@link Team}s with scores, also contains methods to check winner
 * of match.
 *
 * @author Toby T. van Willegen
 * @version 1.1, 2017-09-14.
 */
public class MatchResult {
    @NonNull
    private Map<Team, Double> teamScores;

    public MatchResult() {
        this.teamScores = new HashMap<>();
    }

    public MatchResult(@NonNull Map<Team, Double> teamScores) {
        this.teamScores = teamScores;
    }

    /**
     * Sets the score of a team.
     *
     * @param team  The {@link Team} to add the score for
     * @param score The score te add
     */
    public void setScore(@NonNull Team team, @NonNull double score) {
        this.teamScores.put(team, score);
    }

    /**
     * Retrieves the team that has the highest score in this match. Will throw a {@link
     * objects.match.NoTeamAddedException} if there are no teams added to this match.
     *
     * @return {@link Team} the team with the highest score.
     * @throws NoTeamAddedException Is thrown when there are no Teams added to this matchresult.
     */
    public Team getWinningTeam() throws NoTeamAddedException {
        Map.Entry<Team, Double> maxEntry = null;

        for (Map.Entry<Team, Double> entry : this.teamScores.entrySet()) {
            if (maxEntry == null ||
                entry.getValue().compareTo(maxEntry.getValue()) > 0) {
                maxEntry = entry;
            }
        }

        if (maxEntry != null) {
            return maxEntry.getKey();
        } else {
            throw new NoTeamAddedException();
        }
    }

    /**
     * Returns a list in which is sorted in such a way that the {@link Team} with the highest score
     * is at index 0, and lowest at end.
     *
     * @return {@link List} A sorted list with teams
     */
    public List<Team> getTeamOrder() {
        List<Team> orderedTeamList = new ArrayList<>();
        PriorityQueue<Map.Entry<Team, Double>> orderedResults = new
            PriorityQueue<>(
            ((o1, o2) -> Double.compare(o2.getValue(), o1.getValue())));
        orderedResults.addAll(teamScores.entrySet());
        for (Map.Entry<Team, Double> entry : orderedResults) {
            orderedTeamList.add(entry.getKey());
        }
        return orderedTeamList;
    }
}
