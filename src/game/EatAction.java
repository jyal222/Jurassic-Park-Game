package game;

import edu.monash.fit2099.engine.*;

import java.util.List;

public class EatAction extends Action {

    private List<Eatable> foodList;
    private Location l;

    public EatAction(List<Eatable> foodList) {
        this.foodList = foodList;
    }

    @Override
    public String execute(Actor actor, GameMap map) {
        Dinosaur dinosaur = (Dinosaur) actor;
        // eat fruits from bush and ground
        l = map.locationOf(actor);

        for (Eatable food: foodList) {
            dinosaur.eat(food);
        }
        return menuDescription(actor);
    }
    // TODO: remember to set foodlevel when creating a food


    @Override
    public String menuDescription(Actor actor) {
        return actor + "at (" + l.x() + ", " + l.y() + ") eats" + foodList;
    }
}
