package org.testing;

import java.util.stream.LongStream;
public class MathSolver {

    public static long fact(long x) {
        if(x <= 1) {
            return 1;
        }
        return x * fact(--x);
    }

    public static double findArcSin(double x) {
        long lim = 10;
        if (Math.abs(x) >= 1) {
            return Double.NaN;
        }
        return LongStream.iterate(0, n -> n + 1).limit(lim)
                .mapToDouble(n -> {
                    long bc = fact(2*n)/(fact(n)*fact(n));
                    double k = Math.pow(2, -2*n);
                    return k*bc*(Math.pow(x, 2*n+1)/(2*n+1));
                })
                .sum();
    }
    

}
