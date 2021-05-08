package game;

import edu.monash.fit2099.engine.Action;
import edu.monash.fit2099.engine.Actor;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import game.Player;

public class BuyItemsAction extends Action {

    private Item item;

    /**
     * Constructor
     *
     * @param item
     */
    public BuyItemsAction(Item item) {
        this.item = item;
    }

    /**
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {
        if (actor instanceof Player) {
            Player player = (Player) actor;
            String message = "Eco Points: " + player.getEcoPoints();
            if (player.canSpend(item.getPrice())) {
                player.addItemToInventory(item);
                player.spend(item.getPrice());
                message += System.lineSeparator() + "Eco Points after purchasing: " + player.getEcoPoints();
            } else {
                message += System.lineSeparator() + "Not enough eco points for " + item;
            }
            return message;
        } else {
            throw new IllegalArgumentException("Actor must be an instance of type player to perform this action");
        }
    }

    /**
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Buy " + item + " from Vending Machine (" + item.getPrice() + " ecopoints)";
    }

}
