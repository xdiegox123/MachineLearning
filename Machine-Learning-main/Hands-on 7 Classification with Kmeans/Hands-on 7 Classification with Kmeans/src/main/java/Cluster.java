import java.util.ArrayList;
import java.util.List;

// Clase que representa un clúster, que incluye un centroide y una lista de puntos.
public class Cluster {
    private Point centroid;
    private List<Point> points;

    // Constructor que inicializa el centroide y crea una lista de puntos vacía.
    public Cluster(Point initialCentroid) {
        this.centroid = initialCentroid;
        this.points = new ArrayList<>();
    }

    public Point getCentroid() { return centroid; }
    public List<Point> getPoints() { return points; }

    // Agregar un punto al clúster.
    public void addPoint(Point point) { points.add(point); }

    // Limpiar la lista de puntos, utilizado para la nueva asignación de puntos.
    public void clearPoints() { points.clear(); }

    // Actualizar el centroide calculando la media de todos los puntos del clúster.
    public void updateCentroid() {
        double sumX = 0, sumY = 0;
        for (Point point : points) {
            sumX += point.getX();
            sumY += point.getY();
        }
        // Calcular el nuevo centroide.
        centroid = new Point(sumX / points.size(), sumY / points.size());
    }
}
