package game;

import edu.monash.fit2099.engine.*;

public class Allosaur extends Dinosaur {
    int babyFoodLevel = 20;

    /**
     * Constructor.
     *
     * @param name        the name of the Actor
     * @param displayChar the character that will represent the Actor in the display
     * @param hitPoints   the Actor's starting hit points
     */
    public Allosaur(String name, char displayChar, int hitPoints) {
        super("allosaur", 'A', 100);
        // get the gender of allosaur
        this.setGender(this.randomiseGender());
        this.setFoodLevel(50);
        this.setMaxFoodLevel(100);
        this.setUnconsciousTurns(0);
        this.setPregnantTurns(20);
    }

    @Override
    public Action playTurn(Actions actions, Action lastAction, GameMap map, Display display) {
        return null;
    }

    private static void layEgg(Dinosaur d, Location l) {
        if (d.getPregnantTurns() >= 20) {
            Egg egg = new Egg(((Allosaur) d).name);
            egg.setEdible(false);
            l.addItem(egg);
        }
    }

}
