package ementas;

import java.time.LocalDateTime;

public class Ementa {

    public static final int NR_PRATOS_NA_EMENTA = 4;

    private String nome;
    private String local;
    private Prato[] pratos;
    private int nrPratosAdicionados;
    private java.time.LocalDateTime dia;

    public Ementa(String nome, String local, LocalDateTime dia) {
        this.nome = nome;
        this.local = local;
        this.dia = dia;
        pratos = new Prato[NR_PRATOS_NA_EMENTA];
        nrPratosAdicionados = 0;
    }

    public Ementa(String nome, String local) {
        throw new UnsupportedOperationException();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("Ementa [nome=" + nome + ", local=" + local + ", dia " + dia + "]\n");
        for (Prato prato : pratos) {
            builder.append("\t");
            builder.append(prato.getNome());
            builder.append("\t");
            builder.append(prato.getPreco());
            builder.append("\n");
        }
        builder.append("]");
        return builder.toString();

    }

    public double getTotalEmenta() {
        double sum = 0;
        for (int i = 0; i < nrPratosAdicionados; i++) {
            sum += pratos[i].getPreco();
        }
        return sum;
    }

    public void addPrato(Prato prato) {
        pratos[nrPratosAdicionados] = prato;
        nrPratosAdicionados++;
    }

    public Prato listarPrato(int ordem) {
        return pratos[ordem];
    }

    public void addPrato(Prato p, Object o) {
        throw new UnsupportedOperationException();
    }

    public void listarPratos() {
        throw new UnsupportedOperationException();
    }
}
