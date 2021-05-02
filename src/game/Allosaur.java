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
    @Override
    public void tick(Location l, GameMap g) {
        this.setFoodLevel(this.getFoodLevel() - 1);
        ArrayList<Location> adjacentLocations = l.validAdjacentLocations();

        // Baby dinosaurs grow up
        if(this.getStage().equals("baby")){
            babyDinosaurGrows();
        }

        if (this.getFoodLevel() > 0) {


            // If dinosaur getting hungry
            if (this.getFoodLevel() <=20) {
                System.out.println(name + " at (" + l.x() + ", " + l.y() + ") is getting hungry!");
            }

            // If dinosaur can breed
            if (this.getFoodLevel() > 50) {
                // Check locations for breedable stegosaurs
                for (Location adj : adjacentLocations) {
                    if (l.containsAnActor()) {
                        Actor a = adj.getActor();
                        if (a instanceof Allosaur) {
                            if (breed((Dinosaur) a)) {
                                break;
                            }
                        }
                    }
                }
            }

            // Potentially lay egg
            if (this.isPregnant()) {
                layEgg(l);
            }

            // Eat an egg currently on the ground
            for (Item item : l.getItems()){
                if (item instanceof Egg){
                    Egg e = (Egg) item;
                    if(e.isEdible()){
                        this.setFoodLevel(this.getFoodLevel()+ e.getFoodLevel());
                        l.removeItem(e);
                    }
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
                                if (this.getStage().equals("adult")){
                                    stegosaurAttacked.setFoodLevel(stegosaurAttacked.getFoodLevel() - 20);
                                    this.setFoodLevel(this.getFoodLevel() + 20);
                                    stegosaurAttacked.setAttacked(true);

                                    // baby dinosaur can only attack baby dinosaur
                                }else if(this.getStage().equals("baby")){
                                    if (stegosaurAttacked.getStage().equals("baby")){
                                        stegosaurAttacked.setFoodLevel(stegosaurAttacked.getFoodLevel() - 10);
                                        this.setFoodLevel(this.getFoodLevel() + 10);
                                        stegosaurAttacked.setAttacked(true);
                                    }
                                }
                            }
                            stegosaurAttacked.setAttackTurns(getAttackTurns()+1);
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
            this.setUnconscious(true);
            this.setUnconsciousTurns(this.getUnconsciousTurns() + 1);
            if (this.getUnconsciousTurns() >= 15){
                this.setDead(true);
                this.setDeadTurns(this.getDeadTurns() + 1);

                // Corpse of dead dinosaur still remains for 20 turns
                if(this.getDeadTurns() >= 20){
                    g.removeActor(this);
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
