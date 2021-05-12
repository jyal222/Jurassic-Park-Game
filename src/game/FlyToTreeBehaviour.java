package game;

import edu.monash.fit2099.engine.*;

public class FlyToTreeBehaviour extends DinosaurBehaviour {
    @Override
    public Action getAction(Dinosaur dinosaur, GameMap map) {

        if (dinosaur instanceof Pterodactyls) {
            Pterodactyls pterodactyls = (Pterodactyls) dinosaur;
            // check adjacent location for Pterodactyls on tree
            for (Exit exit : map.locationOf(pterodactyls).getExits()) {
                if (exit.getDestination().getGround() instanceof Tree) {
                    Dinosaur otherDinosaur = (Dinosaur) exit.getDestination().getActor();
                    if (dinosaur.canBreedWith(otherDinosaur)) {
                        System.out.println(dinosaur.gender + " " + dinosaur + " found a mate.");
                        return dinosaur.getBreedAction(otherDinosaur);
                    }
                }
            }
            // if adjacent location no dinosaur, find the nearest tree from the whole map
            NumberRange xRange = map.getXRange();
            NumberRange yRange = map.getYRange();
            Location curLocation = map.locationOf(pterodactyls);
            int x1 = curLocation.x();
            int y1 = curLocation.y();
            double shortestDistance = 999;
            Location nearestLct = null;

            // todo
            for (Integer x : xRange) {
                for (Integer y : yRange) {
                    Location lct = map.at(x, y);
                    if (lct.getGround() instanceof Tree) {
                        // ???? maybe on ground
                        if (lct.containsAnActor() && lct.getActor() instanceof Pterodactyls) {
                            Dinosaur otherDinosaur = (Dinosaur) lct.getActor();
                        }

            }
            if (nearestLct != null) {
                return findDirection(curLocation, nearestLct, map, pterodactyls, true);
            }
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
