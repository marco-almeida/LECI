package aula06;

import aula05.Date;

public class Bolseiro extends Aluno {
    private int montante;

    public Bolseiro(String nome, int cc, Date dataNasc, int montante) {
        super(nome, cc, dataNasc);
        this.montante = montante;
    }

    public Bolseiro(String nome, int cc, Date dataNasc, Date dataInsc, int montante) {
        super(nome, cc, dataNasc, dataInsc);
        this.montante = montante;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; Montante: %d", montante);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + montante;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        Bolseiro other = (Bolseiro) obj;
        if (montante != other.montante)
            return false;
        return true;
    }

    public int getMontante() {
        return montante;
    }

    public void setMontante(int montante) {
        this.montante = montante;
    }
}
