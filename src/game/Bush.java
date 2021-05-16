package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bush extends Ground implements Producible {

    private List<Food> fruits = new ArrayList<>();

    /**
     * Constructor for Bush.
     */
    public Bush() {
        super('^');
    }

    /**
     * This method is to produce fruit from bush based of the probability.
     *
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);

        Random random = new Random();

        if (random.nextInt(100) + 1 <= 10) {
            fruits.add(new Fruit(10));
        }
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
        Actions actions = new Actions();
        if (location.map().locationOf(actor) == location) {
            actions.add(new PickFruitAction(this));
        }
        return actions;
    }

    /**
     * To get a list of fruits in a tree.
     *
     * @return a list of fruit in the tree.
     */
    @Override
    public List<Food> getFood() {
        return fruits;
    }

    /**
     * TO remove fruit from the list
     *
     * @param foodToRemove fruit
     * @return boolean
     */
    @Override
    public boolean removeFood(Food foodToRemove) {
        return fruits.remove(foodToRemove);
    }

    @Override
    public boolean removeAllFood(List<Food> foodsToRemove) {
        return fruits.removeAll(foodsToRemove);
    }

    @Override
    public String toString() {
        return "Bush";
    }

    @Override
    public boolean canDrink() {
        return false;
    }
}
