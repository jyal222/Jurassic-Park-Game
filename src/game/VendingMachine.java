package game;

import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Item;

import java.util.ArrayList;

public class VendingMachine extends Ground {
    ArrayList<Item> items;

    /**
     * A VendingMachine constructor
     * Items are being added into items to be sold
     */
    public VendingMachine() {
        super('V');
        this.items = new ArrayList<Item>();
        this.items.add(new Fruit());
        this.items.add(new MealKit("vegetarian"));
        this.items.add(new MealKit("carnivore"));
        this.items.add(new Egg("stegosaur"));
        this.items.add(new Egg("brachiosaur"));
        this.items.add(new Egg("allosaur"));
        this.items.add(new LaserGun());
    }

    /**
     * To check if an actor can enter this specific location
     * @param actor Only players can enter a vending machine
     * @return boolean
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * To get the arraylist of items that are available at a vending machine
     * @return all of the items that are available for player at a vending machine
     */
    public ArrayList<Item> getItems() {
        return items;
    }

    /**
     * Sets the arraylist of items that will be sold at a vending machine
     * @param items an array list of Items to be sold
     */
    public void setItems(ArrayList<Item> items) {
        this.items = items;
    }
}
