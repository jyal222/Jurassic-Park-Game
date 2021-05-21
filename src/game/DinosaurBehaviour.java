package game;

import edu.monash.fit2099.engine.*;

/**
 * An abstract class implements Behaviour.
 */
public abstract class DinosaurBehaviour implements Behaviour {
    /**
     * This method is to returns a Dinosaur Action for the dinosaur.
     * If no action to do, return null
     *
     * @param actor the Actor acting
     * @param map   the GameMap containing the Actor
     * @return get action of dinosaur
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        if (actor instanceof Dinosaur) {
            return getAction((Dinosaur) actor, (GameMapSub) map);
        }
        return null;
    }

    /**
     * To be overridden by other dinosaur's behaviour.
     *
     * @param dinosaur the dinosaur acting
     * @param map      the GameMap containing the Dinosaur
     * @return action
     */
    public abstract Action getAction(Dinosaur dinosaur, GameMapSub map);


    /**
     * To get distance
     * @param a
     * @param b
     * @return integer distance
     */
    public int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    /**
     * To follow the destination location.
     * @param actor
     * @param map
     * @param there
     * @return MoveActorAction
     */
    public Action followLocation(Actor actor, GameMap map, Location there) {
        Location here = map.locationOf(actor);

        int currentDistance = distance(here, there);
        for (Exit exit : here.getExits()) {
            Location destination = exit.getDestination();
            if (destination.canActorEnter(actor)) {
                int newDistance = distance(destination, there);
                if (newDistance < currentDistance) {
                    return new MoveActorAction(destination, exit.getName());
                }
            }
        }

        return null;
    }

}
