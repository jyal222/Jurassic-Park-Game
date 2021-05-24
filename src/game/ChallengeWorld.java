package game;

import edu.monash.fit2099.engine.Display;
import edu.monash.fit2099.engine.World;

public class ChallengeWorld extends WorldSub {

    private final int maxMoveNumber;
    private final int targetEcoPoint;

    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */
    public ChallengeWorld(int maxMoveNumber, int targetEcoPoint, Display display) {
        super(display);
        this.maxMoveNumber = maxMoveNumber;
        this.targetEcoPoint = targetEcoPoint;
    }

    /**
     * Check if the game is still running
     * Display string if player has enough eco points or has exceeded the move number
     *
     * @return boolean
     */
    @Override
    protected boolean stillRunning() {
        boolean hasEnoughEcoPoint = ((Player) player).getEcoPoints() >= targetEcoPoint;
        boolean hasExceedMoveNumber = ((Player) player).turns >= maxMoveNumber;

        if (hasEnoughEcoPoint) {
            display.println("Congratulations! You've won!");
            return false;
        }

        if (hasExceedMoveNumber) {
            display.println("Oh no! You've reached the max number of moves. You lose!");
            return false;
        }

        return super.stillRunning();
    }
}
