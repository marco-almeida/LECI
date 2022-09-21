package aula07;

public class Apartamento extends Alojamento {
    private int numQuartos;

    public Apartamento(String codigo, String nome, String local, double precoNoite,
            double avaliacao, int numQuartos) {
        super(codigo, nome, local, precoNoite, avaliacao);
        this.numQuartos = numQuartos;
    }

    @Override
    public String toString() {
        return super.toString() + "Apartamento [numQuartos=" + numQuartos + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + numQuartos;
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
        Apartamento other = (Apartamento) obj;
        if (numQuartos != other.numQuartos)
            return false;
        return true;
    }

    public int getNumQuartos() {
        return numQuartos;
    }

    public void setNumQuartos(int numQuartos) {
        this.numQuartos = numQuartos;
    }

}
