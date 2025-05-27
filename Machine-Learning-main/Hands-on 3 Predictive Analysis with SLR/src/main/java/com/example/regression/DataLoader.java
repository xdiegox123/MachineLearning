package com.example.regression;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class DataLoader {
    // Listas para almacenar los valores de X (independiente) e Y (dependiente)
    private ArrayList<Double> X = new ArrayList<Double>();
    private ArrayList<Double> Y = new ArrayList<>();

    // Método para cargar datos desde un archivo CSV
    public void loadData(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        String line;
        // Leer cada línea del archivo CSV y agregar los valores a las listas
        while ((line = br.readLine()) != null) {
            String[] values = line.split(",");
            X.add(Double.parseDouble(values[0])); // Agregar el valor de X
            Y.add(Double.parseDouble(values[1])); // Agregar el valor de Y
        }
        br.close();
    }

    // Método para dividir los datos en dos partes: entrenamiento y prueba
    public double[][] splitData(double percentage) {
        int size = X.size();
        int splitIndex = (int) (size * percentage); // Calcular el índice de división (70%)

        // Crear una lista de índices para mezclar los datos aleatoriamente
        ArrayList<Integer> indices = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            indices.add(i);
        }
        Collections.shuffle(indices); // Mezclar los índices aleatoriamente

        // Crear arrays para los conjuntos de entrenamiento y prueba
        double[] X_train = new double[splitIndex];
        double[] Y_train = new double[splitIndex];
        double[] X_test = new double[size - splitIndex];
        double[] Y_test = new double[size - splitIndex];

        // Llenar el conjunto de entrenamiento
        for (int i = 0; i < splitIndex; i++) {
            X_train[i] = X.get(indices.get(i));
            Y_train[i] = Y.get(indices.get(i));
        }
        // Llenar el conjunto de prueba
        for (int i = splitIndex; i < size; i++) {
            X_test[i - splitIndex] = X.get(indices.get(i));
            Y_test[i - splitIndex] = Y.get(indices.get(i));
        }

        // Devolver los conjuntos de entrenamiento y prueba
        return new double[][]{X_train, Y_train, X_test, Y_test};
    }
}
