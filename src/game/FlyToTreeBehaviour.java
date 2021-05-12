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
                }
                return null;
            }
        }
        return null;
    }
}
