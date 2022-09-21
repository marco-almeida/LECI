public class Produto implements Rateable {
    private double preco;
    private int avaliacao;
    private String nome;
    private String maisInfo;

    public Produto(double preco, int avaliacao, String nome, String info) {
        this.preco = preco;
        this.avaliacao = avaliacao;
        this.nome = nome;
        this.maisInfo = info;
    }

    @Override
    public int getAvaliacao() {
        return 0;
    }

    @Override
    public void rate() {
    }

}
