package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;

public class BreedAction extends Action {

    private Dinosaur me;

    public BreedAction(Dinosaur dinosaur) {
        this.me = dinosaur;
    }

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

    @Override
    public String menuDescription(Actor actor) {
        return "A pair of " + actor.getClass() + " have just bred";
    }
}
