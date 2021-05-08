package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An action that is for dinosaur's breeding.
 */
public class BreedAction extends DinosaurAction {

    private Dinosaur anotherDinosaur;

    /**
     * Constructor
     * @param dinosaur dinosaur currently acting.
     */
    public BreedAction(Dinosaur dinosaur) {
        this.anotherDinosaur = dinosaur;
    }

    /**
     * This method is to set pregnant of the pregnant dinosaur.
     * @param dinosaur The actor performing the action.
     * @param map The map the actor is on.
     * @return a string showing which dinosaur has pregnant.
     */
    @Override
    public String execute(Dinosaur dinosaur, GameMap map) {
        if (anotherDinosaur.getGender().equals("female")) {
            anotherDinosaur.setPregnant(true);
        } else if (dinosaur.getGender().equals("female")) {
            dinosaur.setPregnant(true);
        }
        return menuDescription(dinosaur);
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return a string showing which dinosaur has pregnant.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "A pair of " + actor + " have just bred";
    }
}
