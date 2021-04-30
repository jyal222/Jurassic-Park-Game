package game;

import edu.monash.fit2099.engine.Food;

public class MealKit extends Food {

    String type;

    /**
     * Instantiates a MealKit constructor
     * @param type a string value of either "carnivore" or "vegetarian"
     */
    public MealKit(String type) {
        super(9999, 100, 0, "Vegetarian Meal Kit", 'm', true);
        if (type.equals("carnivore") || type.equals("vegetarian")){
            this.type = type;
            this.setPrice(100);
            if (this.type == "carnivore"){
                this.setPrice(500);
                this.setName("Carnivore Meal Kit");
            }
        } else {
            throw new IllegalArgumentException("Type must be either carnivore or vegetarian");
        }
    }

    /**
     * Get the type of mealkit
     * @return a string of either "carnivore" or "vegetarian"
     */
    public String getType() {
        return type;
    }
}
