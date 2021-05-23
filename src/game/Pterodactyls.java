package game;

import edu.monash.fit2099.engine.*;

import static game.Capability.breed;

public class Pterodactyls extends Dinosaur implements Eatable {

    public static final String PTERODACTYLS = "pterodactyls";
    public static final int BABY_FOOD_LEVEL = 20;
    public static final int MAX_FOOD_LEVEL = 100;
    public static final int MAX_WATER_LEVEL = 100;
    public static final int MAX_FUEL = 30;

    private boolean isFlying = false;
    private int fuel = 0;

    /**
     * A constructor to set initial food level of Pterodactyls
     *
     * @param hitPoints the Actor's starting hit points
     */
    public Pterodactyls(int hitPoints) {
        super(PTERODACTYLS, 'p', hitPoints, MAX_FOOD_LEVEL, 60, MAX_WATER_LEVEL);
        super.pregnantThreshold = 10;
        super.eggHatchThreshold = 15;
        super.babyThreshold = 30;
        super.unconsciousThreshold = 20;
        super.deadThreshold = 20;
        super.hungryThreshold = 90;
        super.breedThreshold = 50;
        super.corpseFoodLevel = 30;
        super.eggEcoPoints = 100;
        super.thirstyThreshold = 80;
        super.waterLevelConsumed = 30;

        behaviourMap.put(Behaviour.Type.CatchFishBehaviour, new CatchFishBehaviour());
    }

    /**
     * A constructor to instantiate adult Pterodactyls
     */
    public Pterodactyls() {
        this(60); //50
        addCapability(breed);
        displayChar = 'P';
    }

    public Pterodactyls(String gender) {
        this(60);
        addCapability(breed);
        super.gender = gender;
        displayChar = 'P';
    }

    /**
     * This method is to check whether the food is eatable for pterodactyls. In this case, only fish and corpse are eatable.
     *
     * @param food
     * @return boolean depends on whether the food is eatable
     */
    @Override
    public boolean canEat(Eatable food) {
        return (food instanceof Fish || food instanceof Corpse);
    }

    /**
     * This method is to create a new eat action when the food in the location is eatable for pterodactyls.
     *
     * @param location of the pterodactyls
     * @return a new EatAction(foodList) if condition is met, null if location not valid or food is not eatable.
     */
    @Override
    public DinosaurAction getEatAction(Location location) {
        // Search for fish
        Ground ground = location.getGround();
        if (ground instanceof Lake) {
            Lake lake = (Lake) ground;
            for (Food food : lake.getFood()) {
                if (canEat(food)) {
                    return new EatAction(food, lake);
                }
            }
        }
        // Search for corpse
        for (Item item : location.getItems()) {
            if (item instanceof Eatable && canEat((Eatable) item)) {
                if (item instanceof Corpse) isFlying = false;
                return new EatAction((Food) item);
            }
        }

        return null;
    }

    /**
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (isFlying) fuel--;
        if (fuel <= 0) isFlying = false;
        if (map.locationOf(this).getGround() instanceof Tree) {
            System.out.println("Peri REFUEL");
            fuel = MAX_FUEL;
            isFlying = true;
        }

        Action action = super.playTurn(actions, lastAction, map, display);

        if (!(action instanceof DoNothingAction)) {
            return action;
        }

        action = behaviourMap.get(Behaviour.Type.CatchFishBehaviour).getAction(this, map);
        if (action != null) {
            return action;
        }

        return new DoNothingAction();
    }

    /**
     * To check if pterodactyls is able to enter the water
     *
     * @return boolean
     */
    @Override
    public boolean canEnterWater() {
        return isFlying;
    }

    /**
     * Increase the pterodactyls's food level if the food is eaten
     *
     * @param food food that is eaten
     */
    @Override
    public void eat(Eatable food) {
        if (food instanceof Corpse) {
            int level = Math.min(food.getFoodLevel(), 10);
            food.decreaseFoodLevel(level);
            heal(level);
        } else {
            super.eat(food);
        }
    }

    /**
     * To lay egg on tree.
     *
     * @param l location of dinosaur
     */
    @Override
    protected void layEgg(Location l) {
        pregnantTurns++;
        if (pregnantTurns >= pregnantThreshold) {
            if (l.getGround() instanceof Tree) {
                l.addItem(new Egg(this));
                pregnantTurns = 0;
                isPregnant = false;
            }
        }
    }

    /**
     * This method will create a Breed Action.
     * @param currentLoc
     * @return BreedAction
     */
    @Override
    public BreedAction getBreedAction(Location currentLoc) {
        if (currentLoc.getGround() instanceof Tree) {
            for (Exit exit : currentLoc.getExits()) {
                // if current dinosaur is on Tree
                if (exit.getDestination().getActor() instanceof Dinosaur && exit.getDestination().getGround() instanceof Tree) { // if adjacent location is tree
                    Dinosaur otherDinosaur = (Dinosaur) exit.getDestination().getActor();
                    if (this.canBreedWith(otherDinosaur)) {
                        System.out.println(this.gender + " " + this.name + " found a mate.");
                        return new BreedAction(otherDinosaur);
                    }
                }

            }
        }
        return null;
    }

    /**
     * To return true if pterodactyl is flying
     * @return boolean
     */
    @Override
    public boolean isFlying() {
        return isFlying;
    }

    /**
     * To get food level of pterodactyl
     * @return
     */
    @Override
    public int getFoodLevel() {
        return hitPoints;
    }

    /**
     * To decrease Food Level.
     * @param amount of food level
     */
    @Override
    public void decreaseFoodLevel(int amount) {

    }
}
