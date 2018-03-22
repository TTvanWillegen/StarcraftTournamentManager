package objects;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import objects.auxiliary.CircularLinkedList;
import objects.auxiliary.PeekableIterator;
import objects.match.Match;
import objects.team.Team;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

/**
 * A Round object contains one or multiple {@link Pool}s.
 *
 * @author Toby T. van Willegen
 * @version 1.1, 2017-06-13.
 */
public class Round {
    @Nullable
    private Round nextRound = null;
    @NonNull
    private CircularLinkedList<Pool> pouleList;
    @NonNull
    private PeekableIterator<Pool> pouleIterator;

    public Round() {
        pouleList = new CircularLinkedList<>();
        pouleIterator = pouleList.getIterator();
    }

    public Round(@NonNull List<Pool> poolList) {
        this();
        this.pouleList.addAll(poolList);
    }

    /**
     * Returns the next {@link Pool} in the sequence.
     */
    public Pool getNextPoule() {
        return pouleIterator.next();
    }

    /**
     * Checks if there is a next {@link Pool} in the sequence.
     */
    public boolean hasNextPoule() {
        return pouleIterator.hasNext();
    }

    /**
     * Returns the next {@link Match} in the sequence.
     */
    public Match getNextMatch() throws IndexOutOfBoundsException {
        return pouleIterator.next().getNextMatch();
    }

    /**
     * Peeks the next {@link Match} in the sequence.
     */
    public Match peekNextMatch() throws IndexOutOfBoundsException {
        return pouleIterator.peek().peekNextMatch();
    }

    /**
     * Checks if there is a next {@link Match} in the sequence.
     */
    public boolean hasNextMatch() {
        return pouleIterator.hasNext() && pouleIterator.peek().hasNextMatch();
    }

    /**
     * Returns a {@link java.util.Collection} of all the {@link Team}s that occur in all the {@link
     * Pool}s in this Round.
     *
     * @return A collection containing unique instances of the {@link Team}s of all the {@link
     *     Pool}s
     */
    public Collection<Team> getTeams() {
        Pool startingPool = pouleIterator.next();
        HashSet<Team> teamList = new HashSet<>(startingPool.getTeamList());

        while (!pouleIterator.peek().equals(startingPool)) {
            Pool pool = pouleIterator.next();
            teamList.addAll(pool.getTeamList());
        }
        return teamList;
    }

    public Collection<Team> getTopTeams(@NonNull int topN) {
        //TODO: make sure that the round has a way to get the top X
        return new HashSet<>();
    }

    /**
     * Removes a {@link Pool} from the list and returns *true* if it is removed.
     *
     * @param poolToRemove The {@link Pool} to remove from the {@link Pool}
     * @return (boolean) *True* if removed, *False* if not.
     */
    public boolean removePoule(@NonNull Pool poolToRemove) {
        return pouleList.remove(poolToRemove);
    }

    public void addPoules(@NonNull List<@NonNull Pool> poolToAdd) {
        pouleList.addAll(poolToAdd);
    }

    public void addPoule(@NonNull Pool poolToAdd) {
        pouleList.add(poolToAdd);
    }

    @Nullable
    public Round getNextRound() {
        return this.nextRound;
    }

    public void setNextRound(@Nullable Round round) {
        this.nextRound = round;
    }
}
