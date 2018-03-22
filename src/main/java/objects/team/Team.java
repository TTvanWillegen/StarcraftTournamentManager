package objects.team;

import java.util.Objects;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * A Team object contains a TeamName, aswell as a {@link TeamRace}, amount of
 * games played and
 * amount of games won.
 *
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public class Team {
	@NonNull
	private String name;
	@NonNull
	private TeamRace teamRace;

	/**
	 * Generates a {@link Team} object with provided name and
	 * {@link TeamRace}. Sets wins and
	 * plays to 0.
	 *
	 * @param name
	 * 	{@link String}The name the team gets.
	 * @param teamRace
	 * 	{@link TeamRace} The race the team gets.
	 */
	public Team(@NonNull String name, @NonNull TeamRace teamRace) {
		this.name = name;
		this.teamRace = teamRace;
	}

	@NonNull
	public String getName() {
		return name;
	}

	@NonNull
	public TeamRace getTeamRace() {
		return teamRace;
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Team team = (Team) o;
		return Objects.equals(name, team.name) && teamRace == team.teamRace;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, teamRace);
	}

	@NonNull
	@Override
	public String toString() {
		return "Team{" + "name='" + name + '\'' + ", teamRace=" + teamRace +
			'}';
	}
}
