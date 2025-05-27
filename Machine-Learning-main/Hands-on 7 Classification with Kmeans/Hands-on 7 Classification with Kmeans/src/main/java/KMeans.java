import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// Clase que implementa el algoritmo K-means.
public class KMeans {
    private int k;
    private int maxIterations;
    private List<Point> data;
    private List<Cluster> clusters;

    // Constructor que inicializa el número de clústeres, iteraciones máximas y el conjunto de datos.
    public KMeans(int k, int maxIterations, List<Point> data) {
        this.k = k;
        this.maxIterations = maxIterations;
        this.data = data;
        this.clusters = new ArrayList<>();
    }

    // Inicializar los clústeres con puntos aleatorios del conjunto de datos.
    public void initializeClusters() {
        Random random = new Random();
        for (int i = 0; i < k; i++) {
            Point centroid = data.get(random.nextInt(data.size()));
            clusters.add(new Cluster(centroid));
        }
    }

    // Asignar cada punto al clúster cuyo centroide esté más cercano.
    public void assignPointsToClusters() {
        for (Cluster cluster : clusters) {
            cluster.clearPoints();
        }
        for (Point point : data) {
            Cluster nearestCluster = null;
            double minDistance = Double.MAX_VALUE;
            for (Cluster cluster : clusters) {
                double distance = point.distance(cluster.getCentroid());
                if (distance < minDistance) {
                    minDistance = distance;
                    nearestCluster = cluster;
                }
            }
            nearestCluster.addPoint(point);
        }
    }

    // Actualizar los centroides de todos los clústeres.
    public void updateCentroids() {
        for (Cluster cluster : clusters) {
            cluster.updateCentroid();
        }
    }

    // Ejecutar el algoritmo K-means.
    public void fit() {
        initializeClusters();
        for (int i = 0; i < maxIterations; i++) {
            assignPointsToClusters();
            updateCentroids();
        }
    }

    public List<Cluster> getClusters() {
        return clusters;
    }
}
