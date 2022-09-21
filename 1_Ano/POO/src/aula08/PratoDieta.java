package aula08;

public class PratoDieta extends Prato {
    private double maxCalorias;

    public PratoDieta(String nome, double maxCalorias) {
        super(nome);
        this.maxCalorias = maxCalorias;
    }

    @Override
    public boolean addIngrediente(Alimento a){
        double totalCalorias = 0;
        for (Alimento al : this.getComposicao()) {
            totalCalorias += al.getCalorias();
        }
        if(maxCalorias >= totalCalorias + a.getCalorias()){
            this.getComposicao().add(a);
            return true;
        }
        return false;
    }

    public double getMaxCalorias() {
        return this.maxCalorias;
    }

    public void setMaxCalorias(double maxCalorias) {
        this.maxCalorias = maxCalorias;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        long temp;
        temp = Double.doubleToLongBits(maxCalorias);
        result = prime * result + (int) (temp ^ (temp >>> 32));
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
        PratoDieta other = (PratoDieta) obj;
        if (Double.doubleToLongBits(maxCalorias) != Double.doubleToLongBits(other.maxCalorias))
            return false;
        return true;
    }

    @Override
    public String toString() {
        double sum = 0;
        for (Alimento alimento : this.getComposicao()) {
            sum += alimento.getCalorias();
        }
        return super.toString() + String.format(" - Dieta (%4.2f Calorias)",sum);
    }
    
}
