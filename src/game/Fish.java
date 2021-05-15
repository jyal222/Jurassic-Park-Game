package game;

public class Fish extends Food {
    /**
     * This method is for a type of food based on the Item constructor.
     *
     * @param foodLevel    The amount of food level for each dinosaur to increase when they consumed a food
     */
    public Fish(int foodLevel) {
        super(foodLevel, 20, 5, "Fish", '>', true);
    }
}
