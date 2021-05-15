package game;

import java.text.DecimalFormat;
import java.util.Random;

public class Rain{
    private boolean isRaining;
    private double rainFall;
    private static final Rain instance= new Rain();
    private int rainInterval;

    private Rain(){
        isRaining = false;
    }

    public static Rain getInstance(){
        return instance;
    }

    public void raining(){
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
                rainInterval=0;
            }

        }
    }

    public double getRainFall() {
        return rainFall;
    }

    public boolean isRaining() {
        return isRaining;
    }
}
