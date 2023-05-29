package org.testing.math.log;

import org.testing.math.CSVInterface;

public class LogBaseN implements CSVInterface {

    public Double ln(final double x, final double eps) {
        if (x <= 0) {
            throw new ArithmeticException("argument must be > 0");
        }
//        if (x == 1.0) {
//            return 0.0;
//        }
        if (x == Math.E){
            return 1.0;
        }
        int k = 0;
        double y = x;
        while (y > Math.E)
        {
            y /= Math.E;
            k++;
        }
        double n = y - 1,
                sum = n;
        int i = 1;
        while (Math.abs(n) > eps)
        {
            i++;
            n *= -((y-1)*(i-1))/i;
            sum += n;
        }
        final double res = k + sum;
        writeToCsv(buildCSVRes(x, res, Math.E), FILENAME);
        return res;
    }
}
