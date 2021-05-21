package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lake extends Ground implements Producible {

    private int waterSips = 25;
    private static final int MAX_FISH_NUMBER = 25;

    // game begins with 5 fish
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
     * Constructor for Lake.
     */
    public Lake() {
        super('~');
    }

    /**
     * Check if the actor can enter the location
     *
     * @param actor the Actor to check
     * @return boolean
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return actor.canEnterWater();

    }

    /**
     * Override this to implement terrain that blocks thrown objects but not movement, or vice versa
     *
     * @return true
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    /**
     * This method is to tick Lake.
     *
     * @param location The location of the Ground
     */
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

    /**
     * To get water sips of lake
     *
     * @return int waterSips
     */
    public int getWaterSips() {
        return waterSips;
    }

    /**
     * To set water sips of lake
     *
     * @param waterSips
     */
    public void setWaterSips(int waterSips) {
        this.waterSips = waterSips;
    }

    /**
     * Return true if water sips more than 0
     *
     * @return boolean
     */
    @Override
    public boolean canDrink() {
        return waterSips > 0;
    }

    /**
     * To get fishes in a lake
     *
     * @return list of fish
     */
    @Override
    public List<Food> getFood() {
        return fishes;
    }

    /**
     * To remove food (fish) from the list
     *
     * @param foodToRemove
     * @return boolean
     */
    @Override
    public boolean removeFood(Food foodToRemove) {
        return fishes.remove(foodToRemove);
    }

    /**
     * To remove all the fishes in the list
     *
     * @param foodsToRemove
     * @return boolean
     */
    @Override
    public boolean removeAllFood(List<Food> foodsToRemove) {
        return fishes.removeAll(foodsToRemove);
    }

}
