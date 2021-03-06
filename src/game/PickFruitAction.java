package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.Random;

public class PickFruitAction extends Action {

    Producible source;

    public PickFruitAction(Producible source) {
        this.source = source;
    }

    /**
     * To pick fruit if available.
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Player) {
            Player player = (Player) actor;
            Random random = new Random();
            int number = random.nextInt(10) + 1;
            if (number > 6 && !source.getFood().isEmpty()) {
                Food fruit = source.getFood().get(0);
                player.earn(10);
                player.addItemToInventory(fruit);
                source.removeFood(fruit);
                return actor + " successfully picked fruit from " + source;
            } else {
                return actor + " searched the tree or bush for fruit, but can't find any ripe ones.";
            }
        } else {
            throw new IllegalArgumentException("Actor must be an instance of type player to perform this action");
        }
    }

    /**
     * To return a string showing action
     * @param actor The actor performing the action.
     * @return String
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " tries to pick fruit from " + source;
    }
}
