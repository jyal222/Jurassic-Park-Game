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
        rainInterval = 0;
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
        System.out.println("rain interval = " + rainInterval);
        float min = 0.1f;
        float max = 0.6f;

        if (rainInterval == 10) {
            rainInterval = 0;
            String msg;

            if (random.nextInt(100) + 1 <= 20) {
                isRaining = true;
                msg = "It's raining!";

                float randomFloat = min + random.nextFloat() * (max - min);
                DecimalFormat value = new DecimalFormat("#.#");
                rainFall = Double.parseDouble(value.format(randomFloat));
            } else {
                msg = "It's not raining!";

            }
            System.out.println(msg);
        }
    }

    /**
     * To set the rain interval
     * @param rainInterval
     */
    public void setRainInterval(int rainInterval) {
        this.rainInterval = rainInterval;
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
