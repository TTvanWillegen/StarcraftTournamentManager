package objects;


/**
 * The Tournament object containing one or multiple {@link Round}s.
 *
 * @author Toby T. van Willegen
 * @version 1.1, 2018-03-21.
 */
public class Tournament {

	private static Tournament tournament = null;
	private Round startingRound;

	private Tournament() {
		startingRound = null;
	}

	public static Tournament getInstance() {
		if (tournament == null) {
			tournament = new Tournament();
		}
		return tournament;
	}

	// teams to pass through.
	public void addRound(Round round) {
		if (startingRound == null) {
			startingRound = round;
		} else {
			getLastRound().setNextRound(round);
		}
	}

	// teams to pass through.
	public void removeRound(Round round) {
		Round lastRound = null;
		Round currentRound = startingRound;
		while (currentRound != null) {

			if (round.equals(currentRound)) {
				if(lastRound == null){
					startingRound = currentRound.getNextRound();
				}else{
					lastRound.setNextRound(currentRound.getNextRound());
				}
				System.out.println("Removed: " + currentRound.toString());
				break;
			}
			lastRound = currentRound;
			currentRound = currentRound.getNextRound();
		}
	}

	private Round getLastRound() {
		Round round = startingRound;
		while (round.getNextRound() != null) {
			Round nextRound = round.getNextRound();
			if (nextRound != null) {
				round = nextRound;
			}
		}
		return round;
	}
}
