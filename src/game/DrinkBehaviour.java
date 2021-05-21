package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.Location;

public class DrinkBehaviour extends DinosaurBehaviour {

    /**
     * This method is to returns a DrinkAction for the dinosaur to drink, if thirsty.
     *
     * @param dinosaur the dinosaur acting
     * @param map      the GameMap containing the Dinosaur
     * @return drink action
     */
    @Override
    public Action getAction(Dinosaur dinosaur, GameMapSub map) {
        Location currentLoc = map.locationOf(dinosaur);
        if (dinosaur.isThirsty()) {
            System.out.println(dinosaur + " at (" + currentLoc.x() + ", " + currentLoc.y() + ") is getting thirsty!");
            for (Exit exit : map.locationOf(dinosaur).getExits()) {
                if (exit.getDestination().getGround().canDrink()) {
                    return dinosaur.getDrinkAction((Lake) exit.getDestination().getGround());
                }
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
                    if (loc.canActorEnter(dinosaur) && loc.getGround().canDrink()) {
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
