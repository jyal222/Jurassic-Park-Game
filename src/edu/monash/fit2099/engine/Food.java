package edu.monash.fit2099.engine;

public abstract class Food extends Item {
    int foodLevel;
    int foodPrice;
    int pointsGained;


    /**
     * This method is for a type of food based on the Item constructor.
     * @param foodLevel The amount of food level for each dinosaur to increase when they consumed a food
     * @param foodPrice The price of the food selling in vending machine
     * @param pointsGained The points gained when they feed a food to dinosaur
     * @param name the name of this Item
     * @param displayChar the character to use to represent this item if it is on the ground
     * @param portable true if and only if the Item can be picked up
     */
    public Food(int foodLevel, int foodPrice, int pointsGained, String name, char displayChar, boolean portable) {
        super(name, displayChar, portable);
        this.foodLevel = foodLevel;
        this.foodPrice = foodPrice;
        this.pointsGained = pointsGained;
    }

    /**
     * To get the food level of each food child instance
     * @return foodLevel integer
     */
    public int getFoodLevel() {
        return foodLevel;
    }

    /**
     * To set the food level of each food child instance
     * @param foodLevel the foodLevel of each food child instance
     */
    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }
}
