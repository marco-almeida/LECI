package lab08.ex3.startypes;

import java.awt.*;

public abstract class StarType {

    public int size;
    public Color color;
    protected String description;
    protected Float[] physicalProperties;

    public StarType(int size, Color color) {
        this.size = size;
        this.color = color;
    }

}

