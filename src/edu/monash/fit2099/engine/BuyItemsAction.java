package edu.monash.fit2099.engine;

import game.Player;

public class BuyItemsAction extends Action {

    Item item;

    /**
     * Constructor
     * @param item
     */
    public BuyItemsAction(Item item){
        this.item = item;
    }

    /**
     *
     * @param actor The actor performing the action.
     * @param map The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Player) {
            Item item = this.getItem();
            System.out.println("Eco Points: " + ((Player) actor).getEcoPoints());
            if (((Player) actor).getEcoPoints() >= item.getPrice()){
                actor.addItemToInventory(item);
                ((Player) actor).setEcoPoints(((Player) actor).getEcoPoints() - item.getPrice());
                System.out.println("Eco Points: " + ((Player) actor).getEcoPoints());
            }
            else {
                return ("Not enough eco points for " + item.getName());
            }
        } else {
            throw new IllegalArgumentException("Actor must be an instance of type player to perform this action");
        }
        return menuDescription(actor);
    }

    /**
     *
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        String message;
        message = "Buy ";
        message += item.getName() + " from Vending Machine (" + Integer.toString(item.getPrice()) + " ecopoints)";
        return message;
    }

    /**
     *
     * @return
     */
    public Item getItem() {
        return item;
    }

    /**
     *
     * @param item
     */
    public void setItem(Item item) {
        this.item = item;
    }
}
