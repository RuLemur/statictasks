package util;

import java.util.Random;

public class Randomizer {
    private static Random rnd = new Random();

    public static int getRandomInt(int from, int to) {
        return rnd.nextInt(to + 1 - from) + from;
    }

    public static double getRandomDouble(double from, double to) {
        double number = rnd.nextDouble() * (to - from) + from;
        return number;
    }

    public static boolean getRandomBooelan() {
        return rnd.nextBoolean();
    }

    public static boolean getRandomBooelan(int chanceTrue) {
        int chance = getRandomInt(1, 100);
        if (chance <= chanceTrue) {
            return true;
        } else {
            return false;
        }
    }
}
