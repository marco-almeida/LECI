public abstract class ConcreteServico implements Servico {
    protected String name;
    protected String description;
    protected double price;

    protected ConcreteServico(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }

    @Override
    public double price() {
        return price;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + "[" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ']';
    }
}
