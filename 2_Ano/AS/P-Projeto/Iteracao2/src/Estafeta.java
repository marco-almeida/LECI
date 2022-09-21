public class Estafeta extends Fornecedor implements Rateable {
    private int avaliacao;

    public Estafeta(String nome, String instituicao, String password, int numTelemovel, String localizacao) {
        super(nome, instituicao, password, numTelemovel, localizacao);
    }

    @Override
    public int getAvaliacao() {
        return this.avaliacao;
    }

    @Override
    public void rate() {
    }
    
}
