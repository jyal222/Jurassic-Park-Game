package game;

import edu.monash.fit2099.engine.*;

import static game.Capability.breed;

/**
 * A behaviour of Dinosaur.
 */
public class BreedBehaviour implements Behaviour {

    /**
     * This method is to get location of nearest dinosaur to breed.
     * @param actor the Actor acting
     * @param map the GameMap containing the Actor
     * @return getBreedAction if breedable dinosaur is in adjacent location, findDirectoin method if no breedable dinosaur in adjacent location,
     * null if no breedable dinosaur in the whole map.
     */
    @Override
    public Action getAction(Actor actor, GameMap map) {
        Dinosaur me = (Dinosaur) actor;

        // check adjacent location for dinosaur
        for (Exit exit : map.locationOf(actor).getExits()) {
            if (exit.getDestination().getActor() instanceof Dinosaur) {
                Dinosaur act = (Dinosaur) exit.getDestination().getActor();
                if (me.canBreed(act)) {
                    return me.getBreedAction();
                }
            }
        }

        // if adjacent location no dinosaur, find the nearest dinosaur from the whole map
        NumberRange xRange = map.getXRange();
        NumberRange yRange = map.getYRange();
        Location curLocation = map.locationOf(actor);
        int x1 = curLocation.x();
        int y1 = curLocation.y();
        double shortestDistance = 999;
        Location nearestLct = null;

        for (Integer x : xRange) {
            for (Integer y : yRange) {
                Location lct = map.at(x, y);
                if (map.isAnActorAt(lct) && map.getActorAt(lct) instanceof Dinosaur) {
                    Dinosaur act = (Dinosaur) map.getActorAt(lct);
                    // TODO add and remove capability for breed in Dinosaur class
                    if (me.canBreed(act)) {
                        double distance = Math.sqrt(Math.pow((x - x1), 2) + Math.pow((y - y1), 2));
                        if (distance < shortestDistance) {
                            shortestDistance = distance;
                            nearestLct = lct;
                        }
                    }
                }
            }
        }
        if (nearestLct != null) {
            return findDirection(curLocation, nearestLct, map, actor, true);

        }
        return null;
    }

    /**
     * This method is to find direction for dinosaur to walk.
     * @param currentLct current location of dinosaur
     * @param nearestLct current location of breedable dinosaur
     * @param map game map
     * @param actor actor acting
     * @param preferY boolean
     * @return move action
     */
    public MoveActorAction findDirection(Location currentLct, Location nearestLct, GameMap map, Actor actor, boolean preferY) {

        int x2 = nearestLct.x();
        int y2 = nearestLct.y();
        int x1 = currentLct.x();
        int y1 = currentLct.y();
        int dx = x2 - x1;
        int dy = y2 - y1;

        //TODO change direction
        if (dy >= dx && dy != 0 && preferY) {
            if (dy > 0) {
                MoveActorAction action = map.at(x1, y1 + 1).getMoveAction(actor, "north", "a");
                if (action == null) {
                    return findDirection(currentLct, nearestLct, map, actor, false);
                }
            } else {
                //move downwards
                MoveActorAction action = map.at(x1, y1 - 1).getMoveAction(actor, "north", "a");
                if (action == null) {
                    return findDirection(currentLct, nearestLct, map, actor, false);
                }
            }
        } else if (dy < dx && dx != 0) {
            //move in x axis
            if (dx > 0) {
                return map.at(x1 + 1, y1).getMoveAction(actor, "north", "a");
            } else {
                //move left
                return map.at(x1 - 1, y1).getMoveAction(actor, "north", "a");
            }
        }
        return null;
    }

}
