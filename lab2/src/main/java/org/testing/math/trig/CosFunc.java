package org.testing.math.trig;

import org.testing.math.CSVInterface;

import java.util.ArrayList;
import java.util.List;

public class CosFunc  implements CSVInterface {
    List<String> resList = new ArrayList<>();

    public double cos(final double x,final double eps) {
        double n = 1.0;
        double sum = 0.0;
        long i = 0;
        while (Math.abs(n) > eps)
        {
            sum += n;
            i++;
            n *= -1.0 * x * x / ((2.0 * i - 1.0) * (2.0 * i));
        }
        final double res = Math.ceil(sum/eps)*eps;
        if (res == 0.0){
            return res;
        }
        return sum;
    }

    public List<String> getResList() {
        return resList;
    }
}
