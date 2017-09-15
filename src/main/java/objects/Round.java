package objects;

import exceptions.OrderNotDefinedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public class Round {
	private List<Poule> pouleList = new ArrayList<>();
	private Random random = new Random();
	private Order pouleOrder;

	public Round(Order pouleOrder) {
		this.pouleOrder = pouleOrder;
	}

	public Round(Order pouleOrder, List<Poule> pouleList) {
		this(pouleOrder);
		this.pouleList = pouleList;
	}

	/**
	 * Returns the next {@link Poule} in the sequence, based on the
	 * {@link objects.Order} that is provided.
	 *
	 * @return Next {@link objects.Poule} in line as defined by the {@link
	 * objects.Order}
	 * @throws OrderNotDefinedException
	 * 	Throws this exception when there is no {@link objects.Order} defined in
	 * 	the Poule
	 */
	public Poule getNextPoule() throws OrderNotDefinedException {
		switch (pouleOrder) {
			case LINEAR:
				return getNextLinearPoule();
			case RANDOM:
				return getNextRandomPoule();
		}
		throw new OrderNotDefinedException();
	}

	/**
	 * Removes a {@link objects.Poule} from the list and returns *true* if
	 * it is
	 * removed.
	 *
	 * @param pouleToRemove
	 * 	The {@link objects.Poule} to remove from the {@link objects.Poule}
	 * @return (boolean) *True* if removed, *False* if not.
	 */
	public boolean removePoule(Poule pouleToRemove) {
		return pouleList.remove(pouleToRemove);
	}

	public void addPoule(Poule pouleToAdd) {
		pouleList.add(pouleToAdd);
	}

	private Poule getNextRandomPoule() {
		return pouleList.get(random.nextInt(pouleList.size()));
	}

	private Poule getNextLinearPoule() {
		return pouleList.get(0);
	}
}
