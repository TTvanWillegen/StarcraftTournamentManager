package objects.match;

import java.util.ArrayList;

import objects.team.Team;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;


/**
 * @author Toby T. van Willegen
 * @version 1.1, 2018-03-21.
 */
public class MatchResultTest {

    @Test
    public void testHighestScore() throws NoTeamAddedException {
        MatchResult matchResult = new MatchResult();
        Team teamA = mock(Team.class);
        Team teamB = mock(Team.class);
        matchResult.setScore(teamA, 0);
        matchResult.setScore(teamB, 1);
        assertEquals(matchResult.getWinningTeam(), teamB);
    }

    @Test(expected = NoTeamAddedException.class)
    public void testHighestScoreEmpty() throws NoTeamAddedException {
        MatchResult matchResult = new MatchResult();
        assertEquals(matchResult.getWinningTeam(), null);
    }


    @Test
    public void testTeamOrderScore() {
        MatchResult matchResult = new MatchResult();
        Team teamA = mock(Team.class);
        Team teamB = mock(Team.class);
        matchResult.setScore(teamA, 0);
        matchResult.setScore(teamB, 1);
        ArrayList<Team> teamOrder = new ArrayList<>();
        teamOrder.add(teamB);
        teamOrder.add(teamA);
        assertEquals(matchResult.getTeamOrder(), teamOrder);
    }

    @Test
    public void testTeamOrderScoreEmpty() {
        MatchResult matchResult = new MatchResult();
        ArrayList<Team> teamOrder = new ArrayList<>();
        assertEquals(matchResult.getTeamOrder(), teamOrder);
    }
}
