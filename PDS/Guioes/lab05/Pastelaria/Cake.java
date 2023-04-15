package lab05.Pastelaria;

public class Cake {
    private Shape shape;
    private String cakeLayer;
    private int numCakeLayers;
    private Cream midLayerCream;
    private Cream topLayerCream;
    private Topping topping;
    private String message;

    public Cake(String cakeLayer){
        this.numCakeLayers = 0;
        this.cakeLayer = cakeLayer;
    }

    @Override
    public String toString() {
        return String.format("%s cake with %d layers, topped with %s and %s. Message says: %s",cakeLayer, numCakeLayers, topLayerCream, topping, message);
    }

    public void setShape(Shape shape){ this.shape = shape; }

    public void addTopping(Topping topping) { this.topping = topping; }

    public void setMessage(String message){ this.message = message; }

    public void addTopLayer(Cream topLayerCream){ this.topLayerCream = topLayerCream; }

    public void addCreamLayer(Cream creamLayer){ this.midLayerCream = creamLayer; }

    public void addCakeLayer(){ this.numCakeLayers += 1; }
}