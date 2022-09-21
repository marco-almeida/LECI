package aula07;

public class Bola extends ObjetoMovel {
    private String cor;

    public Bola(String cor) {
        this.cor = cor;
    }

    @Override
    public String toString() {
        return super.toString() + "Bola [cor=" + cor + "]";
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

}
