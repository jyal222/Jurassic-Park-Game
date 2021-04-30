package game;

import edu.monash.fit2099.engine.Food;

public class Fruit extends Food {
    int rotTurn;

    /**
     *
     */
    public Fruit() {
        super(20, 30, 10, "Fruit", 'f', true);
        this.rotTurn = 15;
        this.setPrice(30);
    }

    /**
     * Returns the number of turns until the fruit expires.
     * Fruits on the ground for more than 15 turns will rot and disappear
     * @return integer indicating how many turns it takes for the fruit to expire
     */
    public int getRotTurns() { return rotTurn; }

    /**
     * Increases by 1 every term and sets the number of times the fruit has stayed on the ground.
     * @param rotTurn the new updated expiry turns for a current fruit
     */
    public void setRotTurns(int rotTurn) { this.rotTurn = rotTurn; }

}
