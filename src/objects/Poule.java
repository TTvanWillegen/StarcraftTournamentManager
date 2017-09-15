package objects;

import exceptions.OrderNotDefinedException;
import objects.match.Match;
import objects.match.MatchState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public class Poule {
	private List<Match> matchList = new ArrayList<>();
	private Random random = new Random();
	private Order matchOrder;

	public Poule(Order matchOrder) {
		this.matchOrder = matchOrder;
	}

	public Poule(Order matchOrder, List<Match> matches) {
		this(matchOrder);
		this.matchList = matches;
	}

	/**
	 * Returns the next {@link Match} in the sequence, based on the {@link
	 * objects.Order} that is provided.
	 *
	 * @return Next {@link Match} in line as defined by the {@link
	 * objects.Order}, will always be a {@link Match} with {@link
	 * MatchState#IN_LINE}
	 * @throws OrderNotDefinedException
	 * 	Throws this exception when there is no {@link objects.Order} defined in
	 * 	the Poule
	 */
	public Match getNextMatch() throws OrderNotDefinedException {
		if (matchOrder == Order.LINEAR) {
			return getNextLinearMatch();
		} else if (matchOrder == Order.RANDOM) {
			return getNextRandomMatch();
		}
		throw new OrderNotDefinedException();
	}

	/**
	 * Removes a {@link Match} from the list and returns *True* if it is
	 * removed.
	 *
	 * @param matchToRemove
	 * 	The {@link Match} to remove from the {@link objects.Poule}
	 * @return (boolean) *True* iff removed, *False* if not.
	 */
	public boolean removeMatch(Match matchToRemove) {
		return matchList.remove(matchToRemove);
	}

	public void addMatch(Match matchToAdd) {
		matchList.add(matchToAdd);
	}

	private List<Match> returnMatchesInLine() {
		return matchList.stream().filter(
			match -> match.getMatchState() == MatchState.IN_LINE).collect(
			Collectors.toList());
	}

	private Match getNextRandomMatch() {
		List<Match> matchesInLine = returnMatchesInLine();
		return matchesInLine.get(random.nextInt(matchesInLine.size()));
	}

	private Match getNextLinearMatch() {
		return returnMatchesInLine().get(0);
	}
}
