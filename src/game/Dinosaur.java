package game;

import edu.monash.fit2099.engine.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static game.Capability.breed;
import static game.Dinosaur.Stage.adult;

/**
 * An abstract class method to be inherited by each type of dinosaur.
 */
public abstract class Dinosaur extends Actor {

    // use hitPoints from Actor class to represent food level

    public static final String MALE = "male";
    public static final String FEMALE = "female";

    protected Map<Behaviour.Type, Behaviour> behaviourMap = new HashMap<>();
    protected Stage stage = adult; // default
    protected String gender;
    protected boolean isPregnant = false;

    protected int babyAge = 0;
    protected int deadTurns = 0;
    protected int unconsciousTurns = 0;
    protected int unconsciousTurnsDueToThirsty = 0;
    protected int pregnantTurns = 0;
    protected int attackTurns = 0;

    protected int pregnantThreshold; // max turns of pregnant before laying eggs
    protected int eggHatchThreshold; // max turns of egg before hatching into baby dinosaur
    protected int babyThreshold; // max turns of baby before turning into adult
    protected int unconsciousThreshold; // max turns of unconscious before die
    protected int deadThreshold; // max turns of death before disappearing from location
    protected int hungryThreshold; // hit point to become hungry
    protected int breedThreshold; // enough hit point to breed
    protected int corpseFoodLevel; // food level of the corpse when this dinosaur dies
    protected int eggEcoPoints; // eco points of this dinosaur's egg

    protected int waterLevel; // water level of the dinosaur
    protected int thirstyThreshold; // max turns to become thirsty
    protected int waterLevelConsumed; // water level consumed by each dinosaur for 1 sip of water
    protected int maxWaterLevel; // max water level of dinosaur

    private Rain rain = Rain.getInstance();


    /**
     * A dinosaur stage can only be baby or adult.
     */
    public enum Stage {
        baby, adult
    }

    /**
     * Constructor of Dinosaur class
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Dinosaur(String name, char displayChar, int hitPoints, int maxHitPoints, int waterLevel, int maxWaterLevel) {
        super(name, displayChar, hitPoints);
        super.maxHitPoints = maxHitPoints;
        // randomise the gender of dinosaur
        this.setGender(this.randomiseGender());
        this.maxWaterLevel = maxWaterLevel;
        this.waterLevel = waterLevel;
        behaviourMap.put(Behaviour.Type.WanderBehaviour, new WanderBehaviour());
        behaviourMap.put(Behaviour.Type.EatBehaviour, new EatBehaviour());
        behaviourMap.put(Behaviour.Type.BreedBehaviour, new BreedBehaviour());
        behaviourMap.put(Behaviour.Type.DrinkBehaviour, new DrinkBehaviour());
    }

    /**
     * To get the water level of dinosaur
     *
     * @return int waterLevel
     */
    public int getWaterLevel() {
        return waterLevel;
    }

    /**
     * To set the water level of dinosaur
     *
     * @param waterLevel
     */
    public void setWaterLevel(int waterLevel) {
        this.waterLevel = waterLevel;
    }

    /**
     * To get number of dead turns of dinosaur
     *
     * @return integer number of dead turns of dinosaur
     */
    public int getDeadTurns() {
        return deadTurns;
    }

    /**
     * To set number of dead turns of dinosaur
     *
     * @param deadTurns
     */
    public void setDeadTurns(int deadTurns) {
        this.deadTurns = deadTurns;
    }

    /**
     * @return a string shows male or female
     */
    public String randomiseGender() {
        Random rand = new Random();
        return rand.nextBoolean() ? MALE : FEMALE;
    }

    /**
     * To set the gender of the dinosaur
     *
     * @return string gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * To get the gender of the dinosaur
     *
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * To get stage of the dinosaur (baby, adult)
     *
     * @return Stage stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * To set stage of the dinosaur
     *
     * @param stage
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * To set a dinosaur to be pregnant
     *
     * @param pregnant
     */
    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }

    /**
     * To get number of turns after dinosaur being attacked
     *
     * @return integer
     */
    public int getAttackTurns() {
        return attackTurns;
    }

    /**
     * To set number of turns after dinosaur being attacked
     *
     * @param attackTurns
     */
    public void setAttackTurns(int attackTurns) {
        this.attackTurns = attackTurns;
    }

    /**
     * To get dead threshold of dinosaur
     *
     * @return int deadThreshold
     */
    public int getDeadThreshold() {
        return deadThreshold;
    }

    /**
     * To get corpse food level of dinosaur
     *
     * @return int corpseFoodLevel
     */
    public int getCorpseFoodLevel() {
        return corpseFoodLevel;
    }

    /**
     * To get eco points of each dinosaur's egg
     *
     * @return int eggEcoPoints
     */
    public int getEggEcoPoints() {
        return eggEcoPoints;
    }

    /**
     * To get the egg hatching threshold
     *
     * @return int eggHatchThreshold
     */
    public int getEggHatchThreshold() {
        return eggHatchThreshold;
    }

    /**
     * Return true if hit points less and equal than hungry threshold set, dinosaur is hungry
     *
     * @return boolean
     */
    public boolean isHungry() {
        return hitPoints <= hungryThreshold;
    }

    /**
     * Return true if water level less and equal than thirsty threshold set, dinosaur is thirsty
     *
     * @return boolean
     */
    public boolean isThirsty() {
        return waterLevel <= thirstyThreshold;
    }

    /**
     * To set a dinosaur to be dead
     */
    public void die(GameMap map) {
        // Set 'X' to represents dead dinosaur
        this.displayChar = 'X';
        this.hitPoints = 0;
        Item corpse = new Corpse(this);
        map.locationOf(this).addItem(corpse);
        map.removeActor(this);
    }

    /**
     * To check if a dinosaur can breed
     *
     * @param actor to be checked
     * @return boolean
     */
    public boolean canBreedWith(Actor actor) {
        if (actor instanceof Dinosaur) {
            Dinosaur dinosaur = (Dinosaur) actor;
            // check food level and pregnant and not baby
            return this.getClass() == dinosaur.getClass() && this.getGender() != dinosaur.getGender() && dinosaur.hasCapability(breed);
        }
        return false;
    }

    /**
     * To let the pregnant dinosaur lay egg if passed the pregnant turns
     *
     * @param l location of dinosaur
     */
    protected void layEgg(Location l) {
        pregnantTurns++;
        if (pregnantTurns >= pregnantThreshold) {
            l.addItem(new Egg(this));
            pregnantTurns = 0;
            isPregnant = false;
        }
    }

    /**
     * To set a baby dinosaur to adult after number of turns alive of a baby dinosaur is sufficient
     */
    protected void babyDinosaurGrows() { // todo display character
        babyAge++;
        if (babyAge >= babyThreshold) {
            this.setStage(Stage.adult);
            displayChar = Character.toUpperCase(displayChar);
        }
    }

    /**
     * To check if food is eatable
     *
     * @param food
     * @return
     */
    public abstract boolean canEat(Eatable food);

    public boolean isFlying() {
        return false;
    }

    /**
     * Increase the dinosaur's food level if the food is eaten
     *
     * @param food food that is eaten
     */
    public void eat(Eatable food) {
        food.decreaseFoodLevel(food.getFoodLevel());
        heal(food.getFoodLevel());
    }

    /**
     * Dinosaur consumes water until its maximum water level
     *
     * @param sip
     */
    public void drink(int sip) {
        waterLevel = Math.min(waterLevel + (waterLevelConsumed * sip), maxWaterLevel);
    }

    /**
     * Return true if dinosaur is conscious.
     *
     * @return boolean
     */
    @Override
    public boolean isConscious() {
        return waterLevel > 0 || hitPoints > 0;
    }

    /**
     * Returns a EatAction that will allow dinosaur to eat.
     *
     * @param location current location
     * @return EatAction
     */
    public abstract DinosaurAction getEatAction(Location location);

    /**
     * Returns a DrinkAction that will allow dinosaur to drink.
     *
     * @param lake
     * @return
     */
    public DinosaurAction getDrinkAction(Lake lake) {
        return new DrinkWaterAction(lake);
    }

    /**
     * Returns a BreedAction that will allow dinosaur to breed.
     *
     * @return BreedAction
     */
    public BreedAction getBreedAction(Location currentLct) {
        for (Exit exit : currentLct.getExits()) {
            if (exit.getDestination().getActor() instanceof Dinosaur) {
                Dinosaur otherDinosaur = (Dinosaur) exit.getDestination().getActor();
                if (this.canBreedWith(otherDinosaur)) {
                    System.out.println(this.gender + " " + this.name + " found a mate.");
                    return new BreedAction(otherDinosaur);
                }
            }
        }

        return null;
    }

    /**
     * This method is to check which behaviour should be performed by dinosaur at current play turn.
     *
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return each action based on the behaviour
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        // minus 1 food level
        hitPoints--;
        // minus 1 water level
        waterLevel--;

        if (!isConscious()) {

            if (waterLevel <= 0) {
                unconsciousTurnsDueToThirsty++;
                if (rain.isRaining()) {
                    waterLevel = 10;
                    unconsciousTurnsDueToThirsty = 0;
                } else {
                    if (unconsciousTurnsDueToThirsty >= 15) {
                        die(map);
                    }
                }
            }
            if (hitPoints <= 0) {
                unconsciousTurns++;
                if (unconsciousTurns >= unconsciousThreshold) {
                    die(map);
                }
            }
            return new DoNothingAction();
        }


        // baby grow
        if (stage == Stage.baby) {
            babyDinosaurGrows();
        }

        // lay eggs
        if (isPregnant) {
            layEgg(map.locationOf(this));
        }

        if (hitPoints > breedThreshold && !isPregnant && stage == Stage.adult && isConscious()) {
            addCapability(breed);
        } else {
            removeCapability(breed);
        }

        Action action;
        action = behaviourMap.get(Behaviour.Type.BreedBehaviour).getAction(this, map);

        if (action == null) {
            action = behaviourMap.get(Behaviour.Type.EatBehaviour).getAction(this, map);
        }

        if (action == null) {
            action = behaviourMap.get(Behaviour.Type.DrinkBehaviour).getAction(this, map);
        }

        if (action != null) {
            return action;
        }

        return new DoNothingAction();
    }

    /**
     * Returns a collection of the Actions that the otherActor can do to the current Actor.
     *
     * @param otherActor the Actor that might be performing attack
     * @param direction  String representing the direction of the other Actor
     * @param map        current GameMap
     * @return actions
     */
    @Override
    public Actions getAllowableActions(Actor otherActor, String direction, GameMap map) {
        Actions actions = new Actions();
        if (otherActor instanceof Player) {
            for (Item item : otherActor.getInventory()) {
                if (item instanceof Eatable && canEat((Eatable) item)) {
                    actions.add(new FeedDinosaurAction((Food) item, this, map.locationOf(this)));
                }
            }
        }
        return actions;
    }

    /**
     * To get name of dinosaur
     *
     * @return string
     */
    @Override
    public String toString() {
        return super.name;
    }


}
