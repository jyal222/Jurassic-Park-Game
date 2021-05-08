package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static game.Capability.breed;
/**
 * A children class that inherited from Dinosaur class, which is a herbivore dinosaur.
 */
public class Brachiosaur extends Dinosaur {

    public static final String NAME = Brachiosaur.class.getSimpleName();
    private Behaviour behaviour;

    /**
     * Constructor of Brachiosaur class
     * All Brachiosaurs are represented by a 'b' and have 100 hit points, hungry threshold of 140, breed threshold of 70 and dead threshold of 40.
     *
     * @param foodLevel
     */
    public Brachiosaur(int foodLevel) {
        super("brachiosaur", 'b', 100, 140, 70, 40);
        // get the gender of brachiosaur
        super.setGender(this.randomiseGender());
        super.setFoodLevel(foodLevel);
        super.setMaxFoodLevel(160);
        super.setUnconsciousTurns(15);
        super.setPregnantThreshold(30);
        super.setBabyThreshold(50);
    }

    public Brachiosaur() {
        this(100);
    }

    /**
     * Figure out what to do next.
     * <p>
     * FIXME: Brachiosaur wanders around at random, or if no suitable MoveActions are available, it
     * just stands there.  That's boring.
     *
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // If Brachiosaur step on the Bush, 50% to kill bush
        Location location = map.locationOf(this);
        if (location.getGround() instanceof Bush) {
            Random random = new Random();
            if (random.nextInt(100) + 1 <= 50) {
                location.setGround(new Dirt());
                System.out.println(name + " at (" + location.x() + ", " + location.y() + ") steps on bush");
            }
        }

        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * This method is to create a new eat action when the food in the location is eatable for brachiosaur.
     * @param location of the brachiosaur
     * @return a new EatAction(foodList) if condition is met, null if location not valid or food is not eatable.
     */
    @Override
    public EatAction getEatAction(Location location) {
        Ground ground = location.getGround();
        if (ground instanceof Tree) {
            Tree tree = (Tree) ground;
            List<Eatable> foodList = new ArrayList<>();
            for (Fruit fruit : tree.getFruits()) {
                foodList.add(fruit);
            }
            tree.getFruits().removeAll(foodList);
            return new EatAction(foodList);
        }
        return null;
    }

    /**
     * This method is to check whether the food is eatable for brachiosaur. In this case, only fruit and mealkit are eatable.
     * @param food to be checked
     * @return boolean depends on whether the food is eatable
     */
    @Override
    public boolean canEat(Eatable food) {
        return (food instanceof Fruit || food instanceof MealKit);
    }

    /**
     * To get food level of brachiosaur.
     * @return food level
     */
    @Override
    public int getFoodLevel() {
        return 50;
    }
}

