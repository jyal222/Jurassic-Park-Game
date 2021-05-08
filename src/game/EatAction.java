package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

public class EatAction extends Action {

    private List<Eatable> foodList;
    private Location l;

    /**
     * A constructor that has a list of food as its parameter.
     * @param foodList a list of food that found in the location and is eatable
     */
    public EatAction(List<Eatable> foodList) {
        this.foodList = foodList;
    }

    /**
     * This method is to let dinosaur to eat food in the food list.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dinosaur = (Dinosaur) actor;
        // eat fruits from bush and ground
        l = map.locationOf(actor);
        //TODO need to remove food from list after eating
        for (Eatable food: foodList) {
            dinosaur.eat(food);
        }
        return menuDescription(actor);
    }
    // TODO: remember to set foodlevel when creating a food


    /**
     *  This method is to print a line showing which dinosaur has eaten what food.
     * @param actor The actor performing the action.
     * @return string showing which dinosaur has eaten what food.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + "at (" + l.x() + ", " + l.y() + ") eats" + foodList;
    }
}
