package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lake extends Ground {

    private int waterSips = 25;
    private List<Food> fishes = new ArrayList<>(5);
    private int rainInterval = 0;

    /**
     *
     */
    public Lake() {
        super('~');
    }

    /**
     * Returns an Action list.
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
//        Actions actions = new Actions();
//        if (location.map().locationOf(actor) == location) {
//            actions.add(new PickFruitAction(this));
//        }
//        return actions;
        return null;
    }

    /**
     *
     * @param actor the Actor to check
     * @return
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     *
     * @return
     */
    @Override
    public boolean blocksThrownObjects() {
        return true;
    }

    public void tick(Location location) {
        super.tick(location);

        rainInterval++;
        Random random = new Random();

        if (rainInterval == 10) {
            if (random.nextInt(100) + 1 <= 20) {
                // todo rain
            }
        }

        // 60% for a new fish to be born
        if (random.nextInt(100) + 1 <= 60) {
            if (fishes.size() <= 25) {
                fishes.add(new Fish(5));
            }
        }

    }

    public int getWaterSips() {
        return waterSips;
    }

    public void setWaterSips(int waterSips) {
        this.waterSips = waterSips;
    }
}
