package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class DrinkWaterAction extends DinosaurAction {
    private Location loc;
    protected Lake waterSource;

    public DrinkWaterAction(Lake waterSource) {
        this.waterSource = waterSource;
    }

    @Override
    public String execute(Dinosaur dinosaur, GameMap map) {
        loc = map.locationOf(dinosaur);
        dinosaur.drink(1);
        waterSource.setWaterSips(waterSource.getWaterSips() - 1);
        return menuDescription(dinosaur);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + "at (" + loc.x() + ", " + loc.y() + ") drink water.";
    }
}
