package lab08.ex3_marco.startypes;

import lab08.ex3_marco.StarFlyweight;

import java.awt.*;

public class FStar extends StarFlyweight{
    
    public FStar() {
        super(2, new Color(255, 255, 245));
        this.description = "This is a long description of the F type star....";
        // This would store the values of physical properties for each type of star
        this.physicalProperties = new Float[10];
    }
}
