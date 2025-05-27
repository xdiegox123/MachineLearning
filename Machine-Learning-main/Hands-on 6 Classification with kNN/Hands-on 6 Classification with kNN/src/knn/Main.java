package knn;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Carga el conjunto de datos desde un archivo CSV
        List<DataPoint> dataSet = loadDataSet("Hands-on 6 Classification with kNN/src/knn/data.csv");

        // Valor de k para el clasificador k-NN
        int k = 6;
        // Crea una instancia del clasificador k-NN
        KNNClassifier knn = new KNNClassifier(k);

        // Divide el conjunto de datos en 70% entrenamiento y 30% prueba
        List<DataPoint> trainingSet = dataSet.subList(0, (int) (dataSet.size() * 0.7));
        List<DataPoint> testSet = dataSet.subList((int) (dataSet.size() * 0.7), dataSet.size());

        // Entrena el clasificador con el conjunto de entrenamiento
        knn.train(trainingSet);

        // Variable para contar las predicciones correctas
        int correct = 0;
        // Clasifica cada punto del conjunto de prueba y verifica su precisión
        for (DataPoint testPoint : testSet) {
            // Clasifica el punto de prueba
            String predictedLabel = knn.classify(testPoint);
            // Muestra la etiqueta real y la predicha en la consola
            System.out.println("Actual: " + testPoint.getLabel() + ", Predicted: " + predictedLabel);
            // Si la predicción es correcta, incrementa el contador
            if (predictedLabel.equals(testPoint.getLabel())) {
                correct++;
            }
        }

        // Calcula la precisión como el porcentaje de predicciones correctas
        double accuracy = (double) correct / testSet.size() * 100;
        System.out.println("Accuracy: " + accuracy + "%");
    }

    // Método para cargar el conjunto de datos desde un archivo CSV
    public static List<DataPoint> loadDataSet(String filePath) {
        List<DataPoint> dataSet = new ArrayList<>();
        // Lee el archivo línea por línea
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Procesa cada línea del archivo
            while ((line = br.readLine()) != null) {
                String[] values = line.split(","); // Divide la línea en valores separados por comas
                double[] features = new double[values.length - 1];
                // Convierte las primeras columnas en características numéricas
                for (int i = 0; i < features.length; i++) {
                    features[i] = Double.parseDouble(values[i]);
                }
                // La última columna es la etiqueta de clase
                String label = values[values.length - 1];
                // Agrega un nuevo punto de datos a la lista
                dataSet.add(new DataPoint(features, label));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataSet;
    }
}
