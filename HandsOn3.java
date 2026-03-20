import java.util.Arrays;

class MatrixMath {
    // Multiplica dos matrices: A (m x n) * B (n x p)
    public static double[][] multiply(double[][] A, double[][] B) {
        double[][] C = new double[A.length][B[0].length];
        for (int i = 0; i < A.length; i++)
            for (int j = 0; j < B[0].length; j++)
                for (int k = 0; k < B.length; k++) C[i][j] += A[i][k] * B[k][j];
        return C;
    }
    // Transpone una matriz: Filas pasan a ser columnas
    public static double[][] transpose(double[][] m) {
        double[][] t = new double[m[0].length][m.length];
        for (int i = 0; i < m.length; i++)
            for (int j = 0; j < m[0].length; j++) t[j][i] = m[i][j];
        return t;
    }

    // Invierte una matriz usando Gauss-Jordan
    public static double[][] invert(double[][] matrix) {
        int n = matrix.length;
        double[][] augmented = new double[n][2 * n];
        // Crear matriz aumentada [A | I]
        for (int i = 0; i < n; i++) {
            System.arraycopy(matrix[i], 0, augmented[i], 0, n);
            augmented[i][i + n] = 1;
        }
        // Proceso de Gauss-Jordan
        for (int i = 0; i < n; i++) {
            double pivot = augmented[i][i];
            for (int j = 0; j < 2 * n; j++) augmented[i][j] /= pivot;
            for (int k = 0; k < n; k++) {
                if (k != i) {
                    double factor = augmented[k][i];
                    for (int j = 0; j < 2 * n; j++) augmented[k][j] -= factor * augmented[i][j];
                }
            }
        }
        // Extraer la parte derecha (la inversa)
        double[][] inverse = new double[n][n];
        for (int i = 0; i < n; i++) System.arraycopy(augmented[i], n, inverse[i], 0, n);
        return inverse;
    }
}

public class HandsOn3 {
    public static void main(String[] args) {
        // Matriz X con columna de 1s (para Beta0), Factor 1 (x1) y Factor 2 (x2)
        double[][] X = {
                {1, 41.9, 29.1}, {1, 43.4, 29.3}, {1, 43.9, 29.5}, {1, 44.5, 29.7},
                {1, 47.3, 29.9}, {1, 47.5, 30.3}, {1, 47.9, 30.5}, {1, 50.2, 30.7},
                {1, 52.8, 30.8}, {1, 53.2, 30.9}, {1, 56.7, 31.5}, {1, 57.0, 31.7},
                {1, 63.5, 31.9}, {1, 65.3, 32.0}, {1, 71.1, 32.1}, {1, 77.0, 32.5}, {1, 77.8, 32.9}
        };

        // Vector Y (Yield)
        double[][] Y = {
                {251.3}, {251.3}, {248.3}, {267.5}, {273.0}, {276.5}, {270.3}, {274.9},
                {285.0}, {290.0}, {297.0}, {302.5}, {304.5}, {309.3}, {321.7}, {330.7}, {349.0}
        };

        // betas
        double[][] Xt = MatrixMath.transpose(X);
        double[][] XtX = MatrixMath.multiply(Xt, X);
        double[][] XtXInv = MatrixMath.invert(XtX);
        double[][] XtY = MatrixMath.multiply(Xt, Y);
        double[][] betas = MatrixMath.multiply(XtXInv, XtY);


        System.out.println("Parametros del Modelo MLR:");
        System.out.printf("Beta 0 (Intercepto): %.4f\n", betas[0][0]);
        System.out.printf("Beta 1 (Factor 1):   %.4f\n", betas[1][0]);
        System.out.printf("Beta 2 (Factor 2):   %.4f\n", betas[2][0]);

        // Ejemplo con x1=50, x2=30
        double x1 = 50.0, x2 = 30.0;
        double yield = betas[0][0] + (betas[1][0] * x1) + (betas[2][0] * x2);
        System.out.printf("\nSimulacion: Para x1=%.1f y x2=%.1f, el Yield es: %.2f\n", x1, x2, yield);
    }
}