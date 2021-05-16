package game;

/**
 * Food that can be eaten by dinosaur.
 * Every Eatable must have a food level.
 */
public interface Eatable {

    int getFoodLevel(); // get the food level

    void decreaseFoodLevel(int amount); // decrease food level of food
}
