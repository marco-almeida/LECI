package aula08;

public abstract class Pesado extends Viatura {
    private int numQuadro;
    private int peso;

    protected Pesado(String matricula, String marca, String modelo, int potencia, int numQuadro, int peso) {
        super(matricula, marca, modelo, potencia);
        this.numQuadro = numQuadro;
        this.peso = peso;
    }

    public int getNumQuadro() {
        return numQuadro;
    }

    public void setNumQuadro(int numQuadro) {
        this.numQuadro = numQuadro;
    }

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + numQuadro;
        result = prime * result + peso;
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
        Pesado other = (Pesado) obj;
        if (numQuadro != other.numQuadro)
            return false;
        if (peso != other.peso)
            return false;
        return super.equals(obj);
    }

}
