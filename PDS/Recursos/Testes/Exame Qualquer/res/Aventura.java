public class Aventura extends ConcreteServico{
    protected Aventura(String name, String description, double price) {
        super(name, description, price);
    }

    @Override
    public TipoServico type() {
        return TipoServico.AVENTURA;
    }
}
