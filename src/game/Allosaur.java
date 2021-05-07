package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

public class Allosaur extends Dinosaur {

    public static final String NAME = Allosaur.class.getSimpleName();
    int babyFoodLevel = 20;
    private Behaviour behaviour;
    private List<Dinosaur> dinosaursAttackedList = new ArrayList<>();

    /**
     * Constructor of Allosaur class
     * All Allosaur are represented by an 'a' and have 100 hit points.
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
     *
     * @param l location of the dinosaur
     * @param g game map
     */
    public static void dinosaurTick(Allosaur a, Location l, GameMap g) {

    }

    /**
     * Figure out what to do next.
     * <p>
     * FIXME: Allosaur wanders around at random, or if no suitable MoveActions are available, it
     * just stands there.  That's boring.
     *
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

    @Override
    public boolean canEat(Eatable food) {
        boolean canEatEgg = true;
        if (food instanceof Egg) {
            Egg egg = (Egg) food;
            canEatEgg = !egg.getType().equals("allosaur");
        }
        return (food instanceof MealKit || (food instanceof Egg && canEatEgg) || food instanceof Dinosaur);
    }

    @Override
    public int getFoodLevel() {
        return 50;
    }

    public List<Dinosaur> getDinosaursAttackedList() {
        return dinosaursAttackedList;
    }

    public Action getAttackAction(Dinosaur dinosaur) {
        return new DinosaurAttackAction(dinosaur);
    }
}
