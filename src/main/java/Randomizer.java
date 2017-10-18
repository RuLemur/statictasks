import java.util.Random;

public class Randomizer {

    public static int getRandomInt(int from, int to) {
        Random rnd = new Random();

        return rnd.nextInt(to - from) + from;
    }
}
