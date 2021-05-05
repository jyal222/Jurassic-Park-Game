package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import game.Fruit;
import game.Player;

import java.util.Random;

public class PickFruitAction extends Action {

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        String message;
        if(actor instanceof Player){
            Random random = new Random();
            int number = random.nextInt(10) + 1; //either 1 or 2
            if (number > 6) {
                actor.addItemToInventory(new Fruit());
                message = "You successfully picked fruit from the tree or the bush.";
            } else {
                message = "You search the tree or bush for fruit, but you can't find any ripe ones.";
            }
        } else {
            throw new IllegalArgumentException("Actor must be an instance of type player to perform this action");
        }
        return message;
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Pick fruit from the tree or the bush";
    }
}
