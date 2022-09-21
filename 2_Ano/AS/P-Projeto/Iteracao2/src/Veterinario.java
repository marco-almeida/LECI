public class Veterinario extends Fornecedor implements Rateable {
    private int avaliacao;

    public Veterinario(String nome, String instituicao, String password, int numTelemovel, String localizacao) {
        super(nome, instituicao, password, numTelemovel, localizacao);
        this.avaliacao = 0;
    }

    @Override
    public int getAvaliacao() {
        return this.avaliacao;
    }

    @Override
    public void rate() {
        
    }

}
