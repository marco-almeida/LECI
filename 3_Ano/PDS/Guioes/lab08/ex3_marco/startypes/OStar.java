package lab08.ex3_marco.startypes;

import lab08.ex3_marco.StarFlyweight;

import java.awt.*;

public class OStar extends StarFlyweight{
    
    public OStar() {
        super(5, new Color(225, 250, 250));
        this.description = "This is a long description of the O type star....";
        // This would store the values of physical properties for each type of star
        this.physicalProperties = new Float[10];
    }
}
