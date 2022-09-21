package aula07;

import java.util.Arrays;

public class Equipa {
    private String nome;
    private String nomeResponsavel;
    private int golosMarcados = 0;
    private int golosSofridos = 0;
    private Robo[] robos;

    public Equipa(String nome, String nomeResponsavel, Robo[] robos) {
        this.nome = nome;
        this.nomeResponsavel = nomeResponsavel;
        this.robos = robos;
    }

    @Override
    public String toString() {
        return "Equipa [golosMarcados=" + golosMarcados + ", golosSofridos=" + golosSofridos + ", nome=" + nome
                + ", nomeResponsavel=" + nomeResponsavel + ", robos=" + Arrays.toString(robos) + "]";
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNomeResponsavel() {
        return nomeResponsavel;
    }

    public void setNomeResponsavel(String nomeResponsavel) {
        this.nomeResponsavel = nomeResponsavel;
    }

    public int getGolosMarcados() {
        return golosMarcados;
    }

    public void setGolosMarcados(int golosMarcados) {
        this.golosMarcados = golosMarcados;
    }

    public int getGolosSofridos() {
        return golosSofridos;
    }

    public void setGolosSofridos(int golosSofridos) {
        this.golosSofridos = golosSofridos;
    }

    public Robo[] getRobos() {
        return robos;
    }

    public void setRobos(Robo[] robos) {
        this.robos = robos;
    }

}
