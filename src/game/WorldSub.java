package game;

import edu.monash.fit2099.engine.*;

public class WorldSub extends World {

    private Rain rain = Rain.getInstance();
    /**
     * Constructor.
     *
     * @param display the Display that will display this World.
     */

    public WorldSub(Display display) {
        super(display);
        rain.setRainInterval(0);
    }

    @Override
    public void run() {
        if (player == null)
            throw new IllegalStateException();

        // initialize the last action map to nothing actions;
        for (Actor actor : actorLocations) {
            lastActionMap.put(actor, new DoNothingAction());
        }

        // This loop is basically the whole game
        while (stillRunning()) {
            GameMap playersMap = actorLocations.locationOf(player).map();
            playersMap.draw(display);
            rain.raining();

            // Process all the actors.
            for (Actor actor : actorLocations) {
                if (stillRunning())
                    processActorTurn(actor);
            }



            // Tick over all the maps. For the map stuff.
            for (GameMap gameMap : gameMaps) {
                gameMap.tick();
            }

        }
        display.println(endGameMessage());
    }
}
