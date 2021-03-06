package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A children class that inherited from Dinosaur class, which is a carnivore dinosaur.
 */
public class Allosaur extends Dinosaur {

    public static final String ALLOSAUR = "allosaur";
    public static final int BABY_FOOD_LEVEL = 20;
    public static final int MAX_FOOD_LEVEL = 100;
    public static final int MAX_WATER_LEVEL = 100;

    private List<Dinosaur> attackedDinosaursList = new ArrayList<>();

    /**
     * Constructor of Allosaur class
     * All Allosaurs are represented by an 'a' and have 100 hit points, hungry threshold of 90, breed threshold of 50 and dead threshold of 20.
     *
     * @param hitPoints starting hit points of Allosaur
     */
    public Allosaur(int hitPoints) {
        super(ALLOSAUR, 'a', hitPoints, MAX_FOOD_LEVEL, 60, MAX_WATER_LEVEL);
        super.pregnantThreshold = 20;
        super.eggHatchThreshold = 50;
        super.babyThreshold = 50;
        super.unconsciousThreshold = 20;
        super.deadThreshold = 20;
        super.hungryThreshold = 90;
        super.breedThreshold = 50;
        super.corpseFoodLevel = 50;
        super.eggEcoPoints = 1000;
        super.thirstyThreshold = 80;
        super.waterLevelConsumed = 30;

        behaviourMap.put(Behaviour.Type.AttackBehaviour, new AttackBehaviour());
    }

    /**
     * A constructor to instantiate adult Allosaur
     */
    public Allosaur() {
        this(50);
        displayChar = 'A';
    }


    /**
     * This method ticks all the allosaurs across the gamemap.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return action to be performed.
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        Action action = behaviourMap.get(Behaviour.Type.AttackBehaviour).getAction(this, map);
        if (action != null) {
            return action;
        }
        return super.playTurn(actions, lastAction, map, display);
    }

    /**
     * This method is to create a new eat action when the food in the location is eatable for allosaur.
     *
     * @param location of the allosaur
     * @return a new EatAction(foodList) if condition is met, null if location not valid or food is not eatable.
     */
    @Override
    public DinosaurAction getEatAction(Location location) {
        // Search for dead corpse or egg
        for (Item item : location.getItems()) {
            if (item instanceof Eatable && canEat((Eatable) item)) {
                return new EatAction((Food) item);
            }
        }
        for (Exit exit : location.getExits()) {
            // Search for fish
            Ground ground = exit.getDestination().getGround();
            if (ground instanceof Lake) {
                Producible source = (Producible) ground;
                for (Food food : source.getFood()) {
                    if (canEat(food)) {
                        return new EatAction(food, source);
                    }
                }
            }
            // Search for Pterodactyls
            Actor actor = exit.getDestination().getActor();
            if (actor instanceof Eatable && canEat((Eatable) actor)) {
                return new EatActorAction(actor);
            }
        }

        return null;
    }

    /**
     * This method is to check whether the food is eatable for allosaur. In this case, only egg, corpse and mealkit are eatable.
     *
     * @param food to be checked
     * @return boolean depends on whether the food is eatable
     */
    @Override
    public boolean canEat(Eatable food) {
        boolean canEatEgg = true;
        boolean canEatPtero = true;

        if (food instanceof Egg) {
            Egg egg = (Egg) food;
            canEatEgg = !(egg.getDinosaur() instanceof Allosaur) && !egg.isOnTree();
        }
        if (food instanceof Pterodactyls) {
            canEatPtero = !((Pterodactyls) food).isFlying();
        }
        return food instanceof CarnivoreMealKit ||
                food instanceof Corpse ||
                food instanceof Fish ||
                (food instanceof Egg && canEatEgg) ||
                (food instanceof Pterodactyls && canEatPtero);
    }

    /**
     * This method will return a list of dinosaur that is attacked by the allosaur
     *
     * @return list
     */
    public List<Dinosaur> getAttackedDinosaursList() {
        return attackedDinosaursList;
    }

    /**
     * This method is to create a new dinosaur attack action.
     *
     * @param dinosaur dinosaur to be attacked
     * @return a new DinosaurAttackAction(dinosaur)
     */
    public Action getAttackAction(Dinosaur dinosaur) {
        return new DinosaurAttackAction(dinosaur);
    }

    /**
     * To check if allosaur is able to enter the water
     *
     * @return boolean
     */
    @Override
    public boolean canEnterWater() {
        return false;
    }
}
