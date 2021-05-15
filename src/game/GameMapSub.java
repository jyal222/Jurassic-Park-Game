package game;

import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.GroundFactory;

import java.util.List;

public class GameMapSub extends GameMap {

    private Rain rain = Rain.getInstance();

    public GameMapSub(GroundFactory groundFactory, List<String> lines) {
        super(groundFactory, lines);
    }


    @Override
    public void tick() {
        rain.raining();
        super.tick();
    }
}
