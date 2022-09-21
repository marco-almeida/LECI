package aula08;

public class LigeiroEletrico extends Ligeiro implements VeiculoEletrico {
    private int autonomia;

    public LigeiroEletrico(String matricula, String marca, String modelo, int potencia, int numQuadro,
            int capacidadeBagageira, int autonomia) {
        super(matricula, marca, modelo, potencia, numQuadro, capacidadeBagageira);
        this.autonomia = autonomia;
    }

    @Override
    public int autonomia() {
        return this.autonomia;
    }

    @Override
    public void carregar(int percentagem) {
        if (percentagem + autonomia >= 100) {
            autonomia = 100;
        } else {
            autonomia += percentagem;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + autonomia;
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
        LigeiroEletrico other = (LigeiroEletrico) obj;
        if (autonomia != other.autonomia)
            return false;
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return super.toString() + "LigeiroEletrico [autonomia=" + autonomia + "]";
    }

    public int getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(int autonomia) {
        this.autonomia = autonomia;
    }

}
