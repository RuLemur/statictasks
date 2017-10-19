import java.text.DecimalFormat;
import java.util.Random;

public class Randomizer {

    public static int getRandomInt(int from, int to) {
        Random rnd = new Random();

        return rnd.nextInt(to - from) + from;
    }


    public static double getRandomDouble(double from, double to) {
        Random rnd = new Random();

        double number = rnd.nextDouble() * (to - from) + from;
//        number = (double) Math.round(number * 100d) / 100d;
        return number;
    }
}
