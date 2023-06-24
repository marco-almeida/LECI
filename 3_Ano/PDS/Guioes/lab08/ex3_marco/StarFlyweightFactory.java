package lab08.ex3_marco;

import lab08.ex3_marco.startypes.*;

import java.util.HashMap;
import java.util.Map;

public class StarFlyweightFactory {
    private static final Map<Character, StarFlyweight> cache = new HashMap<>();

    private StarFlyweightFactory() {
    }

    public static StarFlyweight getStarFlyweight(char c) {
        StarFlyweight s = cache.get(c);
        if (s != null) {
            return s;
        }
        s = switch (c) {
            case 'A' -> new AStar();
            case 'B' -> new BStar();
            case 'F' -> new FStar();
            case 'G' -> new GStar();
            case 'K' -> new KStar();
            case 'M' -> new MStar();
            default -> new OStar();
            // OStar() as default
        };
        cache.put(c, s);
        return s;
    }

}
