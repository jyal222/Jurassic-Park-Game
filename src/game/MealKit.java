package game;

/**
 * A class that inherited from Food.
 */
public abstract class MealKit extends Food {

//    protected String type;

    /**
     * Instantiates a MealKit constructor
     * @param name name of mealkit
     * @param foodPrice price of mealkit
     */
    public MealKit(String name, int foodPrice) {
        super(1000, foodPrice, 0, name, 'm', true);
    }

}