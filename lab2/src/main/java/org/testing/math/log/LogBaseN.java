package org.testing.math.log;

public class LogBaseN {

    public Double ln(double x, double eps) {
        if (x <= 0) {
            throw new ArithmeticException("argument must be > 0");
        }
        if (x == 1.0) return 0.0;
        int k = 0;
        while (x > Math.E)
        {
            x /= Math.E;
            k++;
        }

        double n = x - 1, sum = n;
        int i = 1;
        while (Math.abs(n) > eps)
        {
            i++;
            n *= -((x-1)*(i-1))/i;
            sum += n;
        }
        return k + sum;
    }
}
