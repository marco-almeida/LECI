package aula07;

import java.util.Arrays;

public class Jogo {
    private Equipa[] equipas;
    private Bola bola;
    private int duracao;
    private int tempoDecorrido = 0;
    
    public Jogo(Equipa[] equipas, Bola bola, int duracao) {
        this.equipas = equipas;
        this.bola = bola;
        this.duracao = duracao;
    }

    @Override
    public String toString() {
        return "Jogo [bola=" + bola + ", duracao=" + duracao + ", equipas=" + Arrays.toString(equipas)
                + ", tempoDecorrido=" + tempoDecorrido + "]";
    }

    public Equipa[] getEquipas() {
        return equipas;
    }

    public void setEquipas(Equipa[] equipas) {
        this.equipas = equipas;
    }

    public Bola getBola() {
        return bola;
    }

    public void setBola(Bola bola) {
        this.bola = bola;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public int getTempoDecorrido() {
        return tempoDecorrido;
    }

    public void setTempoDecorrido(int tempoDecorrido) {
        this.tempoDecorrido = tempoDecorrido;
    }

}
