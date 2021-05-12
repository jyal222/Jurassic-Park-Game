package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class DrinkWaterAction extends DinosaurAction {
    private Water water;
    private Location loc;


    @Override
    public String execute(Dinosaur dinosaur, GameMap map) {
        loc = map.locationOf(dinosaur);

//        if (source != null) {
//            // eat fruits from ground
//            dinosaur.eat(food);
//            loc.removeItem(food);
//            return menuDescription(dinosaur);
//
//    }
        return null;
    }

    @Override
    public String menuDescription(Actor actor) {
        return null;
    }
}
