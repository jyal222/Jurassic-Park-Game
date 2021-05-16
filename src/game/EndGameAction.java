package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class EndGameAction extends Action {

    /**
     * To remove the current player and end the game.
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return empty string
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        // Remove player from the map
        if (actor instanceof Player) {
            map.removeActor(actor);
        } else {
            throw new IllegalArgumentException("Actor must be an instance of type player to perform this action");
        }
        return "";
    }

    /**
     * Player able to view and potentially select to end the game
     *
     * @param actor The actor performing the action.
     * @return a string
     */
    @Override
    public String menuDescription(Actor actor) {
        return "End the game";
    }
}
