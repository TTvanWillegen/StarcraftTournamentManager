package objects;

import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * The Game object containing one or multiple {@link Round}s.
 *
 * @author Toby T. van Willegen
 * @version 1.1, 2018-03-21.
 */
public class Game {
    @NonNull
    private Round startingRound;

    public Game() {
        startingRound = new Round();
    }

    public Game(@NonNull Round round) {
        this.startingRound = round;
    }

    // teams to pass through.
    public void addRound(@NonNull Round round) {
        getLastRound().setNextRound(round);
    }

    @NonNull
    private Round getLastRound() {
        @NonNull Round round = startingRound;
        while (round.getNextRound() != null) {
            Round nextRound = round.getNextRound();
            if (nextRound != null) {
                round = nextRound;
            }
        }
        return round;
    }
}
