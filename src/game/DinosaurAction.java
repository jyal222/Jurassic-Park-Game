package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An abstract class for all dinosaur specific action.
 */
public abstract class DinosaurAction extends Action {
    /**
     * Allow the Actor to do action.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return execute
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Dinosaur) {
            return execute((Dinosaur) actor, map);
        }
        return null;
    }

    /**
     * This is to be overridden by other dinosaur action.
     * @param dinosaur The dinosaur performing the action.
     * @param map game map
     * @return execute
     */
    public abstract String execute(Dinosaur dinosaur, GameMap map);
}
