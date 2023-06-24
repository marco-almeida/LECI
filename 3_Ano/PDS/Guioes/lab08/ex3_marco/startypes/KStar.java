package lab08.ex3_marco.startypes;

import lab08.ex3_marco.StarFlyweight;

import java.awt.*;

public class KStar extends StarFlyweight{
    
    public KStar() {
        super(1, new Color(230, 160, 10));
        this.description = "This is a long description of the K type star....";
        // This would store the values of physical properties for each type of star
        this.physicalProperties = new Float[10];
    }
}
