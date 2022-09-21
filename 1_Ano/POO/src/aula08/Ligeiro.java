package aula08;

public class Ligeiro extends Viatura {
    private int numQuadro;
    private int capacidadeBagageira;

    public Ligeiro(String matricula, String marca, String modelo, int potencia, int numQuadro,
            int capacidadeBagageira) {
        super(matricula, marca, modelo, potencia);
        this.numQuadro = numQuadro;
        this.capacidadeBagageira = capacidadeBagageira;
    }

    @Override
    public String toString() {
        return super.toString() + "Ligeiro [capacidadeBagageira=" + capacidadeBagageira + ", numQuadro=" + numQuadro
                + "] ";
    }

    public int getNumQuadro() {
        return numQuadro;
    }

    public void setNumQuadro(int numQuadro) {
        this.numQuadro = numQuadro;
    }

    public int getCapacidadeBagageira() {
        return capacidadeBagageira;
    }

    public void setCapacidadeBagageira(int capacidadeBagageira) {
        this.capacidadeBagageira = capacidadeBagageira;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + capacidadeBagageira;
        result = prime * result + numQuadro;
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
        Ligeiro other = (Ligeiro) obj;
        if (capacidadeBagageira != other.capacidadeBagageira)
            return false;
        if (numQuadro != other.numQuadro)
            return false;
        return super.equals(obj);
    }

}
