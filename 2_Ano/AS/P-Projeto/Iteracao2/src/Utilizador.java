import java.util.List;

public class Utilizador {
    private String nome;
    private Data dataNascimento;
    private String localizacao;
    private String email;
    private List<Animal> perfis;
    private Data dataInsc;
    private Carrinho carrinho;

    public Utilizador(String nome, Data dataNascimento, String localizacao, String email, List<Animal> perfis,
            Data dataInsc) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.localizacao = localizacao;
        this.email = email;
        this.perfis = perfis;
        this.dataInsc = dataInsc;
    }

}
