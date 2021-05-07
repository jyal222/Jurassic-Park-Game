package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public abstract class DinosaurBehaviour implements Behaviour {
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor instanceof Dinosaur) {
            return getAction((Dinosaur) actor, map);
        }
        return null;
    }

    public abstract Action getAction(Dinosaur dinosaur, GameMap map);
}
