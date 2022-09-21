package aula06;

import aula05.Date;

public class Aluno extends Pessoa {
    private int nMec;
    private Date dataInsc;
    private static int nMecs = 100;

    public Aluno(String nome, int cc, Date dataNasc) {
        super(nome, cc, dataNasc);
        this.nMec = nMecs++;
        this.dataInsc = new Date();
    }

    public Aluno(String nome, int cc, Date dataNasc, Date dataInsc) {
        this(nome, cc, dataNasc);
        this.dataInsc = dataInsc;
    }

    @Override
    public String toString() {
        return super.toString() + String.format("; Nmec: %d; Data de Inscrição: %s", nMec, dataInsc.toString());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((dataInsc == null) ? 0 : dataInsc.hashCode());
        result = prime * result + nMec;
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
        Aluno other = (Aluno) obj;
        if (dataInsc == null) {
            if (other.dataInsc != null)
                return false;
        } else if (!dataInsc.equals(other.dataInsc))
            return false;
        if (nMec != other.nMec)
            return false;
        return true;
    }

    public int getnMec() {
        return nMec;
    }

    public void setnMec(int nMec) {
        this.nMec = nMec;
    }

    public Date getDataInsc() {
        return dataInsc;
    }

    public void setDataInsc(Date dataInsc) {
        this.dataInsc = dataInsc;
    }

    public static int getnMecs() {
        return nMecs;
    }

    public static void setnMecs(int nMecs) {
        Aluno.nMecs = nMecs;
    }
}
