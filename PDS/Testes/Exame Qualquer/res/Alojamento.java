public class Alojamento extends ConcreteServico {
    private int maxOccupation;
    protected Alojamento(String name, String description, double price, int maxOccupation) {
        super(name, description, price);
        this.maxOccupation = maxOccupation;
    }

    public int getMaxOccupation() {
        return maxOccupation;
    }

    @Override
    public TipoServico type() {
        return TipoServico.ALOJAMENTO;
    }

    @Override
    public String toString() {
        return "Alojamento[" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", maxOccupation=" + maxOccupation +
                ']';
    }
}
