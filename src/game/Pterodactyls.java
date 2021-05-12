package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Random;

public class Pterodactyls extends Dinosaur{

    public static final String PTERODACTYLS = "pterodactyls";
    public static final int BABY_FOOD_LEVEL = 20;
    public static final int MAX_FOOD_LEVEL = 100;

    /**
     * Constructor of Dinosaur class
     *
     * @param hitPoints    the Actor's starting hit points

     */
    public Pterodactyls(int hitPoints) {
        super(PTERODACTYLS, 'p', hitPoints, 100);
        super.pregnantThreshold = 10;
        super.eggHatchThreshold = 15;
        super.babyThreshold = 30;
        super.unconsciousThreshold = 20;
        super.deadThreshold = 20;
        super.hungryThreshold = 90;
        super.breedThreshold = 50;
        super.corpseFoodLevel = 30;
        super.eggEcoPoints = 100;

    }

    /**
     * A constructor to set initial food level of stegosaur
     */
    public Pterodactyls() {
        this(50);
    }

    @Override
    public boolean canEat(Eatable food) {
        return (food instanceof Fish || food instanceof Corpse);
    }

    @Override
    public DinosaurAction getEatAction(Location location) {

        Ground ground = location.getGround();
        if (ground instanceof Lake) {
            for (Item item : location.getItems()) {
                if (item instanceof Eatable && canEat((Eatable) item)) {
                    return new EatAction((Food) item);
                }
            }
        }

        else {
            // Search for dead corpse
            for (Item item : location.getItems()) {
                if (item instanceof Eatable && canEat((Eatable) item)) {
                    return new EatAction((Food) item);
                }
            }
        }
        return null;
    }

    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        Location current = map.locationOf(this);
        return actions;
    }

    /**
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {

        Location location = map.locationOf(this);


        return super.playTurn(actions, lastAction, map, display);
    }
}
