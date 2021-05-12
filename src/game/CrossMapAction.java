package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

public class CrossMapAction extends Action {

    World world;
    ArrayList<GameMap> gameMaps;
    protected ActorLocations actorLocations = new ActorLocations();
    GameMap playersMap;

    public CrossMapAction(GameMap playersMap) {
        this.playersMap = playersMap;
    }

    @Override
    public String execute(Actor actor, GameMap map) {

        GameMap firstMap = this.getGameMaps().get(0);
        GameMap secondMap = this.getGameMaps().get(1);
        Location current = actorLocations.locationOf(actor);
        Location newLocation;
        boolean canCrossMaps = false;

        if(this.gameMaps.size() == 2){
            Player player = (Player) actor;
            // If player at northern border of map 1
            if(actorLocations.locationOf(player).map().equals(firstMap) && actorLocations.locationOf(player).y() == 0) {
                canCrossMaps = true;
                // Same x at southern border of map2
                newLocation = secondMap.at(current.x(), secondMap.getYRange().max());
                if(newLocation.canActorEnter(player)){
                    firstMap.removeActor(player);
                    secondMap.addActor(player, newLocation);
                    world.addPlayer(player, newLocation);
                    playersMap = actorLocations.locationOf(player).map();
                }
            } // If player at southern border of map2
            else if (actorLocations.locationOf(player).map().equals(secondMap) && actorLocations.locationOf(player).y() == 24){
                // Same x at northern border of map1
                newLocation = firstMap.at(current.x(), secondMap.getYRange().min());
                if (newLocation.canActorEnter(player)){
                    secondMap.removeActor(player);
                    firstMap.addActor(player, newLocation);
                    world.addPlayer(player, newLocation);
                    playersMap = actorLocations.locationOf(player).map();
                }
            }

        }
        return menuDescription(actor);
    }

    @Override
    public String menuDescription(Actor actor) {
        return "Cross over to the second map";
    }

    public World getWorld() {
        return world;
    }

    public void setWorld(World world) {
        this.world = world;
    }

    public ArrayList<GameMap> getGameMaps() {
        return gameMaps;
    }

    public void setGameMaps(ArrayList<GameMap> gameMaps) {
        this.gameMaps = gameMaps;
    }
}
