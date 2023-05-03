package lab08.ex3.startypes;

import java.awt.Color;

public class FStar extends StarType{
    
    public FStar() {
        super(2, new Color(255, 255, 245));
        this.description = "This is a long description of the F type star....";
        // This would store the values of physical properties for each type of star
        this.physicalProperties = new Float[10];
    }
}
