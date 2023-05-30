import java.util.ArrayList;
import java.util.List;

public class PacoteServicos implements Servico {
    private String name;
    private String description;

    private List<Servico> servicos;

    public PacoteServicos(String name, String description) {
        this.name = name;
        this.description = description;
        servicos = new ArrayList<>();
    }

    public void add(Servico s) {
        servicos.add(s);
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public String description() {
        return description;
    }

    private int getDiscount() {
        int differentServices = (int) servicos.stream().map(Servico::type).distinct().count();
        return differentServices > 3 ? 10 : differentServices * 3;
    }

    @Override
    public double price() {
        double basePrice = servicos.stream().mapToDouble(Servico::price).sum();
        int discount = getDiscount();
        return basePrice * (1 - discount / 100.0);
    }

    @Override
    public TipoServico type() {
        return TipoServico.PACOTE_SERVICOS;
    }

    @Override
    public String toString() {
        int differentServices = (int) servicos.stream().map(Servico::type).distinct().count();
        return String.format("Pacote com %d serviços de %d tipos. Preco (desconto = %d%%): %.2f", servicos.size(), differentServices, getDiscount(), price());
    }
}
