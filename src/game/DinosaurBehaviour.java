package game;

import edu.monash.fit2099.engine.*;

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

    public MoveActorAction findDirection(Location currentLct, Location nearestLct, GameMap map, Actor actor, boolean preferY) {

        int x2 = nearestLct.x();
        int y2 = nearestLct.y();
        int x1 = currentLct.x();
        int y1 = currentLct.y();
        int dx = x2 - x1;
        int dy = y2 - y1;

        if (dy >= dx && dy != 0 && preferY) {
            if (dy > 0) { // move upwards
                MoveActorAction action = map.at(x1, y1 + 1).getMoveAction(actor, "north", "a");
                if (action == null) {
                    return findDirection(currentLct, nearestLct, map, actor, false);
                }
            } else {
                //move downwards
                MoveActorAction action = map.at(x1, y1 - 1).getMoveAction(actor, "south", "a");
                if (action == null) {
                    return findDirection(currentLct, nearestLct, map, actor, false);
                }
            }
        } else if (dy < dx && dx != 0) {
            //move in x axis
            if (dx > 0) { // move right
                return map.at(x1 + 1, y1).getMoveAction(actor, "east", "a");
            } else {
                //move left
                return map.at(x1 - 1, y1).getMoveAction(actor, "west", "a");
            }
        }
        return null;
    }
}
