class DiscreteMaths {

    // calcular la pendiente (Beta 1)
    public double calculateBeta1(double[] x, double[] y) {
        int n = x.length; // Numero de observaciones
        double sumX = 0, sumY = 0, sumXY = 0, sumX2 = 0;

        // Iteramos por los datos para obtener las sumatorias necesarias
        for (int i = 0; i < n; i++) {
            sumX += x[i];           // Sumatoria de X
            sumY += y[i];           // Sumatoria de Y
            sumXY += x[i] * y[i];   // Sumatoria de X * Y
            sumX2 += x[i] * x[i];   // Sumatoria de X al cuadrado
        }
        // Aplicación de la fórmula: β1 = [nΣ(xy) - ΣxΣy] / [nΣ(x^2) - (Σx)^2]
        double numerator = (n * sumXY) - (sumX * sumY);
        double denominator = (n * sumX2) - Math.pow(sumX, 2);
        return numerator / denominator;
    }

    //calcular la intersección (Beta 0)
    public double calculateBeta0(double[] x, double[] y, double b1) {
        double sumX = 0, sumY = 0;
        for (int i = 0; i < x.length; i++) {
            sumX += x[i]; // Σx
            sumY += y[i]; // Σy
        }
        // Aplicación de la fórmula: β0 = (Σy - β1 * Σx) / n
        return (sumY - (b1 * sumX)) / x.length;
    }
}
public class Hands_on2 {
    private double beta0; // Intersección
    private double beta1; // Pendiente

    // Dataset (Inversión en Publicidad vs Ventas)
    private double[] advertising = {23, 26, 30, 34, 43, 48, 52, 57, 58};
    private double[] sales = {651, 762, 856, 1063, 1190, 1298, 1421, 1440, 1518};

    // Constructor: Entrena el modelo al crear la instancia
    public Hands_on2() {
        DiscreteMaths math = new DiscreteMaths();
        // Calcula primero Beta 1 porque Beta 0 depende de ella
        this.beta1 = math.calculateBeta1(advertising, sales);
        this.beta0 = math.calculateBeta0(advertising, sales, beta1);
    }

    // Muestra la ecuación matematica final con formato de 4 decimales
    public void mostrarEcuacion() {
        System.out.printf("beta 0 = %.4f + beta 1 = %.4f \n", beta0, beta1);
    }

    public static void main(String[] args) {
        Hands_on2 proyecto = new Hands_on2();
        proyecto.mostrarEcuacion();

    }
}