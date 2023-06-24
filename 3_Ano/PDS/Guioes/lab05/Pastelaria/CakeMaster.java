package lab05.Pastelaria;

public class CakeMaster {
    CakeBuilder cakeBuilder;

    public void setCakeBuilder(CakeBuilder cakeBuilder){
        this.cakeBuilder = cakeBuilder;
    }

    public void createCake(String message){
        createCake(1, message);
    }

    public void createCake(int numLayers, String message){
        this.cakeBuilder.createCake();
        for(int i=0; i<numLayers; i++){
            this.cakeBuilder.addCakeLayer();
            this.cakeBuilder.addCreamLayer();
        }
        this.cakeBuilder.addTopLayer();
        this.cakeBuilder.addTopping();
        this.cakeBuilder.addMessage(message);
    }

    public void createCake(Shape shape, int numLayers, String message){
        createCake(numLayers, message);
        this.cakeBuilder.setCakeShape(shape);
    }

    public Cake getCake(){
        return this.cakeBuilder.getCake();
    }
    
}
