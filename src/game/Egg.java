package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class Egg extends Food {

    private String type;
    private int turnsOnGround;
    private boolean isEdible = true;

    /**
     * Constructor of Egg class
     *
     * @param type type of dinosaur
     */
    public Egg(String type) {
        super(10, 200, 0, "Egg", 'e', true);
        this.type = type;
        switch (type) {
            case "stegosaur":
                this.setName("Stegosaur Egg");
                this.setPrice(200);
                break;
            case "brachiosaur":
                this.setName("Brachiosaur Egg");
                this.setPrice(500);
                break;
            case "allosaur":
                this.setName("Allosaur Egg");
                this.setPrice(1000);
                break;
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
     *
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
     * To add the eco points of players when an egg is hatched
     *
     * @param map the game map of the game
     */
    private void eggsHatchRewards(GameMap map) {
        for (Player p : map.getPlayers()) {
            if (type.equals("stegosaur")) {
                p.setEcoPoints(p.getEcoPoints() + 100);
                System.out.println(p.getEcoPoints());
            } else if (type.equals("brachiosaur")) {
                p.setEcoPoints(p.getEcoPoints() + 1000);
                System.out.println(p.getEcoPoints());
            } else if (type.equals("allosaur")) {
                p.setEcoPoints(p.getEcoPoints() + 1000);
                System.out.println(p.getEcoPoints());
            }
        }
    }

    /**
     * This method is only applicable to allosaur eggs
     *
     * @return a boolean indicating whether the egg can be eaten
     */
    public boolean isEdible() {
        return isEdible;
    }

    /**
     * To set to the allosaur egg to be not edible, avoid allosaur from eating their child
     *
     * @param isEdible a boolean indicating whether the egg can be eaten
     */
    public void setEdible(boolean isEdible) {
        this.isEdible = isEdible;
    }

    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        turnsOnGround++;

//        // prevent allosaur from eating their own children
//        if (this.getType().equals("allosaur")) {
//            this.setEdible(false);
//
//        } else if (this.getType().equals("stegosaur") || this.getType().equals("brachiosaur")) {
//            this.setEdible(true);
//        }

        if (turnsOnGround > 15) {
            Dinosaur baby = null;
            if (type.equals("stegosaur")) {
                baby = new Stegosaur(10);
            } else if (type.equals("brachiosaur")) {
                baby = new Brachiosaur(10);
            } else if (type.equals("allosaur")) {
                baby = new Allosaur(20);
            }
            baby.setStage(Dinosaur.Stage.baby);
            currentLocation.addActor(baby);
            // remove egg that has hatched (eggshell)
            currentLocation.removeItem(this);
            eggsHatchRewards(currentLocation.map());
        }
    }
}