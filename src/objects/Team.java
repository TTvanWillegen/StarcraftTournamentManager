package objects;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public class Team {
	private String name;
	private Race race;
	private int wins;
	private int plays;
	public Team(String name, Race race){
		this.name = name;
		this.race = race;
		this.wins = 0;
		this.plays = 0;
	}
	public void addLost(){
		this.plays++;
	}
	public void addWin(){
		this.wins++;
		this.plays++;
	}

	public String getName() {
		return name;
	}

	public Race getRace() {
		return race;
	}

	public int getWins() {
		return wins;
	}

	public int getPlays() {
		return plays;
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

		if (wins != team.wins) {
			return false;
		}
		if (plays != team.plays) {
			return false;
		}
		if (name != null ? !name.equals(team.name) : team.name != null) {
			return false;
		}
		return race == team.race;
	}

	@Override
	public int hashCode() {
		int result = name != null ? name.hashCode() : 0;
		result = 31 * result + (race != null ? race.hashCode() : 0);
		result = 31 * result + wins;
		result = 31 * result + plays;
		return result;
	}

	@Override
	public String toString() {
		return "Team{" +
			"name='" + name + '\'' +
			", race=" + race +
			", wins=" + wins +
			", plays=" + plays +
			'}';
	}
}
