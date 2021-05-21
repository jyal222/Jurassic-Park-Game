package game;

import edu.monash.fit2099.engine.*;

public class EatAction extends DinosaurAction {

    private Food food;
    private Producible source;
    private Location loc;

    /**
     * A constructor that has a food as its parameter.
     *
     * @param food a food that is found in the location and is eatable
     */
    public EatAction(Food food) {
        this.food = food;
    }

    /**
     * A constructor that has a food as its parameter.
     *
     * @param food   a food that is found from the source
     * @param source the source of the food
     */
    public EatAction(Food food, Producible source) {
        this(food);
        this.source = source;
    }

    /**
     * This method is to let dinosaur to eat food in the food list.
     *
     * @param dinosaur The dinosaur performing the action.
     * @param map      The map the actor is on.
     * @return
     */
    @Override
    public String execute(Dinosaur dinosaur, GameMap map) {
        loc = map.locationOf(dinosaur);
        if (source != null) {
            // eat food from ground
            dinosaur.eat(food);
            if (food.getFoodLevel() <= 0) {
                loc.removeItem(food);
            }
            return menuDescription(dinosaur);
        } else {
            // eat food from bush/ tree/ lake
            dinosaur.eat(food);
            source.removeFood(food);
            return menuDescription(dinosaur);
        }
    }


    /**
     * This method is to print a line showing which dinosaur has eaten what food.
     *
     * @param actor The actor performing the action.
     * @return string showing which dinosaur has eaten what food.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " at (" + loc.x() + ", " + loc.y() + ") eats " + food;
    }
}
