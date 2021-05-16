package game;

import edu.monash.fit2099.engine.*;

import java.util.*;

public class FlyBehaviour extends DinosaurBehaviour {

    private static final int MAX_FLYING_DISTANCE = 30;

    /**
     * TODO
     * This method is to returns an Action for the dinosaur to fly.
     *
     * @param dinosaur the dinosaur acting
     * @param map      the GameMap containing the Dinosaur
     * @return fly action
     */
    @Override
    public Action getAction(Dinosaur dinosaur, GameMapSub map) {
        Location here = map.locationOf(dinosaur);
        map.removeActor(dinosaur);

//        Location there = map.locationOf(target);
        List<Location> treesLocation = getTreesLocations(map);

        List<Integer> integerList = new ArrayList<>();
        Map<Integer, Location> locationMap = new HashMap<>();

        for (Location loc : treesLocation) {
            if (loc.getGround().canDinosaurStand()) {
                int newDistance = distance(here, loc);
                if (newDistance <= MAX_FLYING_DISTANCE) {
                    integerList.add(newDistance);
                    locationMap.put(newDistance, loc);
                }
            }
        }
        if (!locationMap.isEmpty()) {
            // Take the location with the shortest distance
            Collections.sort(integerList);
            int shortestDistance = integerList.get(0);
            Location nearestLoc = locationMap.get(shortestDistance);

            Location destination;
            if (shortestDistance <= MAX_FLYING_DISTANCE) {
                destination = nearestLoc;
//                nearestLoc.getGround().setDinosaur(dinosaur);
            } else {
                int x2 = nearestLoc.x();
                int y2 = nearestLoc.y();

                int dx = x2 - here.x();
                int dy = y2 - here.y();

                int newX, newY;
                if (Math.abs(dx) >= MAX_FLYING_DISTANCE) {
                    newX = dx > 0 ? here.x() + MAX_FLYING_DISTANCE : here.x() - MAX_FLYING_DISTANCE;
                    newY = here.y();
                } else {
                    int distanceLeft = MAX_FLYING_DISTANCE - Math.abs(dx);
                    newX = x2;
                    newY = dy > 0 ? here.y() + distanceLeft : here.y() - distanceLeft;
                }

                destination = map.at(newX, newY);
                if (destination.canActorEnter(dinosaur)) {
//                    destination.addActor(dinosaur);
                } else {

                }
            }


        }

        return null;
    }

    /**
     * To find the direction for the dinosaur
     *
     * @param currentLct
     * @param nearestLct
     * @param map
     * @param actor
     * @param preferY
     * @return move action
     */
    @Override
    public MoveActorAction findDirection(Location currentLct, Location nearestLct, GameMap map, Actor actor, boolean preferY) {
        return super.findDirection(currentLct, nearestLct, map, actor, preferY);
    }

    private int distance(Location a, Location b) {
        return Math.abs(a.x() - b.x()) + Math.abs(a.y() - b.y());
    }

    public List<Location> getTreesLocations(GameMap map) {
        NumberRange xRange = map.getXRange();
        NumberRange yRange = map.getYRange();

        List<Location> treesLocations = new ArrayList<>();
        for (Integer x : xRange) {
            for (Integer y : yRange) {
                Location lct = map.at(x, y);
                if (lct.getGround() instanceof Tree) {
                    treesLocations.add(lct);
                }
            }
        }
        return treesLocations;
    }

}
