package lab08.ex3_marco.startypes;

import lab08.ex3_marco.StarFlyweight;

import java.awt.*;

public class BStar extends StarFlyweight {
    
    public BStar() {
        super(5, new Color(230, 252, 252));
        this.description = "This is a long description of the B type star....";
        // This would store the values of physical properties for each type of star
        this.physicalProperties = new Float[10];
    }
}
