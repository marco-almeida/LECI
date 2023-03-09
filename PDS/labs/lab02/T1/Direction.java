import java.util.Random;

public enum Direction {
    UP,
    DOWN,
    LEFT,
    RIGHT,
    UPLEFT,
    UPRIGHT,
    DOWNLEFT,
    DOWNRIGHT;

    private static final Random PRNG = new Random();

    public static Direction getRandomDirection() {
        return values()[PRNG.nextInt(values().length)];
    }

}