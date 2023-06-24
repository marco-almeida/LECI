package lab08.ex3_marco;

import java.awt.*;

public class Star {
    private final StarFlyweight flyweight;
    private final int x;
    private final int y;

    public Star(char c, int x, int y) {
        this.flyweight = StarFlyweightFactory.getStarFlyweight(c);
        this.x = x;
        this.y = y;
    }

    public void draw(Graphics g) {
        flyweight.draw(g, x, y);
    }

}
