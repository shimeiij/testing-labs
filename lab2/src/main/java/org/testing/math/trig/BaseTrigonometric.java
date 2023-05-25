package org.testing.math.trig;

import java.util.stream.LongStream;

public class BaseTrigonometric {

    private Long fact(final Long n) {
        return LongStream.rangeClosed(1, n)
                .reduce(1, (long x, long y) -> x * y);
    }

    Double cos(final Long x, final long lim) {
        return LongStream.iterate(0, n->n+1).limit(lim)
                .mapToDouble(n -> (Math.pow(-1, n)*Math.pow(x, 2*n))/fact(2*n)).sum();
    }

}
