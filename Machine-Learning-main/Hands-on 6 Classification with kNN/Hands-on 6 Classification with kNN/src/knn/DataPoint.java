package knn;

import java.util.Arrays;

public class DataPoint {
    private double[] features; // Array de características (valores numéricos) del punto de datos
    private String label;      // Etiqueta de clase del punto de datos

    // Constructor para inicializar un punto de datos con características y etiqueta
    public DataPoint(double[] features, String label) {
        this.features = features;
        this.label = label;
    }

    // Devuelve el array de características del punto
    public double[] getFeatures() {
        return features;
    }

    // Devuelve la etiqueta de clase del punto
    public String getLabel() {
        return label;
    }

    // Calcula la distancia euclidiana entre este punto y otro punto
    public double calculateDistance(DataPoint other) {
        double sum = 0;
        // Itera sobre cada característica y calcula la suma de las diferencias cuadradas
        for (int i = 0; i < features.length; i++) {
            sum += Math.pow(features[i] - other.getFeatures()[i], 2);
        }
        // Retorna la raíz cuadrada de la suma para obtener la distancia euclidiana
        return Math.sqrt(sum);
    }

    @Override
    public String toString() {
        // Convierte el punto de datos en un String que muestra las características y la etiqueta
        return "DataPoint{" + "features=" + Arrays.toString(features) + ", label='" + label + '\'' + '}';
    }
}
