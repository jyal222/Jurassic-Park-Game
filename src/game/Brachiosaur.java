package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A children class that inherited from Dinosaur class, which is a herbivore dinosaur.
 */
public class Brachiosaur extends Dinosaur {

    public static final String BRACHIOSAUR = "brachiosaur";
    public static final int BABY_FOOD_LEVEL = 10;

    /**
     * Constructor of Brachiosaur class
     * All Brachiosaurs are represented by a 'b' and have 100 hit points, hungry threshold of 140, breed threshold of 70 and dead threshold of 40.
     *
     * @param hitPoints starting hit points of Brachiosaur
     */
    public Brachiosaur(int hitPoints) {
        super(BRACHIOSAUR, 'b', hitPoints, 160);
        super.unconsciousThreshold = 15;
        super.pregnantThreshold = 3; // todo 30
        super.babyThreshold = 50;
        super.hungryThreshold = 140;
        super.breedThreshold = 70;
        super.deadThreshold = 40;
        super.corpseFoodLevel = 100;
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
     *
     * @param location of the brachiosaur
     * @return a new EatAction(foodList) if condition is met, null if location not valid or food is not eatable.
     */
    @Override
    public EatAction getEatAction(Location location) {
        Ground ground = location.getGround();
        if (ground instanceof Tree) {
            Tree tree = (Tree) ground;
            List<Eatable> foodList = new ArrayList<>();
            for (Eatable fruit : tree.getFruits()) {
                foodList.add(fruit);
            }
            tree.getFruits().removeAll(foodList);
            return new EatAction(foodList);
        }
        return null;
    }

    /**
     * This method is to check whether the food is eatable for brachiosaur. In this case, only fruit and mealkit are eatable.
     *
     * @param food to be checked
     * @return boolean depends on whether the food is eatable
     */
    @Override
    public boolean canEat(Eatable food) {
        return (food instanceof Fruit || food instanceof VegetarianMealKit);
    }

}

