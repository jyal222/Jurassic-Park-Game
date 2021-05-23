package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.NumberRange;

import java.util.ArrayList;
import java.util.List;

/**
 * An egg class that inherited from Food class.
 */
public class Egg extends Food {

    private Dinosaur dinosaur;
    private int turnsOnGround;
    private boolean isOnTree;

    /**
     * Constructor of Egg class
     *
     * @param dinosaur the dinosaur
     */
    public Egg(Dinosaur dinosaur) {
        super(10, 200, 0, "Egg", 'e', true);
        this.dinosaur = dinosaur;
        String type = dinosaur.toString();
        if (type.equals(Stegosaur.STEGOSAUR)) {
            super.name = "Stegosaur Egg";
            super.price = 200;
        } else if (type.equals(Brachiosaur.BRACHIOSAUR)) {
            super.name = "Brachiosaur Egg";
            super.price = 500;
        } else if (type.equals(Allosaur.ALLOSAUR)) {
            super.name = "Allosaur Egg";
            super.price = 1000;
        } else if (type.equals(Pterodactyls.PTERODACTYLS)) {
            super.name = "Pterodactyls Egg";
            super.price = 200;
        }
    }

    public Egg(Dinosaur dinosaur, boolean isOnTree) {
        this(dinosaur);
        this.isOnTree = isOnTree;
    }

    /**
     * To get Dinosaur type
     *
     * @return Dinosaur dinosaur
     */
    public Dinosaur getDinosaur() {
        return dinosaur;
    }

    /**
     * To add the eco points of players when an egg is hatched
     *
     * @param map the game map of the game
     */
    public void incrementPlayersEcoPoints(GameMap map) {
        // Get all players on map
        NumberRange xRange = map.getXRange();
        NumberRange yRange = map.getYRange();
        List<Player> players = new ArrayList<>();
        for (Integer x : xRange) {
            for (Integer y : yRange) {
                Location loc = map.at(x, y);
                if (loc.containsAnActor() && loc.getActor() instanceof Player) {
                    players.add((Player) loc.getActor());
                }
            }
        }
        // Iterate through every player and increment eco points
        for (Player p : players) {
            p.earn(dinosaur.getEggEcoPoints());
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

        if (turnsOnGround > dinosaur.getEggHatchThreshold() && !currentLocation.containsAnActor()) {
            turnsOnGround = 0;
            Dinosaur baby = null;
            String type = dinosaur.toString();
            if (type.equals(Stegosaur.STEGOSAUR)) {
                baby = new Stegosaur(Stegosaur.BABY_FOOD_LEVEL);
            } else if (type.equals(Brachiosaur.BRACHIOSAUR)) {
                baby = new Brachiosaur(Brachiosaur.BABY_FOOD_LEVEL);
            } else if (type.equals(Allosaur.ALLOSAUR)) {
                baby = new Allosaur(Allosaur.BABY_FOOD_LEVEL);
            } else if (type.equals(Pterodactyls.PTERODACTYLS)) {
                baby = new Pterodactyls(Pterodactyls.BABY_FOOD_LEVEL);
            }
            if (baby == null) return;
            baby.setStage(Dinosaur.Stage.baby);
            currentLocation.addActor(baby);
            // remove egg that has hatched (eggshell)
            currentLocation.removeItem(this);
            incrementPlayersEcoPoints(currentLocation.map());
        }
    }

    /**
     * To return true if the egg is on tree.
     * @return boolean
     */
    public boolean isOnTree() {
        return isOnTree;
    }
}