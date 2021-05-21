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
        Location currentLoc = map.locationOf(dinosaur);

        if (dinosaur.hasCapability(breed)) {
            System.out.println(dinosaur + " at (" + currentLoc.x() + ", " + currentLoc.y() + ") is getting horny!"); // todo: nothing

            Action breedAction = dinosaur.getBreedAction(currentLoc);

            if (breedAction != null){
                return breedAction;
            }

            // if adjacent location no dinosaur, find the nearest dinosaur from the whole map
            int shortestDistance = 999;
            Actor nearestDinosaur = null;
            for (Actor actor : map.getActorLocations()) {
                Location loc = map.locationOf(actor);
                if (dinosaur.canBreedWith(actor)) {
                    int distance = distance(currentLoc, loc);
                    if (distance < shortestDistance) {
                        shortestDistance = distance;
                        nearestDinosaur = actor;
                    }
                }
            }

            if (nearestDinosaur != null) {
                return new FollowBehaviour(nearestDinosaur).getAction(dinosaur, map);
            }
        }

        return null;
    }

}
