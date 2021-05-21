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
    public Action getAction(Dinosaur dinosaur, GameMapSub map) {
        Location currentLoc = map.locationOf(dinosaur);
        if (dinosaur.isHungry()) {
            System.out.println(dinosaur + " at (" + currentLoc.x() + ", " + currentLoc.y() + ") is getting hungry!");

            Action eatAction = dinosaur.getEatAction(currentLoc);
            if (eatAction != null) {
                return eatAction;
            }

            int x1 = currentLoc.x();
            int y1 = currentLoc.y();

            int startX = Math.max(map.getXRange().min(), x1 - 10);
            int endX = Math.min(map.getXRange().max(), x1 + 10);

            int startY = Math.max(map.getYRange().min(), y1 - 10);
            int endY = Math.min(map.getYRange().max(), y1 + 10);

            int shortestDistance = 999;
            Location nearestLct = null;

            for (int x = startX; x <= endX; x++) {
                for (int y = startY; y <= endY; y++) {
                    Location loc = map.at(x, y);
                    Action tryEatAction = dinosaur.getEatAction(loc);
                    if (loc.canActorEnter(dinosaur) && tryEatAction != null) {
                        int distance = distance(currentLoc, loc);
                        if (distance < shortestDistance) {
                            shortestDistance = distance;
                            nearestLct = loc;
                        }
                    }
                }
            }

            if (nearestLct != null) {
                return followLocation(dinosaur, map, nearestLct);
            }
        }
        return null;
    }

}
