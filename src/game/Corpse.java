package game;

import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;

/**
 * A class represent corpse after dead of dinosaur.(could be eaten)
 */
public class Corpse extends Food{

    private Dinosaur dinosaur;

    /***
     * Constructor.
     *  @param dinosaur the dead dinosaur
     */
    public Corpse(Dinosaur dinosaur) {
        super(dinosaur.getCorpseFoodLevel(), 0, 0, "dead " + dinosaur, 'X', false);
        this.dinosaur = dinosaur;
    }

    /**
     * To tick all the dead dinosaur in the game map
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        dinosaur.setDeadTurns(dinosaur.getDeadTurns() + 1);
        if (dinosaur.getDeadTurns() >= dinosaur.getDeadThreshold()) {
            currentLocation.removeItem(this);
        }
    }

    /**
     * To get food level of corpse
     *
     * @return
     */
    @Override
    public int getFoodLevel() {
        return foodLevel;
    }

    /**
     * To decrease the food level of the corpse
     *
     * @param amount
     */
    @Override
    public void decreaseFoodLevel(int amount) {
        foodLevel = Math.max(foodLevel - amount, 0);
    }

    /**
     * To get the price of corpse. In this case, it is 0 as it cannot be sold
     *
     * @return int 0
     */
    @Override
    public int getPrice() {
        return 0;
    }


}
