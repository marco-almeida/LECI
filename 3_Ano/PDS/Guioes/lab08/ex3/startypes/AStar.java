package lab08.ex3.startypes;
import java.awt.Color;

public class AStar extends StarType{
    
    public AStar() {
        super(2, Color.WHITE);
        this.description = "This is a long description of the A type star....";
        // This would store the values of physical properties for each type of star
        this.physicalProperties = new Float[10];
    }
}
