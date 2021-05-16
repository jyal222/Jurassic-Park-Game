package edu.monash.fit2099.interfaces;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Location;

import java.util.List;

/**
 * This interface provides the ability to add methods to Ground, without modifying code in the engine,
 * or downcasting references in the game.   
 */

public interface GroundInterface {
    boolean canDrink();

    boolean canDinosaurStand();

    void setDinosaur(Actor actor);
}
