package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CatchFishBehaviour extends DinosaurBehaviour {
    @Override
    public Action getAction(Dinosaur dinosaur, GameMap map) {
        Location loc = map.locationOf(dinosaur);
        if (loc.getGround() instanceof Lake){
            Lake lake = (Lake)loc.getGround();
            int foodSize = lake.getFood().size();
            Random random = new Random();
            int number = Math.min(random.nextInt(3), foodSize);
            List<Food> fishInLake = new ArrayList<>();
            for (int i = 0; i<= number; i++){
                fishInLake.add(lake.getFood().get(i));
            }
            return new CatchFishAction(fishInLake, lake);
            }
        return null;
        }
}
