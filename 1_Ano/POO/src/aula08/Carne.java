package aula08;

public class Carne extends Alimento {
    private VariedadeCarne variedade;

    public Carne(VariedadeCarne variedade,double proteinas, double calorias, double peso) {
        super(proteinas, calorias, peso);
        this.variedade = variedade;
    }

    @Override
    public String toString() {
        return "Carne [variedade=" + variedade + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + ((variedade == null) ? 0 : variedade.hashCode());
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
        Carne other = (Carne) obj;
        if (variedade != other.variedade)
            return false;
        return true;
    }

    public VariedadeCarne getVariedade() {
        return variedade;
    }

    public void setVariedade(VariedadeCarne variedade) {
        this.variedade = variedade;
    }

}
