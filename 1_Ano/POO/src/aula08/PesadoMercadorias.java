package aula08;

public class PesadoMercadorias extends Pesado {
    private int cargaMaxima;

    public PesadoMercadorias(String matricula, String marca, String modelo, int potencia, int numQuadro, int peso,
            int cargaMaxima) {
        super(matricula, marca, modelo, potencia, numQuadro, peso);
        this.cargaMaxima = cargaMaxima;
    }

    @Override
    public String toString() {
        return super.toString() + "PesadoMercadorias [cargaMaxima=" + cargaMaxima + "]";
    }

    public int getCargaMaxima() {
        return cargaMaxima;
    }

    public void setCargaMaxima(int cargaMaxima) {
        this.cargaMaxima = cargaMaxima;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + cargaMaxima;
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
        PesadoMercadorias other = (PesadoMercadorias) obj;
        if (cargaMaxima != other.cargaMaxima)
            return false;
        return super.equals(obj);
    }
}
