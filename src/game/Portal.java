package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Item;
import edu.monash.fit2099.engine.Location;
import edu.monash.fit2099.engine.MoveActorAction;

public class Portal extends Item {

    /**
     * Constructor.
     */
    public Portal(GameMap destMap, Location currentLocation, String direction) {
        super("Portal", 'O', false);

        if (direction.equals("north")) {
            allowableActions.add(new MoveActorAction(destMap.at(currentLocation.x(), destMap.getYRange().max()), "to second Map!"));
        } else if (direction.equals("south")) {
            allowableActions.add(new MoveActorAction(destMap.at(currentLocation.x(), destMap.getYRange().min()), "to first Map!"));
        }

    }

    /**
     * To get price of the item
     * @return int 0
     */
    @Override
    public int getPrice() {
        return 0;
    }
}
