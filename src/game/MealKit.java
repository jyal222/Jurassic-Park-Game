package game;

/**
 * A class that inherited from Food.
 */
public abstract class MealKit extends Food {

    /**
     * Instantiates a MealKit constructor
     * @param name name of mealkit
     * @param price price of mealkit
     */
    public MealKit(String name, int price) {
        super(1000, price, 0, name, 'm', true);
    }

}