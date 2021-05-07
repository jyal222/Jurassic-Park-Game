package game;

import edu.monash.fit2099.engine.Exit;
import edu.monash.fit2099.engine.GameMap;
import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

    public Dirt() {
        super('.');
    }

    @Override
    public void tick(Location location) {
        super.tick(location);

        ArrayList<Exit> adjLocation = new ArrayList<>();
        
        /*
        // Get adjacent locations
        adjLocation = new ArrayList();

        // Append adjacent locations
        if (location.locationValid(location.x(), location.y() - 1)) {
            adjLocation.add(location.getNorth());
        }
        if (location.locationValid(location.x(), location.y() + 1)) {
            adjLocation.add(location.getSouth());
        }
        if (location.locationValid(location.x() + 1, location.y())) {
            adjLocation.add(location.getEast());
        }
        if (location.locationValid(location.x() - 1, location.y())) {
            adjLocation.add(location.getWest());
        }

         */

        int noAdjacentBush = 0;
        int noAdjacentTree = 0;

        for (Exit lct : adjLocation) {
            if (lct.getDestination(map, location.x(), location.y())) {

                noAdjacentBush += 1;
            } else if (lct.getGround() instanceof Tree) {
                noAdjacentTree += 1;
            }
        }

        Random random = new Random();

        // If no tree in adjacent location, 1% to grow bush
        if (noAdjacentTree == 0) {
            if (random.nextInt(100) + 1 <= 1) {
                location.setGround(new Bush());
            }
            // If at least 2 squares of bush in adjacent location, 10% to grow bush
            if (noAdjacentBush >= 2) {
                if (random.nextInt(100) + 1 <= 10) {
                    location.setGround(new Bush());
                }

            }
        }

    }


}
