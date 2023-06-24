public class Transporte extends ConcreteServico {
    protected Transporte(String name, String description, double price) {
        super(name, description, price);
    }

    @Override
    public TipoServico type() {
        return TipoServico.TRANSPORTE;
    }
}
