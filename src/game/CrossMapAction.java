package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

public class CrossMapAction extends Action {

    private World world;
    ArrayList<GameMap> gameMaps;
    protected ActorLocations actorLocations = new ActorLocations();
    GameMap playersMap;

    /**
     * Constructor for CrossMapAction
     *
     * @param playersMap
     */
    public CrossMapAction(GameMap playersMap) {
        this.playersMap = playersMap;
    }

    /**
     * This method is to let player to perform cross map action
     *
     * @param actor The actor performing the action.
     * @param map   The map the actor is on.
     * @return
     */
    @Override
    public String execute(Actor actor, GameMap map) {

        GameMap firstMap = this.getGameMaps().get(0);
        GameMap secondMap = this.getGameMaps().get(1);
        Location current = actorLocations.locationOf(actor);
        Location newLocation;
        boolean canCrossMaps = false;

        if (this.gameMaps.size() == 2) {
            Player player = (Player) actor;
            // If player at northern border of map 1
            if (actorLocations.locationOf(player).map().equals(firstMap) && actorLocations.locationOf(player).y() == 0) {
                canCrossMaps = true;
                // Same x at southern border of map2
                newLocation = secondMap.at(current.x(), secondMap.getYRange().max());
                if (newLocation.canActorEnter(player)) {
                    firstMap.removeActor(player);
                    secondMap.addActor(player, newLocation);
                    world.addPlayer(player, newLocation);
                    playersMap = actorLocations.locationOf(player).map();
                }
            } // If player at southern border of map2
            else if (actorLocations.locationOf(player).map().equals(secondMap) && actorLocations.locationOf(player).y() == 24) {
                // Same x at northern border of map1
                newLocation = firstMap.at(current.x(), secondMap.getYRange().min());
                if (newLocation.canActorEnter(player)) {
                    secondMap.removeActor(player);
                    firstMap.addActor(player, newLocation);
                    world.addPlayer(player, newLocation);
                    playersMap = actorLocations.locationOf(player).map();
                }
            }

        }
        return menuDescription(actor);
    }

    /**
     * This method is to print a line showing player has crossed over to e second map
     *
     * @param actor The actor performing the action.
     * @return
     */
    @Override
    public String menuDescription(Actor actor) {
        return "Cross over to the second map";
    }

    /**
     * To get world
     *
     * @return World world
     */
    public World getWorld() {
        return world;
    }

    /**
     * To set world
     *
     * @param world
     */
    public void setWorld(World world) {
        this.world = world;
    }

    /**
     * Get the ArrayList of GameMap
     *
     * @return ArrayList gameMaps
     */
    public ArrayList<GameMap> getGameMaps() {
        return gameMaps;
    }

    /**
     * Set the ArrayList of GameMap
     *
     * @param gameMaps
     */
    public void setGameMaps(ArrayList<GameMap> gameMaps) {
        this.gameMaps = gameMaps;
    }
}
