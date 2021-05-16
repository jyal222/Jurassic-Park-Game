package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

import java.util.List;

public class CatchFishAction extends DinosaurAction{
    private List<Food> fishInLake;
    private Lake lake;

    /**
     * A constructor for catch fish  action
     * @param fishInLake
     * @param lake
     */
    public CatchFishAction(List<Food> fishInLake, Lake lake) {
        this.fishInLake = fishInLake;
        this.lake = lake;

    }

    /**
     * This method is to let dinosaur to catch fish.
     * @param dinosaur The dinosaur performing the action.
     * @param map game map
     * @return string printing a line dinosaur had caught fish
     */
    @Override
    public String execute(Dinosaur dinosaur, GameMap map) {
        dinosaur.setWaterLevel(dinosaur.getWaterLevel()+30);
        lake.removeAllFood(fishInLake);
        return menuDescription(dinosaur);
    }

    /**
     * This method is to print a line showing which dinosaur has catch fish.
     * @param actor The actor performing the action.
     * @return string
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + " catches " + fishInLake.size() + " fish!";
    }
}
