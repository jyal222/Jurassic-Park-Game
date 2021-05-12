package game;

import edu.monash.fit2099.engine.Location;

public class Pterodactyls extends Dinosaur{
    /**
     * Constructor of Dinosaur class
     *
     * @param name         the name of the Actor
     * @param displayChar  the character that will represent the Actor in the display
     * @param hitPoints    the Actor's starting hit points
     * @param maxHitPoints
     */
    public Pterodactyls(String name, char displayChar, int hitPoints, int maxHitPoints) {
        super(name, displayChar, hitPoints, maxHitPoints);
    }

    @Override
    public boolean canEat(Eatable food) {
        return false;
    }

    @Override
    public DinosaurAction getEatAction(Location location) {
        return null;
    }
}
