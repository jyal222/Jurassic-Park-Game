package game;

import edu.monash.fit2099.engine.Actions;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

public class Lake extends Ground {
    /**
     *
     */
    public Lake() {
        super('~');
    }

    /**
     * Returns an Action list.
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
//        Actions actions = new Actions();
//        if (location.map().locationOf(actor) == location) {
//            actions.add(new PickFruitAction(this));
//        }
//        return actions;
        return null;
    }
}
