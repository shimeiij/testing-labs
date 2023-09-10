package org.testing.math.log;

import org.testing.math.CSVInterface;

import java.util.ArrayList;
import java.util.List;

public class LogBaseN  implements CSVInterface {
    List<String> resList = new ArrayList<>();

    public void addRes(final double x, final double res) {
        final StringBuilder builder = new StringBuilder();
        builder.append(x).append(',').append(res);
        resList.add(builder.toString());
    }

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
        this.MSG.add(buildCSVRes(x, res));
        addRes(x, res);
        return res;
    }
}
