package game;

import edu.monash.fit2099.engine.ActorLocations;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;

import java.util.List;

public class GameMapSub extends GameMap {



    /**
     * Constructor for GameMapSub Class
     *
     * @param groundFactory
     * @param lines
     */
    public GameMapSub(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }


    /**
     * To get actor locations
     *
     * @return ActorLocations actorLocations
     */
    public ActorLocations getActorLocations() {
        return actorLocations;
    }

}
