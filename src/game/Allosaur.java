package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A children class that inherited from Dinosaur class, which is a carnivore dinosaur.
 */
public class Allosaur extends Dinosaur {

    public static final String NAME = Allosaur.class.getSimpleName();
    int babyFoodLevel = 20;
    private Behaviour behaviour;
    private List<Dinosaur> dinosaursAttackedList = new ArrayList<>();

    /**
     * Constructor of Allosaur class
     * All Allosaurs are represented by an 'a' and have 100 hit points, hungry threshold of 90, breed threshold of 50 and dead threshold of 20.
     *
     * @param foodLevel initial food level of dinosaur
     */
    public Allosaur(int foodLevel) {
        super("allosaur", 'a', 100, 90, 50, 20);
        // set the gender of allosaur
        super.setGender(this.randomiseGender());
        super.setFoodLevel(foodLevel);
        super.setMaxFoodLevel(100);
        super.setUnconsciousTurns(0);
        super.setPregnantThreshold(20);
        super.setBabyThreshold(50);

        behaviourMap.put(Behaviour.Type.AttackBehaviour, new AttackBehaviour());
    }

    public Allosaur() {
        this(50);
    }


    /**
     * This method ticks all the allosaurs across the gamemap.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     * @return action to be performed.
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
     * @param location of the allosaur
     * @return a new EatAction(foodList) if condition is met, null if location not valid or food is not eatable.
     */
    @Override
    public EatAction getEatAction(Location location) {
        for (Exit exit : location.getExits()) {
            if (exit.getDestination().getActor() instanceof Dinosaur) {
                Dinosaur dinosaur = (Dinosaur) exit.getDestination().getActor();
                if (dinosaur.isDead) {
                    List<Eatable> foodList = new ArrayList<>();
                    foodList.add(dinosaur);
                    location.map().removeActor(dinosaur);
                    return new EatAction(foodList);
                }
            }
        }
        for (Item item : location.getItems()) {
            if (item instanceof Eatable && canEat((Eatable) item)) {
                List<Eatable> foodList = new ArrayList<>();
                foodList.add((Eatable) item);
                location.removeItem(item);
                return new EatAction(foodList);
            }
        }
        return null;
    }

    /**
     * This method is to check whether the food is eatable for allosaur. In this case, only egg, corpse and mealkit are eatable.
     * @param food to be checked
     * @return boolean depends on whether the food is eatable
     */
    @Override
    public boolean canEat(Eatable food) {
        boolean canEatEgg = true;
        if (food instanceof Egg) {
            Egg egg = (Egg) food;
            canEatEgg = !egg.getType().equals("allosaur");
        }
        return (food instanceof MealKit || (food instanceof Egg && canEatEgg) || food instanceof Dinosaur);
    }

    /**
     * To get food level of allosaur.
     * @return food level
     */
    @Override
    public int getFoodLevel() {
        return 50;
    }

    /**
     * This method will return a list of dinosaur that is attacked by the allosaur
     * @return list
     */
    public List<Dinosaur> getDinosaursAttackedList() {
        return dinosaursAttackedList;
    }

    /**
     * This method is to create a new dinosaur attack action.
     * @param dinosaur dinosaur to be attacked
     * @return a new DinosaurAttackAction(dinosaur)
     */
    public Action getAttackAction(Dinosaur dinosaur) {
        return new DinosaurAttackAction(dinosaur);
    }
}
