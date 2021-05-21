package game;

import edu.monash.fit2099.engine.*;

import static game.Capability.breed;

/**
 * A behaviour of Dinosaur.
 */
public class BreedBehaviour extends DinosaurBehaviour {

    /**
     * This method is to get location of nearest dinosaur to breed and return breed action if possible.
     *
     * @param dinosaur the Actor acting
     * @param map      the GameMap containing the Actor
     * @return getBreedAction if breedable dinosaur is in adjacent location, find Direction method if no breedable dinosaur in adjacent location.
     */
    @Override
    public Action getAction(Dinosaur dinosaur, GameMapSub map) {
        // check adjacent location for dinosaur
        Location currentLct = map.locationOf(dinosaur);

        if (dinosaur.hasCapability(breed)) {
            Action breedAction = dinosaur.getBreedAction(currentLct);

            if (breedAction != null){
                return breedAction;
            }

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
                    if (lct.containsAnActor() && lct.getActor() instanceof Dinosaur) {
                        Dinosaur otherDinosaur = (Dinosaur) lct.getActor();
                        if (dinosaur.canBreedWith(otherDinosaur)) {
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
        }
        return null;
    }


    /**
     * This method is to find direction for dinosaur to walk.
     *
     * @param currentLct current location of dinosaur
     * @param nearestLct current location of breedable dinosaur
     * @param map        game map
     * @param actor      actor acting
     * @param preferY    boolean
     * @return move action
     */
    @Override
    public MoveActorAction findDirection(Location currentLct, Location nearestLct, GameMap map, Actor actor, boolean preferY) {
        return super.findDirection(currentLct, nearestLct, map, actor, preferY);
    }

}
