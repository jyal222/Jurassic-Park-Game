package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Random;

public abstract class Dinosaur extends Actor {

    private Behaviour behaviour;
    private GameMap map;
    private int foodLevel;
    private int babyFoodLevel;
    private int maxFoodLevel;
    private String stage = "adult"; // default
    private int numTurnsAlive = 0;
    private int unconsciousTurns;
    private boolean isUnconscious = false;
    private int deadTurns = 0;
    private boolean isDead = false;
    private boolean isAttacked = false;
    private String gender;
    private int pregnantTurns = 0;
    private int attackTurns = 0;
    private boolean isPregnant = false;

    /**
     * Constructor of Dinosaur class
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Dinosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    /**
     * To get the food level of the dinosaur
     * @return integer food level
     */
    public int getFoodLevel() {
        return foodLevel;
    }

    /**
     * To set the food level of the dinosaur
     * @param foodLevel food level of the dinosaur
     */
    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    /**
     * To get the maximum food level of the dinosaur
     * @return max food level
     */
    public int getMaxFoodLevel() {
        return maxFoodLevel;
    }

    /**
     * To set the maximum food level of the dinosaur
     * @param maxFoodLevel maximum food level of the dinosaur
     */
    public void setMaxFoodLevel(int maxFoodLevel) {
        this.maxFoodLevel = maxFoodLevel;
    }

    /**
     * To get the number of unconscious turns of the dinosaur
     * @return integer unconscious Turns
     */
    public int getUnconsciousTurns() {
        return unconsciousTurns;
    }

    /**
     * To set the number of unconscious turns of the dinosaur
     * @param unconsciousTurns
     */
    public void setUnconsciousTurns(int unconsciousTurns) {
        this.unconsciousTurns = unconsciousTurns;
    }

    /**
     * To indicate whether a dinosaur is unconscious
     * @return boolean
     */
    public boolean isUnconscious() {
        return isUnconscious;
    }

    /**
     * To set the dinosaur's unconsciousness
     * @param unconscious a boolean indicating whether dinosaur is unconscious
     */
    public void setUnconscious(boolean unconscious) {
        isUnconscious = unconscious;
    }

    /**
     * To get number of dead turns of dinosaur
     * @return integer number of dead turns of dinosaur
     */
    public int getDeadTurns() {
        return deadTurns;
    }

    /**
     * To set number of dead turns of dinosaur
     * @param deadTurns
     */
    public void setDeadTurns(int deadTurns) {
        this.deadTurns = deadTurns;
    }

    /**
     * To indicate where a dinosaur is dead
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
        if (number == 1) {
            return "male";
        } else {
            return "female";
        }
    }

    /**
     * To set the gender of the dinosaur
     * @return string gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * To get the gender of the dinosaur
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * To get stage of the dinosaur (baby, adult)
     * @return string stage
     */
    public String getStage() {
        return stage;
    }

    /**
     * To set stage of the dinosaur
     * @param stage
     */
    public void setStage(String stage) {
        this.stage = stage;
    }

    /**
     * To get number of turns of baby dinosaur alive, to decide when it will be grown as an adult
     * @return integer
     */
    public int getNumTurnsAlive() {
        return numTurnsAlive;
    }

    /**
     * To set number of turns of baby dinosaur alive, to decide when it will be grown as an adult
     * @param numTurnsAlive
     */
    public void setNumTurnsAlive(int numTurnsAlive) {
        this.numTurnsAlive = numTurnsAlive;
    }

    /**
     * To get number of turns of a dinosaur since pregnant
     * @return integer
     */
    public int getPregnantTurns() {
        return pregnantTurns;
    }

    /**
     * To set number of turns of a dinosaur since pregnant
     * @param pregnantTurns
     */
    public void setPregnantTurns(int pregnantTurns) {
        this.pregnantTurns = pregnantTurns;
    }

    /**
     * To indicate whether a dinosaur is pregnant
     * @return boolean
     */
    public boolean isPregnant() {
        return isPregnant;
    }

    /**
     * To set a dinosaur to be pregnant
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

    /**
     * To breed the dinosaur with another dinosaur with same type and opposite gender
     * @param d2 second dinosaur to be breed with the dinosaur
     * @return boolean indicating whether the dinosaur has breed
     */
    public boolean breed(Dinosaur d1, Dinosaur d2){
        if (d1.name == d2.name) {
            if(d1.getFoodLevel() > 50 && d2.getFoodLevel() > 50 && (!d1.getGender().equals(d2.getGender())) && (d1.name.equals(d2.name)) && (!d1.getStage().equals("baby")) && (!d1.getStage().equals("baby"))){
                if(d1.getGender().equals("female")){
                    d1.setPregnant(true);
                    System.out.println("A pair of " + name + " have just bred");
                } else if (d2.getGender().equals("female")){
                    d2.setPregnant(true);
                    System.out.println("A pair of " + name + " have just bred");
                }
                return true;
            }
        }
        return false;
    }

    /**
     * To get number of turns after dinosaur being attacked
     * @return integer
     */
    public int getAttackTurns() {
        return attackTurns;
    }

    /**
     * To set number of turns after dinosaur being attacked
     * @param attackTurns
     */
    public void setAttackTurns(int attackTurns) {
        this.attackTurns = attackTurns;
    }

    /**
     * To decide whether a dinosaur is being attacked
     * @return boolean
     */
    public boolean isAttacked() {
        return isAttacked;
    }

    /**
     * To indicate whether a dinosaur is being attacked
     * @param attacked
     */
    public void setAttacked(boolean attacked) {
        isAttacked = attacked;
    }
}
