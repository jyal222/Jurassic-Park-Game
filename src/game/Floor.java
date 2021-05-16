package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.List;

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

    /**
     * To check if the dinosaur can stand on the location
     *
     * @return boolean
     */
    @Override
    public boolean canDinosaurStand() {
        return false;
    }

    /**
     * To set dinosaur
     *
     * @param actor
     */
    @Override
    public void setDinosaur(Actor actor) {

    }
}
