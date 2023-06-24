package lab08.ex3_marco;

import java.awt.*;

public abstract class StarFlyweight {
    // repeating state here
    private final int size;
    private final Color color;
    protected String description;
    protected Float[] physicalProperties;

    protected StarFlyweight(int size, Color color) {
        this.size = size;
        this.color = color;
    }

    public void draw(Graphics g, int x, int y) {
        g.setColor(color);
        g.fillOval(x, y, size, size);
    }
}
