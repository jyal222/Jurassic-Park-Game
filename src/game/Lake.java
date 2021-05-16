package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lake extends Ground implements Producible{

    private int waterSips = 25;
    private static final int MAX_FISH_NUMBER = 25;
    private List<Food> fishes = new ArrayList<>() {
        {
            add(new Fish());
            add(new Fish());
            add(new Fish());
            add(new Fish());
            add(new Fish());
        }
    };
    private Rain rain = Rain.getInstance();

    /**
     *
     */
    public Lake() {
        super('~');
    }


    /**
     * @param actor the Actor to check
     * @return boolean
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.canEnterWater();

    }

    /**
     * @return
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    public void tick(Location location) {
        super.tick(location);
        Random random = new Random();

        if (rain.isRaining()) {
            waterSips = Math.max(waterSips + (int) (rain.getRainFall() * 20), 25);
        }
        // 60% for a new fish to be born
        if (random.nextInt(100) + 1 <= 60) {
            if (fishes.size() <= MAX_FISH_NUMBER) {
                fishes.add(new Fish());
            }
        }
    }


    public int getWaterSips() {
        return waterSips;
    }

    public void setWaterSips(int waterSips) {
        this.waterSips = waterSips;
    }

    @Override
    public boolean canDrink() {
        return waterSips > 0;
    }

    @Override
    public List<Food> getFood() {
        return fishes;
    }

    @Override
    public boolean removeFood(Food foodToRemove) {
        return fishes.remove(foodToRemove);
    }

    @Override
    public boolean removeAllFood(List<Food> foodsToRemove) {
        return fishes.removeAll(foodsToRemove);
    }
}
