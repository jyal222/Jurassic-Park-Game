package game;

import edu.monash.fit2099.engine.*;

import java.util.Random;

import static game.Capability.breed;

/**
 * A children class that inherited from Dinosaur class, which is a herbivore dinosaur.
 */
public class Brachiosaur extends Dinosaur {

    public static final String BRACHIOSAUR = "brachiosaur";
    public static final int BABY_FOOD_LEVEL = 10;
    public static final int MAX_FOOD_LEVEL = 160;
    public static final int MAX_WATER_LEVEL = 200;

    /**
     * Constructor of Brachiosaur class
     * All Brachiosaurs are represented by a 'b' and have 100 hit points, hungry threshold of 140, breed threshold of 70 and dead threshold of 40.
     *
     * @param hitPoints starting hit points of Brachiosaur
     */
    public Brachiosaur(int hitPoints) {
        super(BRACHIOSAUR, 'b', hitPoints, MAX_FOOD_LEVEL, 140, MAX_WATER_LEVEL);
        super.pregnantThreshold = 30;
        super.eggHatchThreshold = 15;
        super.babyThreshold = 50;
        super.unconsciousThreshold = 15;
        super.deadThreshold = 40;
        super.hungryThreshold = 140;
        super.breedThreshold = 70;
        super.corpseFoodLevel = 100;
        super.eggEcoPoints = 1000;
        super.thirstyThreshold = 150;
        super.waterLevelConsumed = 80;
    }

    /**
     * Constructor for adult Brachiosaur class
     */
    public Brachiosaur() {
        this(100);
        addCapability(breed);
        displayChar = 'B';
    }
    /**
     * Constructor for adult Brachiosaur class
     */
    public Brachiosaur(String gender) {
        this(100);
        addCapability(breed);
        super.gender = gender;
        displayChar = 'B';
    }

    /**
     * Brachiosaur's every play turn
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // If Brachiosaur stepped on the Bush, 50% to kill bush
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
    public DinosaurAction getEatAction(Location location) {
        Ground ground = location.getGround();
        if (ground instanceof Tree) {
            Tree tree = (Tree) ground;
            return new EatManyAction(tree);
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

    /**
     * To check if brachiosaur is able to enter the water
     *
     * @return boolean
     */
    @Override
    public boolean canEnterWater() {
        return true;
    }
}

