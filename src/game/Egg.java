package game;

import edu.monash.fit2099.engine.Food;

public class Egg extends Food {
    String type;
    int turnsOnGround;
    boolean isEdible = true;

    public Egg(String type) {
        super(10, 200, 0, "Stegosaur Egg", 'e', true);
        this.type = type;
        if (this.type.equals("stegosaur")) {
            this.setName("Stegosaur Egg");
            this.setPrice(200);
        }
        else if (this.type.equals("brachiosaur")) {
            this.setName("Brachiosaur Egg");
            this.setPrice(500);
        }
        else if (this.type.equals("allosaur")) {
            this.setName("Allosaur Egg");
            this.setPrice(1000);
        }
    }

    /**
     * Returns whether the egg is a stegosaur or allosaur type
     * @return a string indicating the type of egg
     */
    public String getType() { return type; }

    /**
     * System will set the type of egg depending on the type of dinosaur that laid it
     * @param type string type which will match the type of the mother and father dinosaur
     */
    public void setType(String type) { this.type = type; }

    /**
     * Indicates how long the egg has been on the ground since lain.
     * @return integer
     */
    public int getTurnsOnGround() { return turnsOnGround; }

    /**
     * Changes the number of turns an egg has been on the ground
     * @param turnsOnGround the new number of turns the egg has been on the ground
     */
    public void setTurnsOnGround(int turnsOnGround) { this.turnsOnGround = turnsOnGround; }

    /**
     * This method is only applicable to allosaur eggs
     * @return a boolean indicating whether the egg can be eaten
     */
    public boolean isEdible() {
        return isEdible;
    }

    /**
     * Set to false initially for allosaur eggs to ensure that an adult allosaur doesnt automatically eat an egg if it has just lain one
     * @param isEdible a boolean indicating whether the egg can be eaten
     */
    public void setEdible(boolean isEdible) {
        this.isEdible = isEdible;
    }
}