public class Point {
    protected double x;
    protected double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    // public Point(int distance, double angle) { // se distancia for sempre int
    // this.x = distance * Math.cos(angle);
    // this.y = distance * Math.sin(angle);
    // }

    public static Point pointByPolar(double angle, double distance) {
        return new Point(distance * Math.sin(angle), distance * Math.cos(angle));
    }

    public String toString() {
        return "(" + x + ", " + y + ")";
    }

}
