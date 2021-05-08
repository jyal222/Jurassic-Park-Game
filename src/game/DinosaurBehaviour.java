package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An abstract class implements Behaviour.
 */
public abstract class DinosaurBehaviour implements Behaviour {
    /**
     * This method is to returns a Dinosaur Action for the dinosaur.
     * If no action to do, return null
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return get action of dinosaur
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor instanceof Dinosaur) {
            return getAction((Dinosaur) actor, map);
        }
        return null;
    }

    /**
     * To be overridden by other dinosaur's behaviour.
     * @param dinosaur the dinosaur acting
     * @param map the GameMap containing the Dinosaur
     * @return action
     */
    public abstract Action getAction(Dinosaur dinosaur, GameMap map);
}
