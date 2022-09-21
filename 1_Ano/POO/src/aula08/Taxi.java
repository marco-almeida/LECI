package aula08;

public class Taxi extends Ligeiro {
    private int numLicense;

    public Taxi(String matricula, String marca, String modelo, int potencia, int numQuadro, int capacidadeBagageira,
            int numLicense) {
        super(matricula, marca, modelo, potencia, numQuadro, capacidadeBagageira);
        this.numLicense = numLicense;
    }

    @Override
    public String toString() {
        return super.toString() + "Taxi [numLicense=" + numLicense + "]";
    }

    public int getNumLicense() {
        return numLicense;
    }

    public void setNumLicense(int numLicense) {
        this.numLicense = numLicense;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + numLicense;
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
        Taxi other = (Taxi) obj;
        if (numLicense != other.numLicense)
            return false;
        return super.equals(obj);
    }

}
