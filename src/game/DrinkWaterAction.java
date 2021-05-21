package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class DrinkWaterAction extends DinosaurAction {
    private Location loc;
    protected Lake waterSource;

    /**
     * A constructor for action performing drink water
     *
     * @param waterSource
     */
    public DrinkWaterAction(Lake waterSource) {
        this.waterSource = waterSource;
    }

    /**
     * This method is to let dinosaur to drink water.
     *
     * @param dinosaur The dinosaur performing the action.
     * @param map      game map
     * @return string printing dinosaur drink water
     */
    @Override
    public String execute(Dinosaur dinosaur, GameMap map) {
        loc = map.locationOf(dinosaur);
        dinosaur.drink(1);
        waterSource.setWaterSips(waterSource.getWaterSips() - 1);
        return menuDescription(dinosaur);
    }

    /**
     * This method is to print a line showing which dinosaur has drink water.
     *
     * @param actor The actor performing the action.
     * @return a string printing dinosaur has drink water
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " at (" + loc.x() + ", " + loc.y() + ") drink water.";
    }
}
