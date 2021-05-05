package game;

import edu.monash.fit2099.engine.*;

public class EatAction extends Action {

    protected Food food;
    protected Location l;
    private int foodLevel;

    public EatAction(int foodLevel) {
        this.foodLevel = foodLevel;

    }

    @Override
    public String execute(Actor actor, GameMap map) {
        // eat fruits from bush and ground
        l = map.locationOf(actor);
        for (Item i : l.getItems()) {
            if (i instanceof Fruit) {
                food = (Food) i;
                Dinosaur d = (Dinosaur) actor;
                d.setFoodLevel(d.getFoodLevel() + foodLevel);
                l.removeItem(i);
                return menuDescription(actor);

            }
        }
        return null;
    }


    @Override
    public String menuDescription(Actor actor) {
        return actor + "at (" + l.x() + ", " + l.y() + ") eats" + food;
    }
}
