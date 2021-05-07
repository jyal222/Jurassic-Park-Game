package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public abstract class DinosaurAction extends Action {
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Dinosaur) {
            return execute((Dinosaur) actor, map);
        }
        return null;
    }

    public abstract String execute(Dinosaur dinosaur, GameMap map);
}
