package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class DrinkBehaviour extends DinosaurBehaviour {

    /**
     *
     * @param dinosaur the dinosaur acting
     * @param map the GameMap containing the Dinosaur
     * @return
     */
    @Override
    public Action getAction(Dinosaur dinosaur, GameMap map) {
        Location loc = map.locationOf(dinosaur);
        if (dinosaur.isThirsty()) {
            System.out.println(dinosaur + " at (" + loc.x() + ", " + loc.y() + ") is getting thirsty!");
            return dinosaur.getDrinkAction(loc, map);
        }
        return null;
    }
}
