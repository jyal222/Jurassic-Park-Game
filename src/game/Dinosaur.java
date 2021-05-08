package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import static game.Capability.breed;
import static game.Dinosaur.Stage.adult;

public abstract class Dinosaur extends Actor implements Eatable {

    private Behaviour behaviour;
    private GameMap map;
    protected Map<Behaviour.Type, Behaviour> behaviourMap = new HashMap<>();
    protected int foodLevel;
    protected int maxFoodLevel;
    protected Stage stage = adult; // default
    protected int numTurnsAlive = 0;
    protected int unconsciousTurns;
    protected boolean isUnconscious = false;
    protected int deadTurns = 0;
    protected boolean isDead = false;
    protected boolean isAttacked = false;
    protected String gender;
    protected int pregnantTurns = 0;
    protected int attackTurns = 0;
    protected boolean isPregnant = false;
    protected int pregnantThreshold;
    protected int babyThreshold;

    protected final int hungryThreshold;
    protected final int deadThreshold;
    protected final int breedThreshold;

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
    public Dinosaur(String name, char displayChar, int hitPoints, int hungryThreshold, int breedThreshold, int deadThreshold) {
        super(name, displayChar, hitPoints);
        this.hungryThreshold = hungryThreshold;
        this.breedThreshold = breedThreshold;
        this.deadThreshold = deadThreshold;
    }

    /**
     * To get the food level of the dinosaur
     *
     * @return integer food level
     */
    public int getFoodLevel() {
        return foodLevel;
    }

    /**
     * To set the food level of the dinosaur
     *
     * @param foodLevel food level of the dinosaur
     */
    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    /**
     * To get the maximum food level of the dinosaur
     *
     * @return max food level
     */
    public int getMaxFoodLevel() {
        return maxFoodLevel;
    }

    /**
     * To set the maximum food level of the dinosaur
     *
     * @param maxFoodLevel maximum food level of the dinosaur
     */
    public void setMaxFoodLevel(int maxFoodLevel) {
        this.maxFoodLevel = maxFoodLevel;
    }

    /**
     * To get the number of unconscious turns of the dinosaur
     *
     * @return integer unconscious Turns
     */
    public int getUnconsciousTurns() {
        return unconsciousTurns;
    }

    /**
     * To set the number of unconscious turns of the dinosaur
     *
     * @param unconsciousTurns
     */
    public void setUnconsciousTurns(int unconsciousTurns) {
        this.unconsciousTurns = unconsciousTurns;
    }

    /**
     * To indicate whether a dinosaur is unconscious
     *
     * @return boolean
     */
    public boolean isUnconscious() {
        return isUnconscious;
    }

    /**
     * To set the dinosaur's unconsciousness
     *
     * @param unconscious a boolean indicating whether dinosaur is unconscious
     */
    public void setUnconscious(boolean unconscious) {
        isUnconscious = unconscious;
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
     * To indicate where a dinosaur is dead
     *
     * @return boolean
     */
    public boolean isDead() {
        if (this.hitPoints <= 0) {
            this.setDead(true);
        }
        return isDead;
    }

    /**
     * To set a dinosaur to be dead
     *
     * @param dead
     */
    public void setDead(boolean dead) {
        isDead = dead;
        this.hitPoints = 0;
        // Set 'X' to represents dead dinosaur
        this.displayChar = 'X';
    }

    /**
     * @return a string shows male or female
     */
    public String randomiseGender() {
        Random rand = new Random();
        int number = rand.nextInt(2) + 1;
        return number == 1 ? "male" : "female";

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
     * To get number of turns of baby dinosaur alive, to decide when it will be grown as an adult
     *
     * @return integer
     */
    public int getNumTurnsAlive() {
        return numTurnsAlive;
    }

    /**
     * To set number of turns of baby dinosaur alive, to decide when it will be grown as an adult
     *
     * @param numTurnsAlive
     */
    public void setNumTurnsAlive(int numTurnsAlive) {
        this.numTurnsAlive = numTurnsAlive;
    }

    /**
     * To get number of turns of a dinosaur since pregnant
     *
     * @return integer
     */
    public int getPregnantTurns() {
        return pregnantTurns;
    }

    /**
     * To set number of turns of a dinosaur since pregnant
     *
     * @param pregnantTurns
     */
    public void setPregnantTurns(int pregnantTurns) {
        this.pregnantTurns = pregnantTurns;
    }

    /**
     * To indicate whether a dinosaur is pregnant
     *
     * @return boolean
     */
    public boolean isPregnant() {
        return isPregnant;
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
     * An abstract method to be override by children class.
     *
     * @param l location of the dinosaur
     * @param g game map
     */
    public static void tick(Location l, GameMap g) {
    }

    public abstract EatAction getEatAction(Location location);

    public abstract boolean canEat(Eatable food);

    public void eat(Eatable food) {
        foodLevel += food.getFoodLevel();
    }

    /**
     *
     * @return
     */
    public BreedAction getBreedAction() {
        return new BreedAction(this);
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
     * To decide whether a dinosaur is being attacked
     *
     * @return boolean
     */
    public boolean isAttacked() {
        return isAttacked;
    }

    /**
     * To indicate whether a dinosaur is being attacked
     *
     * @param attacked
     */
    public void setAttacked(boolean attacked) {
        isAttacked = attacked;
    }

    public boolean canBreed(Dinosaur dinosaur) {
        // TODO check food level and pregnant and not baby
        return this.getClass() == dinosaur.getClass() && this.getGender() != dinosaur.getGender() && this.hasCapability(breed) && dinosaur.hasCapability(breed);
    }

    public int getPregnantThreshold() {
        return pregnantThreshold;
    }

    public void setPregnantThreshold(int pregnantThreshold) {
        this.pregnantThreshold = pregnantThreshold;
    }

    public int getBabyThreshold() {
        return babyThreshold;
    }

    public void setBabyThreshold(int babyThreshold) {
        this.babyThreshold = babyThreshold;
    }

    /**
     * To let the pregnant dinosaur lay egg if passed the pregnant turns
     *
     * @param l location of dinosaur
     */
    protected void layEgg(Location l) {
        pregnantTurns++;
        if (pregnantTurns >= pregnantThreshold) {
            l.addItem(new Egg(this.getClass().getSimpleName()));
        }
    }

    /**
     * To set a baby dinosaur to adult after number of turns alive of a baby dinosaur is sufficient
     */
    protected void babyDinosaurGrows() {
        numTurnsAlive++;
        if (numTurnsAlive >= babyThreshold) {
            this.setStage(Stage.adult);
        }
    }

    /**
     * This method is to check which behaviour should be performed by dinosaur at current play turn.
     * @param actions    collection of possible Actions for this Actor
     * @param lastAction The Action this Actor took last turn. Can do interesting things in conjunction with Action.getNextAction()
     * @param map        the map containing the Actor
     * @param display    the I/O object to which messages may be written
     * @return each action based on the behaviour
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        if (foodLevel <= 0) {
            setUnconscious(true);
            unconsciousTurns++;
            if (unconsciousTurns >= 20) {
                deadTurns++;
                setDead(true);

                // Corpse of dead stegosaur still remains for 20 turns
                if (deadTurns >= deadThreshold) {
                    map.removeActor(this);
                }
            }
            return new DoNothingAction();
        }

        // minus 1 food level
        foodLevel--;

        // baby grow
        if (stage == Stage.baby) {
            babyDinosaurGrows();
        }

        // lay eggs
        if (isPregnant) {
            layEgg(map.locationOf(this));
        }

        // TODO update capabilities
        if (foodLevel > breedThreshold && !isPregnant && stage == Stage.adult && !isUnconscious) {
            addCapability(breed);
        } else {
            removeCapability(breed);
        }

        Action action = null;
        if (getFoodLevel() <= hungryThreshold) {
            System.out.println(name + " at (" + map.locationOf(this).x() + ", " + map.locationOf(this).y() + ") is getting hungry!");
            action = behaviourMap.get(Behaviour.Type.EatBehaviour).getAction(this, map);
        }

        if (action == null) {
            action = behaviourMap.get(Behaviour.Type.WanderBehaviour).getAction(this, map);
        }

        if (action == null) {
            action = behaviourMap.get(Behaviour.Type.WanderBehaviour).getAction(this, map);
        }

        if (action != null)
            return action;

        return new DoNothingAction();
    }
}
