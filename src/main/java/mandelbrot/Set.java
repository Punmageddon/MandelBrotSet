package mandelbrot;

public class Set {
    static final int ITERATIONS = 100;

    public int getPointValue(double c_a, double c_b){
        double a_n = c_a;
        double b_n = c_b;
        for (int i = 1; i < ITERATIONS; i++){
            double a_n2 = a_n*a_n - b_n*b_n;
            b_n = 2*a_n*b_n;
            a_n = a_n2;
            if (a_n > 20) {
                return i;
            }
        }
        return ITERATIONS;
    }
}
