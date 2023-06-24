package lab08.ex3;
import java.util.HashMap;
import lab08.ex3.startypes.*;
public class FlyweightFactory {
    
    private static final HashMap<Character,StarType> startypes = new HashMap<>();
    private static final int CANVAS_SIZE = 1200;

    public static Star createStar(char type) {
        int x = random(0, CANVAS_SIZE);
        int y = random(0, CANVAS_SIZE);

        Star star = new Star(x, y);
        StarType startype = startypes.get(type);
        //System.out.println(star);
        if (startype == null) {
            switch (type) {
                case 'O' : startype = new OStar(); break;
                case 'A' : startype = new AStar(); break;
                case 'B' : startype = new BStar(); break;
                case 'F' : startype = new FStar(); break;
                case 'G' : startype = new GStar(); break;
                case 'K' : startype = new KStar(); break;
                case 'M' : startype = new MStar(); break;
            }
            startypes.put(type, startype);
        }
        star.setType(startype);

        return star;
    }

    private static int random(int min, int max) {
        return min + (int) (Math.random() * ((max - min) + 1));
    }

}