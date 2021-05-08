package game;

import edu.monash.fit2099.engine.Location;

/**
 * A fruit class the inherited from Food.
 */
public class Fruit extends Food {

    int rotTurn;

    /**
     * Constructor
     * @param foodLevel food level
     */
    public Fruit(int foodLevel) {
        super(foodLevel, 30, 10, "Fruit", 'f', true);
        this.rotTurn = 15;
        this.setPrice(30);
    }

    /**
     * This method is to tick all the fruits in the game map.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        rotTurn--;
        if (rotTurn == 0) {
            currentLocation.removeItem(this);
        }
    }

}
