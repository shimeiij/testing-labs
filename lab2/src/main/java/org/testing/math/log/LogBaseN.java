package org.testing.math.log;

import org.testing.math.CSVInterface;


public class LogBaseN implements CSVInterface {

    public Double ln(final double x, final double eps) {
        if (x <= 0) {
            throw new ArithmeticException("argument must be > 0");
        }
        if (x == 1.0) {
            return 0.0;
        }
        if (x == Math.E) {
            return 1.0;
        }

        double m = (x - 1) / (x + 1), sum = 0, im = 0, term = 1;
        int n = 1;
        while (Math.abs(term) > eps) {
            im = 2*n-1;
            term = Math.pow(m, im);
            term /= im;
            sum += term;
            n++;
        }
        return 2*sum;
    }
}


