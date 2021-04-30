package game;

import edu.monash.fit2099.engine.*;

public class Brachiosaur extends Dinosaur {

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Brachiosaur(String name, char displayChar, int hitPoints) {
        super("brachiosaur", 'B', 100);
        // get the gender of brachiosaur
        this.setGender(this.randomiseGender());
        this.setFoodLevel(100);
        this.setMaxFoodLevel(160);
        this.setUnconsciousTurns(15);
        this.setPregnantTurns(30);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    private static void layEgg(Dinosaur d, Location l) {
        if (d.getPregnantTurns() >= 30) {
            Egg egg = new Egg(((Brachiosaur) d).name);
            l.addItem(egg);
        }
    }

}

