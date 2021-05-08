package game;

import edu.monash.fit2099.engine.Location;

/**
 * A fruit class the inherited from Food.
 */
public class Fruit extends Food {

    int rotTurn;

    /**
     * Constructor
     */
    public Fruit(int foodLevel) {
        super(foodLevel, 30, 10, "Fruit", 'f', true);
        this.rotTurn = 15;
        this.setPrice(30);
    }

    /**
     * This method is to tick all the fruits in the game map.
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

    /**
     * Returns the number of turns until the fruit expires.
     * Fruits on the ground for more than 15 turns will rot and disappear
     *
     * @return integer indicating how many turns it takes for the fruit to expire
     */
    public int getRotTurns() {
        return rotTurn;
    }

    /**
     * Increases by 1 every term and sets the number of times the fruit has stayed on the ground.
     *
     * @param rotTurn the new updated expiry turns for a current fruit
     */
    public void setRotTurns(int rotTurn) {
        this.rotTurn = rotTurn;
    }

}
