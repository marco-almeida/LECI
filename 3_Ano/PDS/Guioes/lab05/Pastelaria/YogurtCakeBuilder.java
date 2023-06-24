package lab05.Pastelaria;

public class YogurtCakeBuilder implements CakeBuilder{
    private Cake cake;

    public void setCakeShape(Shape shape){this.cake.setShape(shape);}
    public void addCakeLayer(){this.cake.addCakeLayer();}
    public void addCreamLayer(){this.cake.addCreamLayer(Cream.Vanilla);}
    public void addTopLayer(){this.cake.addTopLayer(Cream.Red_Berries);}
    public void addTopping(){this.cake.addTopping(Topping.Chocolate);}
    public void addMessage(String m){this.cake.setMessage(m);}
    public void createCake(){this.cake = new Cake("Yogurt");}
    public Cake getCake(){return this.cake;}
}
