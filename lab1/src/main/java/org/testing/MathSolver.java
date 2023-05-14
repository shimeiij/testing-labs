package org.testing;

import java.util.stream.LongStream;
public class MathSolver {


    private static Long fact(final Long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(1, (long x, long y) -> x * y);
    }


    private static double asin(final Double x) {
        final long lim = 30;
        return LongStream.iterate(0, n -> n + 1).limit(lim)
                .mapToDouble(n ->
                    Math.pow(2, -2*n)*(fact(2*n)/Math.pow(fact(n), 2))*(Math.pow(x, 2.0*n+1.0)/(2.0*n+1.0))
                )
                .sum();
    }

    public static double calcAsin(final double x) {
        if (Math.abs(x) > 1) {
            return Double.NaN;
        }
        if (1/Math.sqrt(2) < x && x <= 1) {
            return Math.PI/2 - asin(Math.sqrt(1-x*x));
        }
        if (-1 <= x && x < -1/Math.sqrt(2)) {
            return -Math.PI/2 + asin(Math.sqrt(1-x*x));
        }
        return asin(x);
    }

}
