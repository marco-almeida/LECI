package ementas;

public class OvoLacto extends Alimento {
    private String nome;

    public OvoLacto(String nome, double proteinas, double calorias, double peso) {
        super(proteinas, calorias, peso);
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    @Override
    public String toString() {
        return "OvoLacto [nome=" + nome + "; " + super.toString() + "]";
    }
}