package game;

public class Water extends Food{
    /**
     * This method is for a type of food based on the Item constructor.
     *
     * @param foodLevel    The amount of food level for each dinosaur to increase when they consumed a food
     * @param price        The price of the food selling in vending machine
     * @param pointsGained The points gained when they feed a food to dinosaur
     * @param name         the name of this Item
     * @param displayChar  the character to use to represent this item if it is on the ground
     * @param portable     true if and only if the Item can be picked up
     */
    public Water(int foodLevel, int price, int pointsGained, String name, char displayChar, boolean portable) {
        super(foodLevel, price, pointsGained, name, displayChar, portable);
    }
}
