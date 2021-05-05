package game;

import edu.monash.fit2099.engine.Ground;
import edu.monash.fit2099.engine.Location;

import java.util.List;
import java.util.Random;

public class Bush extends Ground {

    /**
     * Constructor for Bush.
     */
    public Bush() {
        super('^');
    }

    @Override
    public void tick(Location location) {
        super.tick(location);

        Random random = new Random();

        if (random.nextInt(100) + 1 <= 10) {
            Fruit ripeFruit = new Fruit();
            location.addItem(ripeFruit);
        }
    }

}