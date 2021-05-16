package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;
import java.util.List;

/**
 * A vending machine class.
 */
public class VendingMachine extends Ground {

    List<Item> items = new ArrayList<>();

    /**
     * A VendingMachine constructor
     * Items are being added into items to be sold
     */
    public VendingMachine() {
        super('V');
        this.items.add(new Fruit(20));
        this.items.add(new VegetarianMealKit());
        this.items.add(new CarnivoreMealKit());
        this.items.add(new Egg(new Stegosaur()));
        this.items.add(new Egg(new Brachiosaur()));
        this.items.add(new Egg(new Allosaur()));
        this.items.add(new Egg(new Pterodactyls()));
        this.items.add(new LaserGun());
    }

    /**
     * To check if an actor can enter this specific location
     *
     * @param actor Only players can enter a vending machine
     * @return boolean
     */
    @Override
    public boolean canActorEnter(Actor actor) {
        return false;
    }

    /**
     * Returns an Action list.
     *
     * @param actor     the Actor acting
     * @param location  the current Location
     * @param direction the direction of the Ground from the Actor
     * @return Actions actions
     */
    @Override
    public Actions allowableActions(Actor actor, Location location, String direction) {
        Actions actions = new Actions();
        if (actor instanceof Player) {
            Player player = (Player) actor;
            for (Item item : items) {
                if (player.canSpend(item.getPrice())) {
                    actions.add(new BuyItemsAction(item));
                }
            }
        }
        return actions;
    }

    /**
     * Check if the location can be drink
     *
     * @return boolean
     */
    @Override
    public boolean canDrink() {
        return false;
    }

    /**
     * Check if the dinosaur can stand on the location
     *
     * @return boolean
     */
    @Override
    public boolean canDinosaurStand() {
        return false;
    }

    /**
     * To set the dinosaur
     *
     * @param actor
     */
    @Override
    public void setDinosaur(Actor actor) {

    }
}
