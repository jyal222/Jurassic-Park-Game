package game;

import java.util.List;

/**
 * See if the fruit is producible by ground.
 */
public interface Producible {
    List<Food> getFruits();

    boolean removeFruit(Food fruitToRemove);

    boolean removeFruits(List<Food> fruitsToRemove);
}
