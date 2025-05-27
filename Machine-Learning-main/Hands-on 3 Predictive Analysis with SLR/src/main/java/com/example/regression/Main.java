package com.example.regression;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        try {
            // Cargar el dataset desde el archivo CSV
            DataLoader dataLoader = new DataLoader();
            dataLoader.loadData("dataset.csv");

            // Inicializar variables para el mejor R^2 y los parámetros óptimos
            double maxRSquared = Double.MIN_VALUE;
            double optimalB0 = 0, optimalB1 = 0;

            // Realizar el proceso de segmentación y entrenamiento dos veces
            for (int i = 0; i < 2; i++) {
                // Dividir los datos en 70% entrenamiento y 30% prueba
                double[][] data = dataLoader.splitData(0.7);

                double[] X_train = data[0];
                double[] Y_train = data[1];
                double[] X_test = data[2];
                double[] Y_test = data[3];

                // Crear un nuevo modelo de regresión lineal simple
                SimpleLinearRegression slr = new SimpleLinearRegression();
                slr.train(X_train, Y_train); // Entrenar el modelo con el conjunto de entrenamiento

                // Calcular el coeficiente de determinación (R^2) con el conjunto de prueba
                double rSquared = slr.calculateRSquared(X_test, Y_test);

                // Comparar R^2 para seleccionar los mejores parámetros
                if (rSquared > maxRSquared) {
                    maxRSquared = rSquared;
                    optimalB0 = slr.getB0();
                    optimalB1 = slr.getB1();
                }
            }

            // Imprimir el mejor modelo encontrado y sus parámetros
            System.out.println("Mejor modelo encontrado:");
            System.out.println("B_0: " + optimalB0);
            System.out.println("B_1: " + optimalB1);
            System.out.println("Coeficiente de Determinación (R^2): " + maxRSquared);

        } catch (IOException e) {
            System.err.println("Error al cargar los datos: " + e.getMessage());
        }
    }
}
