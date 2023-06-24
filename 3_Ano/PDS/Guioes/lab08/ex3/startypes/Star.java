package lab08.ex3.startypes;

import java.awt.*;

public class Star {
    public int x;
    public int y;
    StarType type;
    
    public Star(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void setType(StarType type){
        this.type = type;
    }

    public void draw(Graphics g) {
        g.setColor(type.color);
        g.fillOval(x, y , type.size, type.size);
    }
}
