package game;

import edu.monash.fit2099.engine.*;

public class EatBehaviour implements Behaviour{


    @Override
    public Action getAction(Actor actor, GameMap map) {
        Location l = map.locationOf(actor);
        if (l.getGround() instanceof Bush || l.getGround() instanceof Tree) {
            return ((Dinosaur)actor).getEatAction();

        }
        return null;
    }
}
