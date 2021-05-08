package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.List;

public class EatManyAction extends DinosaurAction {

    private Producible source;
    private Location loc;

    /**
     * A constructor that has a list of food as its parameter.
     * // TODO change comment
     * @param source a list of food that found in the location and is eatable
     */
    public EatManyAction(Producible source) {
        this.source = source;
    }

    /**
     * This method is to let dinosaur to eat food in the food list.
     *
     * @param dinosaur The dinosaur performing the action.
     * @param map   The map the actor is on.
     * @return
     */
    @Override
    public String execute(Dinosaur dinosaur, GameMap map) {
        // eat fruits from bush and ground
        loc = map.locationOf(dinosaur);
        String message = dinosaur + "at (" + loc.x() + ", " + loc.y() + ") eating many fruits";
        List<Food> foodToRemove = new ArrayList<>();
        for (Food food : source.getFruits()) {
            if (!dinosaur.isHungry()) break;
            dinosaur.eat(food);
            foodToRemove.add(food);
            message += System.lineSeparator() + dinosaur + " eats" + food;
        }
        source.removeFruits(foodToRemove);
        return message;
    }

    /**
     * This method is to print a line showing which dinosaur has eaten what food.
     *
     * @param actor The actor performing the action.
     * @return string showing which dinosaur has eaten what food.
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + "at (" + loc.x() + ", " + loc.y() + ") eats many fruits!!!";
    }
}
