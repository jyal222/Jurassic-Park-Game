package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.Random;

public class Brachiosaur extends Dinosaur {
    private Behaviour behaviour;

    /**
     * Constructor of Brachiosaur class
     * All Brachiosaur are represented by a 'b' and have 100 hit points.
     * @param name
     */
    public Brachiosaur(String name) {
        super("brachiosaur", 'b', 100);
        behaviour = new WanderBehaviour();
        // get the gender of brachiosaur
        super.setGender(this.randomiseGender());
        super.setFoodLevel(100);
        super.setMaxFoodLevel(160);
        super.setUnconsciousTurns(15);
        super.setPregnantTurns(30);
    }

    /**
     * This method ticks all the brachiosaur across the gamemap.
     * @param l location of the dinosaur
     * @param g game map
     */
    //@Override
    public static void dinosaurTick(Brachiosaur b, Location l, GameMap g) {
        b.setFoodLevel(b.getFoodLevel() - 1);
        ArrayList<Location> adjacentLocations = l.validAdjacentLocations();

        // Baby dinosaurs grow up
        if(b.getStage().equals("baby")){
            b.babyDinosaurGrows();
        }

        if (b.getFoodLevel() > 0) {


            // If dinosaur getting hungry
            if (b.getFoodLevel() <= 140) {
                System.out.println(b.name + " at (" + l.x() + ", " + l.y() + ") is getting hungry!");
            }

            // eat fruits from bush and ground
            if (l.getGround() instanceof Tree) {
                for (Item i : l.getItems()){
                    if (i instanceof Fruit) {
                        Fruit f = (Fruit) i;
                        b.setFoodLevel(b.getFoodLevel() + 5);
                        //l.removeItem(f);
                        System.out.println(b.name + " at (" + l.x() + ", " + l.y() + ") eats " + f.getName());
                    }
                }
            }

            // If dinosaur can breed
            if (b.getFoodLevel() > 70) {
                // Check locations for breedable stegosaurs
                for (Location adj : adjacentLocations) {
                    if (l.containsAnActor()) {
                        Actor a = adj.getActor();
                        if (a instanceof Brachiosaur) {
                            if (!b.isPregnant() && !((Brachiosaur) a).isPregnant()) {
                                if (b.breed((Dinosaur) a)) {
                                    break;
                                }
                            }
                            if (b.isPregnant()) {
                                b.setPregnantTurns(b.getPregnantTurns() + 1);
                                b.layEgg(l);
                            } else if (((Brachiosaur) a).isPregnant()) {
                                ((Brachiosaur) a).setPregnantTurns(((Brachiosaur) a).getPregnantTurns() + 1);
                                ((Brachiosaur) a).layEgg(l);
                            }
                        }
                    }
                }
            }

            // If Brachiosaur step on the Bush, 50% to kill bush
            if(l.getGround() instanceof Bush){
                Random random = new Random();
                int number = random.nextInt(100) + 1;
                if (number <= 50){
                    l.setGround(new Dirt());
                    System.out.println(b.name + " at (" + l.x() + ", " + l.y() + ") steps on bush");
                }
            }


            /*

            // Potentially lay egg
            if (b.isPregnant()) {
                b.layEgg(l);
            }

             */

        }
        // For unconscious dinosaurs
        else {
            b.setUnconscious(true);
            b.setUnconsciousTurns(b.getUnconsciousTurns() + 1);
            if (b.getUnconsciousTurns() >= 15){
                b.setDead(true);
                b.setDeadTurns(b.getDeadTurns() + 1);

                // Carcass of dead dinosaur still remains for 40 turns
                if(b.getDeadTurns() >= 40){
                    g.removeActor(b);
                    g.at(l.x(), l.y()).setGround(new Dirt());
                }
            }
        }
    }

    /**
     * Figure out what to do next.
     *
     * FIXME: Brachiosaur wanders around at random, or if no suitable MoveActions are available, it
     * just stands there.  That's boring.
     *
     * @see edu.monash.fit2099.engine.Actor#playTurn(Actions, Action, GameMap, Display)
     */
    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        Action wander = behaviour.getAction(this, map);
        if (wander != null)
            return wander;

        return new DoNothingAction();
    }

    /**
     * To let the pregnant dinosaur lay egg if passed the pregnant turns
     * @param l location of dinosaur
     */
    private void layEgg(Location l) {
        //this.setPregnantTurns(this.getPregnantTurns() + 1);
        if (this.getPregnantTurns() >= 30) {
            Egg egg = new Egg(name);
            l.addItem(egg);
        }
    }

    /**
     * To set a baby dinosaur to adult after number of turns alive of a baby dinosaur is sufficient
     */
    private void babyDinosaurGrows(){
        this.setNumTurnsAlive(this.getNumTurnsAlive() + 1);
        if(this.getNumTurnsAlive() >= 50){
            this.setStage("adult");
        }
    }

}
