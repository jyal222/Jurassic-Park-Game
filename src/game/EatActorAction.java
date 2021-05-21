package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class EatActorAction extends DinosaurAction {

    private Actor target;
    private Location loc;

    /**
     * Constructor of EatActorAction
     * @param target
     */
    public EatActorAction(Actor target) {
        this.target = target;
    }

    /**
     * This method is to let allosaur to eat pterodactyl.
     * @param dinosaur The dinosaur performing the action.
     * @param map      game map
     * @return string
     */
    @Override
    public String execute(Dinosaur dinosaur, GameMap map) {
        loc = map.locationOf(dinosaur);
        dinosaur.heal(Allosaur.MAX_FOOD_LEVEL);
        map.removeActor(target);
        return menuDescription(dinosaur);
    }

    /**
     * This method is to return string.
     * @param actor The actor performing the action.
     * @return string
     */
    @Override
    public String menuDescription(Actor actor) {
        return actor + "at (" + loc.x() + ", " + loc.y() + ") eats" + target;
    }

}
