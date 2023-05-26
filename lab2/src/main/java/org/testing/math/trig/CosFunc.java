package org.testing.math.trig;

public class CosFunc {

    public double cos(double x, double acc) {
        double n = 1.0;
        double sum = 0.0;
        long i = 1;
        do {
            sum += n;
            n *= -1.0 * x * x / ((2 * i - 1) * (2 * i));
            i++;
        } while (Math.abs(n) > acc);

        double res = Math.ceil(sum/acc)*acc;
        if (res == 0.0) return res;
        return sum;
    }

}
