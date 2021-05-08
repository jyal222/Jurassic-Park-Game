package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

/**
 * An egg class that inherited from Food class.
 */
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
        if (type.equals(Stegosaur.STEGOSAUR)) {
            this.setName("Stegosaur Egg");
            this.setPrice(200);
        } else if (type.equals(Brachiosaur.BRACHIOSAUR)) {
            this.setName("Brachiosaur Egg");
            this.setPrice(500);
        } else if (type.equals(Allosaur.ALLOSAUR)) {
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
     * This method is to tick all the eggs action in the game map.
     *
     * @param currentLocation The location of the ground on which we lie.
     */
    @Override
    public void tick(Location currentLocation) {
        super.tick(currentLocation);
        turnsOnGround++;

        if (turnsOnGround > 15) {
            Dinosaur baby = null;
            if (type.equals(Stegosaur.STEGOSAUR)) {
                baby = new Stegosaur(Stegosaur.BABY_FOOD_LEVEL);
            } else if (type.equals(Brachiosaur.BRACHIOSAUR)) {
                baby = new Brachiosaur(Brachiosaur.BABY_FOOD_LEVEL);
            } else if (type.equals(Allosaur.ALLOSAUR)) {
                baby = new Allosaur(Allosaur.BABY_FOOD_LEVEL);
            }
            baby.setStage(Dinosaur.Stage.baby);
            currentLocation.addActor(baby);
            // remove egg that has hatched (eggshell)
            currentLocation.removeItem(this);
            eggsHatchRewards(currentLocation.map());
        }
    }
}