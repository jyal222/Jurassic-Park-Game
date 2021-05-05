package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

public class Allosaur extends Dinosaur {
    int babyFoodLevel = 20;
    private Behaviour behaviour;

    /**
     * Constructor of Allosaur class
     * All Allosaur are represented by an 'a' and have 100 hit points.
     * @param name name of dinosaur
     */
    public Allosaur(String name) {
        super("allosaur", 'a', 100);
        behaviour = new WanderBehaviour();
        // get the gender of allosaur
        super.setGender(this.randomiseGender());
        super.setFoodLevel(50);
        super.setMaxFoodLevel(100);
        super.setUnconsciousTurns(0);
        super.setPregnantTurns(20);
    }

    /**
     * This method ticks all the allosaurs across the gamemap.
     * @param l location of the dinosaur
     * @param g game map
     */
    public static void dinosaurTick(Allosaur a, Location l, GameMap g) {
        a.setFoodLevel(a.getFoodLevel() - 1);
        ArrayList<Location> adjacentLocations = l.validAdjacentLocations();

        // Baby dinosaurs grow up
        if(a.getStage().equals("baby")){
            a.babyDinosaurGrows();
        }

        if (a.getFoodLevel() > 0) {


            // If dinosaur getting hungry
            if (a.getFoodLevel() <=20) {
                System.out.println(a.name + " at (" + l.x() + ", " + l.y() + ") is getting hungry!");
            }

            // If dinosaur can breed
            if (a.getFoodLevel() > 50) {
                // Check locations for breedable stegosaurs
                for (Location adj : adjacentLocations) {
                    if (l.containsAnActor()) {
                        Actor actor = adj.getActor();
                        if (actor instanceof Allosaur) {
                            if (!a.isPregnant() && !((Allosaur) actor).isPregnant()) {
                                if (a.breed((Dinosaur) actor)) {
                                    break;
                                }
                            }
                            if (a.isPregnant()) {
                                a.setPregnantTurns(a.getPregnantTurns() + 1);
                                a.layEgg(l);
                            } else if (((Allosaur) actor).isPregnant()) {
                                ((Allosaur) actor).setPregnantTurns(((Allosaur) actor).getPregnantTurns() + 1);
                                ((Allosaur) actor).layEgg(l);
                            }
                        }
                    }
                }
            }

            /*
            // Potentially lay egg
            if (a.isPregnant()) {
                a.layEgg(l);
            }

             */

            // Eat an egg currently on the ground
            for (Item item : l.getItems()){
                if (item instanceof Egg){
                    Egg e = (Egg) item;
                    if(e.isEdible()){
                        a.setFoodLevel(a.getFoodLevel()+ e.getFoodLevel());
                        l.removeItem(e);
                    }
                }
                if (item instanceof Fruit) {
                    Fruit f = (Fruit) item;
                    a.setFoodLevel(a.getFoodLevel() + f.getFoodLevel());
                    l.removeItem(f);
                }
            }



            // Allosaur attacks other types of dinosaur
            boolean hasAttackedOrKilled = false;
            for (Location adj : adjacentLocations) {
                if (hasAttackedOrKilled) {
                    break; // To only eat 1 dead dinosaur per turn.
                }
                if (adj.containsAnActor()) {
                    if (adj.getActor() instanceof Dinosaur) {
                        Dinosaur stegosaurAttacked = (Dinosaur) adj.getActor();

                        if (stegosaurAttacked instanceof Stegosaur) {
                            // dinosaur that hab been attacked cannot be attack for 20 turns
                            if (stegosaurAttacked.getAttackTurns() > 20){
                                if (a.getStage().equals("adult")){
                                    stegosaurAttacked.setFoodLevel(stegosaurAttacked.getFoodLevel() - 20);
                                    a.setFoodLevel(a.getFoodLevel() + 20);
                                    stegosaurAttacked.setAttacked(true);

                                    // baby dinosaur can only attack baby dinosaur
                                }else if(a.getStage().equals("baby")){
                                    if (stegosaurAttacked.getStage().equals("baby")){
                                        stegosaurAttacked.setFoodLevel(stegosaurAttacked.getFoodLevel() - 10);
                                        a.setFoodLevel(a.getFoodLevel() + 10);
                                        stegosaurAttacked.setAttacked(true);
                                    }
                                }
                            }
                            stegosaurAttacked.setAttackTurns(a.getAttackTurns()+1);
                        }
                        hasAttackedOrKilled = true;
                        // Eat corpse if a dinosaur was killed during battle
                        if(stegosaurAttacked.isDead()){
                            stegosaurAttacked.setDeadTurns(stegosaurAttacked.getDeadTurns() + 1);
                            if(stegosaurAttacked.getDeadTurns() >= 20){
                                g.removeActor(stegosaurAttacked);
                            }
                        }
                    }
                }
            }

        }
        // For unconscious dinosaurs
        else {
            a.setUnconscious(true);
            a.setUnconsciousTurns(a.getUnconsciousTurns() + 1);
            if (a.getUnconsciousTurns() >= 15){
                a.setDead(true);
                a.setDeadTurns(a.getDeadTurns() + 1);

                // Corpse of dead dinosaur still remains for 20 turns
                if(a.getDeadTurns() >= 20){
                    g.removeActor(a);
                    g.at(l.x(), l.y()).setGround(new Dirt());
                }
            }
        }




    }

    /**
     * Figure out what to do next.
     *
     * FIXME: Allosaur wanders around at random, or if no suitable MoveActions are available, it
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
