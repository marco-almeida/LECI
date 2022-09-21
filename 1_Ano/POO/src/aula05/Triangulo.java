package aula05;

public class Triangulo {
    private double lado1, lado2, lado3;

    public Triangulo(double lado1, double lado2, double lado3) {
        if (lado1 + lado2 > lado3 && lado1 + lado3 > lado2 && lado2 + lado3 > lado1 && lado1 > 0 && lado2 > 0
                && lado3 > 0) {
            this.lado1 = lado1;
            this.lado2 = lado2;
            this.lado3 = lado3;
        }
    }

    public double perimetro() {
        return lado1 + lado2 + lado3;
    }

    public double area() {
        double s = (lado1 + lado2 + lado3) / 2;
        return Math.sqrt(s * (s - lado1) * (s - lado2) * (s - lado3));
    }

    @Override
    public String toString() {
        return "Triangulo [lado1=" + lado1 + ", lado2=" + lado2 + ", lado3=" + lado3 + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(lado1);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lado2);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(lado3);
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
        Triangulo other = (Triangulo) obj;
        if (Double.doubleToLongBits(lado1) != Double.doubleToLongBits(other.lado1))
            return false;
        if (Double.doubleToLongBits(lado2) != Double.doubleToLongBits(other.lado2))
            return false;
        if (Double.doubleToLongBits(lado3) != Double.doubleToLongBits(other.lado3))
            return false;
        return true;
    }

    public double getLado1() {
        return lado1;
    }

    public void setLado1(double lado1) {
        if (lado1 + lado2 > lado3 && lado1 + lado3 > lado2 && lado2 + lado3 > lado1 && lado1 > 0) {
            this.lado1 = lado1;
        }

    }

    public double getLado2() {
        return lado2;
    }

    public void setLado2(double lado2) {
        if (lado1 + lado2 > lado3 && lado1 + lado3 > lado2 && lado2 + lado3 > lado1 && lado2 > 0) {
            this.lado2 = lado2;
        }
    }

    public double getLado3() {
        return lado3;
    }

    public void setLado3(double lado3) {
        if (lado1 + lado2 > lado3 && lado1 + lado3 > lado2 && lado2 + lado3 > lado1 && lado3 > 0) {
            this.lado3 = lado3;
        }
    }

}
