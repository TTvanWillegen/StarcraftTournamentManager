package objects.team;

import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A Team object contains a TeamName, aswell as a {@link TeamRace}, amount of games played and
 * amount of games won.
 *
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public class Team {
    private String name;
    private TeamRace teamRace;
    private int wins;
    private int plays;

    /**
     * Generates a {@link Team} object with provided name and {@link TeamRace}. Sets wins and
     * plays to 0.
     *
     * @param name     {@link String}The name the team gets.
     * @param teamRace {@link TeamRace} The race the team gets.
     */
    public Team(String name, TeamRace teamRace) {
        this.name = name;
        this.teamRace = teamRace;
        this.wins = 0;
        this.plays = 0;
    }

    /**
     * Increments the lost {@link objects.match.Match} counter for this {@link Team}.
     */
    public void addLost() {
        this.plays++;
    }

    /**
     * Increments the won {@link objects.match.Match} counter for this {@link Team}.
     */
    public void addWin() {
        this.wins++;
        this.plays++;
    }

    public String getName() {
        return name;
    }

    public TeamRace getTeamRace() {
        return teamRace;
    }

    public int getWins() {
        return wins;
    }

    public int getPlays() {
        return plays;
    }

    @Override
    public boolean equals(@Nullable Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Team team = (Team) o;

        if (wins != team.wins) {
            return false;
        }
        if (plays != team.plays) {
            return false;
        }
        if (name != null ? !name.equals(team.name) : team.name != null) {
            return false;
        }
        return teamRace == team.teamRace;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (teamRace != null ? teamRace.hashCode() : 0);
        result = 31 * result + wins;
        result = 31 * result + plays;
        return result;
    }

    @Override
    public String toString() {
        return "Team{" + "name='" + name + '\'' + ", teamRace=" + teamRace + ", wins=" + wins
                   + ", plays=" + plays + '}';
    }
}
