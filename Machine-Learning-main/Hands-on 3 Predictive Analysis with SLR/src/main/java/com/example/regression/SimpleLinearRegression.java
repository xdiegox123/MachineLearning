package com.example.regression;

public class SimpleLinearRegression {
    private double B_0; // Intercepto de la recta (B_0)
    private double B_1; // Pendiente de la recta (B_1)

    // Método para entrenar el modelo utilizando el conjunto de entrenamiento
    public void train(double[] X, double[] Y) {
        int n = X.length;
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;

        // Calcular las sumatorias necesarias para los cálculos de B_0 y B_1
        for (int i = 0; i < n; i++) {
            sumX += X[i];          // Sumatoria de X
            sumY += Y[i];          // Sumatoria de Y
            sumXY += X[i] * Y[i];  // Sumatoria de X * Y
            sumX2 += X[i] * X[i];  // Sumatoria de X^2
        }

        // Cálculo de B_1 (pendiente) utilizando la fórmula de mínimos cuadrados
        B_1 = (n * sumXY - sumX * sumY) / (n * sumX2 - sumX * sumX);

        // Cálculo de B_0 (intercepto)
        B_0 = (sumY - B_1 * sumX) / n;
    }

    // Método para hacer predicciones basadas en el modelo entrenado
    public double predict(double x) {
        return B_0 + B_1 * x; // Ecuación de la recta: Y = B_0 + B_1 * X
    }

    // Obtener el valor de B_0 (intercepto)
    public double getB0() {
        return B_0;
    }

    // Obtener el valor de B_1 (pendiente)
    public double getB1() {
        return B_1;
    }

    // Método para calcular el coeficiente de determinación (R^2)
    public double calculateRSquared(double[] X, double[] Y) {
        double ssTotal = 0, ssResidual = 0;
        double meanY = 0;
        int n = Y.length;

        // Calcular la media de Y
        for (double y : Y) {
            meanY += y;
        }
        meanY /= n;

        // Calcular la suma de los cuadrados totales (SST) y de los residuales (SSR)
        for (int i = 0; i < n; i++) {
            double predictedY = predict(X[i]); // Predicción del modelo
            ssTotal += Math.pow(Y[i] - meanY, 2);    // SST: sum(Y - Y_promedio)^2
            ssResidual += Math.pow(Y[i] - predictedY, 2); // SSR: sum(Y - Y_predicho)^2
        }

        // Retornar el coeficiente de determinación: R^2 = 1 - (SSR / SST)
        return 1 - (ssResidual / ssTotal);
    }
}
