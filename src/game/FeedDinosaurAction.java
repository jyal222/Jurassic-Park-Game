package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Location;

public class FeedDinosaurAction extends Action {

    private Food food;
    private Dinosaur dinosaur;
    private Location locationOfDinosaur;

    /**
     * Feed Dinosaur Food Action Constructor
     *
     * @param food     food to feed dinosaur
     * @param dinosaur dinosaur to be fed
     * @param location location of dinosaur to be fed
     */
    public FeedDinosaurAction(Food food, Dinosaur dinosaur, Location location) {
        this.food = food;
        this.dinosaur = dinosaur;
        this.locationOfDinosaur = location;
    }

    /**
     * Food item will be removed from inventory if player chooses to feed dinosaur
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return string showing the chosen dinosaur to feed and its location
     * @throws IllegalArgumentException if the actor parameter is not of type Player
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Player) {
            Player player = (Player) actor;
            player.removeItemFromInventory(food);
            player.earn(10);
            dinosaur.eat(food);
            return menuDescription(actor);
        } else {
            throw new IllegalArgumentException("Actor must be an instance of type player to perform this action");
        }
    }

    /**
     * Returns a string showing the food item to be fed to the dinosaur at the location
     *
     * @param actor The actor performing the action.
     * @return String message
     */
    @Override
    public String menuDescription(Actor actor) {
        String message = actor + " feed " + food + " to " + dinosaur + " at (" + locationOfDinosaur.x() + ", " + locationOfDinosaur.y() + ")";
        return message;
    }

}
