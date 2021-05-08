package game;

import edu.monash.fit2099.engine.*;

/**
 * A behaviour of dinosaur.
 */
public class EatBehaviour implements Behaviour {

    /**
     * This method is to TODO
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return eat action
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur dinosaur = (Dinosaur) actor;
        Location l = map.locationOf(actor);
        return dinosaur.getEatAction(l);
    }
}
