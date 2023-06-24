package lab08.ex3_marco.startypes;

import lab08.ex3_marco.StarFlyweight;

import java.awt.*;

public class GStar extends StarFlyweight{
    
    public GStar() {
        super(1, new Color(245, 250, 250));
        this.description = "This is a long description of the G type star....";
        // This would store the values of physical properties for each type of star
        this.physicalProperties = new Float[10];
    }
}
