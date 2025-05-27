// Clase que representa un punto en el espacio de características con dos coordenadas (x, y).
public class Point {
    private double x;
    private double y;

    // Constructor que inicializa las coordenadas del punto.
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() { return x; }
    public double getY() { return y; }

    // Método que calcula la distancia euclidiana entre este punto y otro.
    public double distance(Point other) {
        return Math.sqrt(Math.pow(this.x - other.getX(), 2) + Math.pow(this.y - other.getY(), 2));
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
