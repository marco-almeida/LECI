package aula07;

public class Circulo extends Forma {
    private double raio;

    public Circulo(String cor, double raio) {
        super(cor);
        if (raio > 0) {
            this.raio = raio;
        } else {
            this.setCor(null);
        }
    }

    public double area() {
        return Math.PI * (raio * raio);
    }

    public double perimetro() {
        return Math.PI * 2 * raio;
    }

    @Override
    public String toString() {
        return super.toString() + "Circulo [raio=" + raio + "]";
    }

    public double getRaio() {
        return raio;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(raio);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Circulo other = (Circulo) obj;
        if (Double.doubleToLongBits(raio) != Double.doubleToLongBits(other.raio))
            return false;
        if (getCor() == null) {
            if (other.getCor() != null)
                return false;
        } else if (!getCor().equals(other.getCor()))
            return false;
        return true;
    }

    public void setRaio(double raio) {
        if (raio > 0) {
            this.raio = raio;
        }
    }
}
