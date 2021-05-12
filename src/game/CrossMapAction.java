package game;

import edu.monash.fit2099.engine.*;

import java.util.ArrayList;

public class CrossMapAction extends Action {

    World world;
    ArrayList<GameMap> worldMaps;
    protected ActorLocations actorLocations = new ActorLocations();
    GameMap playersMap;

    @Override
    public String execute(Actor actor, GameMap map) {
        GameMap firstMap = this.getWorldMaps().get(0);
        GameMap secondMap = this.getWorldMaps().get(1);
        Location current = actorLocations.locationOf(actor);
        Location newLocation;
        // If player at northern border of map 1
        if(actorLocations.locationOf(actor).map().equals(firstMap) && actorLocations.locationOf(actor).y() == 0){
            // Remove actor from map1
            firstMap.removeActor(actor);

            // Same x at southern border of map2
            newLocation = secondMap.at(current.x(), secondMap.getYRange().max());
            if(newLocation.canActorEnter(actor)){
                firstMap.removeActor(actor);
                secondMap.addActor(actor, newLocation);
                actorLocations = secondMap.actorsL;
                playersMap = actorLocations.locationOf(actor).map();
            }

            // If player at southern border of map2
        } else if (actorLocations.locationOf(actor).map().equals(secondMap) && actorLocations.locationOf(actor).y() == 24){

            // Remove actor from map2
            secondMap.removeActor(actor);

            // Same x at northern border of map1
            newLocation = firstMap.at(current.x(), secondMap.getYRange().min());
            if (newLocation.canActorEnter(actor)){
                secondMap.removeActor(actor);
                firstMap.addActor(actor, newLocation);
                actorLocations = firstMap.actorLocations;
                playersMap = actorLocations.locationOf(actor).map();
            }
        }
        return menuDescription(actor);
        return null;
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

    public ArrayList<GameMap> getWorldMaps() {
        return worldMaps;
    }

    public void setWorldMaps(ArrayList<GameMap> worldMaps) {
        this.worldMaps = worldMaps;
    }
}
