package game;

import edu.monash.fit2099.engine.*;

/**
 * A behaviour of dinosaur.
 */
public class EatBehaviour extends DinosaurBehaviour {

    /**
     * This method is to returns a EatAction for the dinosaur to eat, if possible.
     * If no food to eat, return null
     *
     * @param dinosaur the Actor acting
     * @param map      the GameMap containing the Actor
     * @return eat action
     */
    @Override
    public Action getAction(Dinosaur dinosaur, GameMap map) {
        Location loc = map.locationOf(dinosaur);
        if (dinosaur.isHungry()) {
            System.out.println(dinosaur + " at (" + loc.x() + ", " + loc.y() + ") is getting hungry!");
            return dinosaur.getEatAction(loc);
        }
        return null;
    }
}
