package game;

import edu.monash.fit2099.engine.Ground;

/**
 * A class that represents the floor inside a building.
 */
public class Floor extends Ground {

    public Floor() {
        super('_');
    }

    /**
     * To check if the location can drink
     *
     * @return boolean
     */
    @Override
    public boolean canDrink() {
        return false;
    }

}
