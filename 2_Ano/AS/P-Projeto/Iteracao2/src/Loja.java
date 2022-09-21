import java.util.List;

public class Loja extends Fornecedor {
    private List<Produto> produtos;

    public Loja(String nome, String instituicao, String password, int numTelemovel, String localizacao,
            List<Produto> produtos) {
        super(nome, instituicao, password, numTelemovel, localizacao);
        this.produtos = produtos;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void removeOrAddProduto() {

    }

}
