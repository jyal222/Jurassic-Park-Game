package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Random;

public abstract class Dinosaur extends Actor {

    private Behaviour behaviour;
    GameMap map;
    int foodLevel;
    int maxFoodLevel;
    String stage = "adult"; // default
    int numTurnsAlive = 0;
    int unconsciousTurns;
    boolean isUnconscious = false;
    int deadTurns = 0;
    boolean isDead = false;
    String gender;
    int pregnantTurns = 0;
    boolean isPregnant = false;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Dinosaur(String name, char displayChar, int hitPoints) {
        super(name, displayChar, hitPoints);
    }

    public int getFoodLevel() {
        return foodLevel;
    }

    public void setFoodLevel(int foodLevel) {
        this.foodLevel = foodLevel;
    }

    public int getMaxFoodLevel() {
        return maxFoodLevel;
    }

    public void setMaxFoodLevel(int maxFoodLevel) {
        this.maxFoodLevel = maxFoodLevel;
    }

    public int getUnconsciousTurns() {
        return unconsciousTurns;
    }

    public void setUnconsciousTurns(int unconsciousTurns) {
        this.unconsciousTurns = unconsciousTurns;
    }

    public boolean isUnconscious() {
        return isUnconscious;
    }

    public void setUnconscious(boolean unconscious) {
        isUnconscious = unconscious;
    }

    public int getDeadTurns() {
        return deadTurns;
    }

    public void setDeadTurns(int deadTurns) {
        this.deadTurns = deadTurns;
    }

    public boolean isDead() {
        if (this.hitPoints <= 0) {
            this.setDead(true);
        }
        return isDead;
    }

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

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStage() {
        return stage;
    }

    public void setStage(String stage) {
        this.stage = stage;
    }

    public int getNumTurnsAlive() {
        return numTurnsAlive;
    }

    public void setNumTurnsAlive(int numTurnsAlive) {
        this.numTurnsAlive = numTurnsAlive;
    }

    public int getPregnantTurns() {
        return pregnantTurns;
    }

    public void setPregnantTurns(int pregnantTurns) {
        this.pregnantTurns = pregnantTurns;
    }

    public boolean isPregnant() {
        return isPregnant;
    }

    public void setPregnant(boolean pregnant) {
        isPregnant = pregnant;
    }

    public static void tick(Dinosaur d, Location l, GameMap g) {
        int currFoodLevel;
        ArrayList<Location> adjacentLocations = l.validAdjacentLocations();
        if (d instanceof Stegosaur || d instanceof Allosaur) {
            currFoodLevel = d.getFoodLevel();
            d.setFoodLevel(currFoodLevel - 1);
            // stegosaur gets hungry
            if (currFoodLevel > 0) {
                if (d.getFoodLevel() <= 20) {
                    System.out.println(d.name + " at (" + l.x() + ", " + l.y() + ") is getting hungry!");
                }
                if (d.getFoodLevel() > 50) {
                    // Check locations for breedable stegosaurs
                    for (Location adj : adjacentLocations) {
                        if (l.containsAnActor()) {
                            Actor actor = adj.getActor();
                            if (actor instanceof Stegosaur) {
                                boolean bred = breed((Stegosaur) actor, d);
                                if (bred) {
                                    break;
                                }
                            }
                            else if (actor instanceof Allosaur) {
                                boolean bred = breed((Allosaur) actor, d);
                                if (bred) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            // For unconscious dinosaurs
            else {
                d.setUnconscious(true);
                d.setUnconsciousTurns(d.getUnconsciousTurns() + 1);
                if (d.getUnconsciousTurns() >= 20) {
                    d.setDead(true);
                    d.setDeadTurns(d.getDeadTurns() + 1);
                }
            }
        } else if (d instanceof Brachiosaur) {
            currFoodLevel = d.getFoodLevel();
            d.setFoodLevel(currFoodLevel - 1);
            // brachiosaur gets hungry
            if (currFoodLevel > 0) {
                if (d.getFoodLevel() <= 20) {
                    System.out.println(d.name + " at (" + l.x() + ", " + l.y() + ") is getting hungry!");
                }
            }
            if (d.getFoodLevel() > 70) {
                // Check locations for breedable brachiosaurs
                for (Location adj : adjacentLocations) {
                    if (l.containsAnActor()) {
                        Actor actor = adj.getActor();
                        if (actor instanceof Dinosaur) {
                            boolean bred = breed((Brachiosaur) actor, d);
                            if (bred) {
                                break;
                            }
                        }
                    }
                }
            }
            // For unconscious dinosaurs
            else {
                d.setUnconscious(true);
                d.setUnconsciousTurns(d.getUnconsciousTurns() + 1);
                if (d.getUnconsciousTurns() >= 20) {
                    d.setDead(true);
                    d.setDeadTurns(d.getDeadTurns() + 1);
                }
            }
        }
    }

    public static boolean breed(Dinosaur d1, Dinosaur d2){
        if(d1.getFoodLevel() > 50 && d2.getFoodLevel() > 50 && (!d1.getGender().equals(d2.getGender())) && (d1.name.equals(d2.name)) && (!d1.getStage().equals("baby")) && (!d2.getStage().equals("baby"))){
            if(d1.getGender().equals("female")){
                d1.setPregnantTurns(d1.getPregnantTurns() + 1);
                d1.setPregnant(true);
            } else if (d2.getGender().equals("female")){
                d2.setPregnantTurns(d2.getPregnantTurns() + 1);
                d2.setPregnant(true);
            }
            System.out.println("A pair of " + d1.name + "have just bred");
            return true;
        }
        return false;
    }

}
