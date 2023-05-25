package org.testing.math.log;

import java.util.stream.LongStream;

public class BaseLog {

    Double ln(Long x, Long acc) {
        if (x <= -1 || x > 1) return Double.NaN;
        return LongStream.iterate(0, n->n+1).limit(acc)
                .mapToDouble(n ->Math.pow(-1, n-1)*Math.pow(x, n)/n).sum();
    }

}
