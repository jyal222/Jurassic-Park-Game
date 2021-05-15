package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class DrinkWaterAction extends DinosaurAction {
    private Water water;
    private Location loc;
    protected Lake waterSource;

    @Override
    public String execute(Dinosaur dinosaur, GameMap map) {
        loc = map.locationOf(dinosaur);
        if (waterSource.getWaterSips() >= 0) {
            dinosaur.drink(water);
            waterSource.setWaterSips(waterSource.getWaterSips() - 1);
        }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "at (" + loc.x() + ", " + loc.y() + ") drink water.";
    }
}
