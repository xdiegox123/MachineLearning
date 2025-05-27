package knn;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class KNNClassifier {
    private List<DataPoint> dataSet; // Lista de puntos de datos de entrenamiento
    private int k;                   // Número de vecinos a considerar

    // Constructor que inicializa el clasificador con un valor de k
    public KNNClassifier(int k) {
        this.k = k;
        this.dataSet = new ArrayList<>();
    }

    // Método para entrenar el clasificador guardando el conjunto de datos de entrenamiento
    public void train(List<DataPoint> trainingSet) {
        this.dataSet = trainingSet;
    }

    // Clasifica un nuevo punto basado en sus k vecinos más cercanos en el conjunto de entrenamiento
    public String classify(DataPoint newPoint) {
        List<DataPoint> nearestNeighbors = findNearestNeighbors(newPoint); // Encuentra los k vecinos más cercanos
        return getMostCommonLabel(nearestNeighbors); // Retorna la etiqueta más común entre los vecinos
    }

    // Encuentra los k vecinos más cercanos a un nuevo punto de datos
    private List<DataPoint> findNearestNeighbors(DataPoint newPoint) {
        // Ordena el conjunto de entrenamiento según la distancia al nuevo punto
        dataSet.sort(Comparator.comparingDouble(newPoint::calculateDistance));
        // Retorna los primeros k elementos de la lista ordenada (los k vecinos más cercanos)
        return dataSet.subList(0, k);
    }

    // Determina la etiqueta de clase más común entre los vecinos cercanos
    private String getMostCommonLabel(List<DataPoint> neighbors) {
        // Mapa para almacenar la frecuencia de cada etiqueta entre los vecinos
        Map<String, Integer> frequencyMap = new HashMap<>();
        for (DataPoint neighbor : neighbors) {
            // Aumenta el contador de la etiqueta en el mapa
            frequencyMap.put(neighbor.getLabel(), frequencyMap.getOrDefault(neighbor.getLabel(), 0) + 1);
        }
        // Retorna la etiqueta más común (aquella con la frecuencia más alta)
        return frequencyMap.entrySet().stream()
                .max(Map.Entry.comparingByValue())
                .get()
                .getKey();
    }
}
