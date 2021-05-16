package game;

import java.util.List;

/**
 * See if the fruit is producible by ground.
 */
public interface Producible {
    List<Food> getFood();

    boolean removeFood(Food foodToRemove);

    boolean removeAllFood(List<Food> foodsToRemove);
}
