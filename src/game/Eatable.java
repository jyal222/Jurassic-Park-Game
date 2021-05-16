package game;

/**
 * Food that can be eaten by dinosaur.
 * Every Eatable must have a food level.
 */
public interface Eatable {

    int getFoodLevel();

    void decreaseFoodLevel(int amount);
}
