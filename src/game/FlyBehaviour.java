package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

public class FlyBehaviour extends DinosaurBehaviour {
    @Override
    public Action getAction(Dinosaur dinosaur, GameMap map) {
        Location currentLocation = map.locationOf(dinosaur);

        return null;
    }

    @Override
    public MoveActorAction findDirection(Location currentLct, Location nearestLct, GameMap map, Actor actor, boolean preferY) {
        return super.findDirection(currentLct,nearestLct, map, actor, preferY);
    }
}
