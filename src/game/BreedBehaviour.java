package game;

import edu.monash.fit2099.engine.*;

/**
 * A behaviour of Dinosaur.
 */
public class BreedBehaviour extends DinosaurBehaviour {

    /**
     * This method is to get location of nearest dinosaur to breed and return breed action if possible.
     * @param dinosaur the Actor acting
     * @param map the GameMap containing the Actor
     * @return getBreedAction if breedable dinosaur is in adjacent location, find Direction method if no breedable dinosaur in adjacent location.
     */
    @Override
    public Action getAction(Dinosaur dinosaur, GameMap map) {
        // check adjacent location for dinosaur
        for (Exit exit : map.locationOf(dinosaur).getExits()) {
            if (exit.getDestination().getActor() instanceof Dinosaur) {
                Dinosaur act = (Dinosaur) exit.getDestination().getActor();
                if (dinosaur.canBreed(act)) {
                    System.out.println(dinosaur + " found a mate.");
                    return dinosaur.getBreedAction(act);
                }
            }
        }

        System.out.println(dinosaur + " moves to find mate.");
        // if adjacent location no dinosaur, find the nearest dinosaur from the whole map
        NumberRange xRange = map.getXRange();
        NumberRange yRange = map.getYRange();
        Location curLocation = map.locationOf(dinosaur);
        int x1 = curLocation.x();
        int y1 = curLocation.y();
        double shortestDistance = 999;
        Location nearestLct = null;

        for (Integer x : xRange) {
            for (Integer y : yRange) {
                Location lct = map.at(x, y);
                if (map.isAnActorAt(lct) && map.getActorAt(lct) instanceof Dinosaur) {
                    Dinosaur act = (Dinosaur) map.getActorAt(lct);
                    if (dinosaur.canBreed(act)) {
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
            return findDirection(curLocation, nearestLct, map, dinosaur, true);

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
