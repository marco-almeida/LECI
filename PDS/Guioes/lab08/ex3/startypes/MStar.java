package lab08.ex3.startypes;

import java.awt.Color;

public class MStar extends StarType{
    
    public MStar() {
        super(1, Color.RED);
        this.description = "This is a long description of the M type star....";
        // This would store the values of physical properties for each type of star
        this.physicalProperties = new Float[10];
    }
}
