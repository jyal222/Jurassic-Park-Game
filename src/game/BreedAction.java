package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

/**
 * An action that is for dinosaur's breeding.
 */
public class BreedAction extends Action {

    private Dinosaur me;

    /**
     * Constructor
     * @param dinosaur dinosaur currently acting.
     */
    public BreedAction(Dinosaur dinosaur) {
        this.me = dinosaur;
    }

    /**
     * This method is to set pregnant of the pregnant dinosaur.
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return a string showing which dinosaur has pregnant.
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur act = (Dinosaur) actor;
        if (me.getGender().equals("female")) {
            me.setPregnant(true);
        } else if (act.getGender().equals("female")) {
            act.setPregnant(true);
        }
        return menuDescription(actor);
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return a string showing which dinosaur has pregnant.
     */
    @Override
    public String menuDescription(Actor actor) {
        return "A pair of " + actor.getClass() + " have just bred";
    }
}
