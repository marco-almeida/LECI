package lab07.CabazesCompras;

public abstract class Product implements Component {
    private final String name;
    private final double weight;

    protected Product(String name, double weight) {
        this.name = name;
        this.weight = weight;
    }

    @Override
    public double getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return String.format("%s '%s' - Weight : %.1f", this.getClass().getSimpleName(), name, weight);
    }
}
