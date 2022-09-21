package aula07;

public class QuartoHotel extends Alojamento {
    private String tipo;

    public QuartoHotel(String codigo, String nome, String local, double precoNoite,
            double avaliacao, String tipo) {
        super(codigo, nome, local, precoNoite, avaliacao);
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return super.toString() + "QuartoHotel [tipo=" + tipo + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((tipo == null) ? 0 : tipo.hashCode());
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
        QuartoHotel other = (QuartoHotel) obj;
        if (tipo == null) {
            if (other.tipo != null)
                return false;
        } else if (!tipo.equals(other.tipo))
            return false;
        return true;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
