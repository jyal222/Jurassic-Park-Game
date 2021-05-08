package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An abstract class implements Behaviour.
 */
public abstract class DinosaurBehaviour implements Behaviour {
    /**
     * TODO description
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return TODO
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor instanceof Dinosaur) {
            return getAction((Dinosaur) actor, map);
        }
        return null;
    }

    /**
     *
     * @param dinosaur the dinosaur acting
     * @param map the GameMap containing the Dinosaur
     * @return TODO
     */
    public abstract Action getAction(Dinosaur dinosaur, GameMap map);
}
