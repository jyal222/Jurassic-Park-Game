package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.List;

public class CatchFishAction extends DinosaurAction{
    private List<Food> fishInLake;
    private Lake lake;

    public CatchFishAction(List<Food> fishInLake, Lake lake) {
        this.fishInLake = fishInLake;
        this.lake = lake;

    }

    @Override
    public String execute(Dinosaur dinosaur, GameMap map) {
        dinosaur.setWaterLevel(dinosaur.getWaterLevel()+30);
        lake.removeAllFood(fishInLake);
        return menuDescription(dinosaur);
    }

    @Override
    public String menuDescription(Actor actor) {
        return actor + " catches " + fishInLake.size() + " fish!";
    }
}
