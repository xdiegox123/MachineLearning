import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Point> points = loadData("Hands-on 7 Classification with Kmeans/src/data/dataset.csv");

        // Comprobar si se cargaron puntos.
        if (points.isEmpty()) {
            System.out.println("No se cargaron datos. Verifica la ruta del archivo y el contenido.");
            return;
        }

        KMeans kMeans = new KMeans(3, 100, points);
        kMeans.fit();

        for (int i = 0; i < kMeans.getClusters().size(); i++) {
            Cluster cluster = kMeans.getClusters().get(i);
            System.out.println("Cluster " + (i+1) + " centroide: " + cluster.getCentroid());
            System.out.println("Puntos en el clúster: " + cluster.getPoints().size());
            for (Point p : cluster.getPoints()) {
                System.out.println(p);
            }
            System.out.println();
        }
    }

    public static List<Point> loadData(String filePath) {
        List<Point> points = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");
                double x = Double.parseDouble(values[0]);
                double y = Double.parseDouble(values[1]);
                points.add(new Point(x, y));
            }
            System.out.println("Datos cargados exitosamente. Total de puntos: " + points.size());
        } catch (IOException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
        return points;
    }
}
