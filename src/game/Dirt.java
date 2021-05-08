package game;

import edu.monash.fit2099.engine.*;


import java.util.Random;

/**
 * A class that represents bare dirt.
 */
public class Dirt extends Ground {

    public Dirt() {
        super('.');
    }

    /**
     * This method is to tick all the dirts in the map.
     * @param location The location of the Ground
     */
    @Override
    public void tick(Location location) {
        super.tick(location);


        int noAdjacentBush = 0;
        int noAdjacentTree = 0;


        for (Exit ext: location.getExits()) {
            if ( ext.getDestination().getGround() instanceof Bush) noAdjacentBush++;
            if ( ext.getDestination().getGround() instanceof Tree) noAdjacentTree++;
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
