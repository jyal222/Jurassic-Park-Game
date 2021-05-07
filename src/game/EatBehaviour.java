package game;

import edu.monash.fit2099.engine.*;

public class EatBehaviour implements Behaviour {

    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dinosaur = (Dinosaur) actor;
        Location l = map.locationOf(actor);
        return dinosaur.getEatAction(l);
    }
}
