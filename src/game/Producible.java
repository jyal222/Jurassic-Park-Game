package game;

import java.util.List;

/**
 * See if the fruit is producible by ground.
 */
public interface Producible {
    List<Food> getFood(); // List of food

    boolean removeFood(Food foodToRemove); // Food to be removed

    boolean removeAllFood(List<Food> foodsToRemove); // Foods to be removed (all)
}
