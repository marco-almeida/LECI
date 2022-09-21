package aula08;

public class PesadoPassageiros extends Pesado {
    private int maxPassageiros;

    public PesadoPassageiros(String matricula, String marca, String modelo, int potencia, int numQuadro, int peso,
            int maxPassageiros) {
        super(matricula, marca, modelo, potencia, numQuadro, peso);
        this.maxPassageiros = maxPassageiros;
    }

    @Override
    public String toString() {
        return super.toString() + "PesadoPassageiros [maxPassageiros=" + maxPassageiros + "]";
    }

    public int getMaxPassageiros() {
        return maxPassageiros;
    }

    public void setMaxPassageiros(int maxPassageiros) {
        this.maxPassageiros = maxPassageiros;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + maxPassageiros;
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
        PesadoPassageiros other = (PesadoPassageiros) obj;
        if (maxPassageiros != other.maxPassageiros)
            return false;
        return super.equals(obj);
    }

}
