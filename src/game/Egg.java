package game;

import edu.monash.fit2099.engine.Food;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;

public class Egg extends Food {
    String type;
    int turnsOnGround;
    boolean isEdible = true;

    /**
     * Constructor of Egg class
     * @param type type of dinosaur
     */
    public Egg(String type) {
        super(10, 200, 0, "Stegosaur Egg", 'e', true);
        this.type = type;
        if (this.type.equals("stegosaur")) {
            this.setName("Stegosaur Egg");
            this.setPrice(200);
        } else if (this.type.equals("brachiosaur")) {
            this.setName("Brachiosaur Egg");
            this.setPrice(500);
        } else if (this.type.equals("allosaur")) {
            this.setName("Allosaur Egg");
            this.setPrice(1000);
        }
    }

    /**
     * Returns whether the type of egg
     *
     * @return a string indicating the type of egg
     */
    public String getType() {
        return type;
    }

    /**
     * To set the type of egg depending on the type of the parent dinosaur
     *
     * @param type string type which will is the type of the parent dinosaur
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * To get the number of turns of egg being laid on the ground
     * @return integer
     */
    public int getTurnsOnGround() {
        return turnsOnGround;
    }

    /**
     * To set the number of turns of egg being laid on the ground
     *
     * @param turnsOnGround the new number of turns the egg has been on the ground
     */
    public void setTurnsOnGround(int turnsOnGround) {
        this.turnsOnGround = turnsOnGround;
    }

    /**
     * To create baby dinosaur when egg is hatched based on their type
     *
     * @param location location where the egg is hatched
     * @param map location on the map
     * @return boolean indicating egg is hatched or not
     */
    public boolean eggsHatch(Location location, GameMap map){
        this.setTurnsOnGround(this.getTurnsOnGround() + 1);

        // prevent allosaur from eating their own children
         if (this.getType().equals("allosaur")){
             this.setEdible(false);

         }else if (this.getType().equals("stegosaur") || this.getType().equals("brachiosaur")){
             this.setEdible(true);
         }

        if (this.getTurnsOnGround() > 15){
            location.removeItem(this);
            Dinosaur baby = null;
            if (this.getType().equals("stegosaur")){
                baby = new Stegosaur("Stegosaur");
            }
            else if (this.getType().equals("brachiosaur")) {
                baby = new Allosaur("Brachiosaur");
            }
            else if (this.getType().equals("allosaur")) {
                baby = new Allosaur("Allosaur");
            }
            baby.setStage("baby");
            baby.setFoodLevel(10);
            location.addActor(baby);
            eggsHatchRewards(map, this);
            // remove egg that has been hatched (eggshell)
            location.removeItem(this);
            return true;
        } else {
            return false;
        }
    }

    /**
     * To add  the eco points of players when an egg is hatched
     * @param map the game map of the game
     * @param e egg that has been hatched
     */
    private void eggsHatchRewards(GameMap map, Egg e){
        ArrayList<Player> players = map.getPlayers();
        for(Player p : players){
            if(e.getType().equals("stegosaur")){
                p.setEcoPoints(p.getEcoPoints() + 100);
            } else if (e.getType().equals("brachiosaur")){
                p.setEcoPoints(p.getEcoPoints() + 1000);
            } else if (e.getType().equals("allosaur")){
                p.setEcoPoints(p.getEcoPoints() + 1000);
            }
        }
    }

    /**
     * This method is only applicable to allosaur eggs
     * @return a boolean indicating whether the egg can be eaten
     */
    public boolean isEdible () {
        return isEdible;
    }

    /**
     * To set to the allosaur egg to be not edible, avoid allosaur from eating their child
     * @param isEdible a boolean indicating whether the egg can be eaten
     */
    public void setEdible ( boolean isEdible){
        this.isEdible = isEdible;
    }
}