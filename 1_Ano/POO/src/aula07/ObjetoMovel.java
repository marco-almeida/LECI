package aula07;

public abstract class ObjetoMovel {
    private int x = 0;
    private int y = 0;
    private double distanciaPercorrida = 0;

    public void move(int newX, int newY) {
        this.distanciaPercorrida += Math.sqrt(((double)newX - x) * (newX - x) + (newY - y) * (newY - y));
        this.x = newX;
        this.y = newY;
    }

    @Override
    public String toString() {
        return "[distanciaPercorrida=" + distanciaPercorrida + ", x=" + x + ", y=" + y + "] ";
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public void setDistanciaPercorrida(double distanciaPercorrida) {
        this.distanciaPercorrida = distanciaPercorrida;
    }

}
