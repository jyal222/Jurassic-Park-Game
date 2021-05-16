package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;

public class Wall extends Ground {

    /**
     * Constructor for Wall
     */
    public Wall() {
        super('#');
    }

    /**
     * To check if the location can be entered by actor
     *
     * @param actor the Actor to check
     * @return boolean
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
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
     * Check if the location can be drink
     *
     * @return boolean
     */
    @Override
    public boolean canDrink() {
        return false;
    }

    /**
     * Check if the dinosaur can stand on the location
     *
     * @return boolean
     */
    @Override
    public boolean canDinosaurStand() {
        return false;
    }

    /**
     * To set the dinosaur
     *
     * @param actor
     */
    @Override
    public void setDinosaur(Actor actor) {

    }
}
