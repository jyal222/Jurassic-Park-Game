package game;

import java.text.DecimalFormat;
import java.util.Random;

public class Rain {

    private boolean isRaining;
    private double rainFall;
    private static final Rain instance = new Rain();
    private int rainInterval;

    private Rain() {
        isRaining = false;
    }

    /**
     * Get instance of Rain
     *
     * @return Rain instance
     */
    public static Rain getInstance() {
        return instance;
    }

    /**
     * This raining method will fall rain.
     */
    public void raining() {
        Random random = new Random();
        rainInterval++;
        float min = 0.1f;
        float max = 0.6f;

        if (rainInterval == 10) {
            if (random.nextInt(100) + 1 <= 20) {
                isRaining = true;
                float randomFloat = min + random.nextFloat() * (max - min);
                DecimalFormat value = new DecimalFormat("#.#");
                rainFall = Double.parseDouble(value.format(randomFloat));
                rainInterval = 0;
            }

        }
    }

    /**
     * To get the random value for rainfall
     *
     * @return double rainfall
     */
    public double getRainFall() {
        return rainFall;
    }

    /**
     * Check if it is raining
     *
     * @return boolean
     */
    public boolean isRaining() {
        return isRaining;
    }
}
