package lab05.Pastelaria;

public class SpongeCakeBuilder implements CakeBuilder{
    private Cake cake;

    public void setCakeShape(Shape shape){this.cake.setShape(shape);}
    public void addCakeLayer(){this.cake.addCakeLayer();}
    public void addCreamLayer(){this.cake.addCreamLayer(Cream.Vanilla);}
    public void addTopLayer(){this.cake.addTopLayer(Cream.Whipped_Cream);}
    public void addTopping(){this.cake.addTopping(Topping.Fruit);}
    public void addMessage(String m){this.cake.setMessage(m);}
    public void createCake(){this.cake = new Cake("Sponge");}
    public Cake getCake(){return this.cake;}
}
