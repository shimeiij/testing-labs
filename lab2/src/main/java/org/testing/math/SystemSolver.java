package org.testing.math;

import org.testing.math.log.LogBase;
import org.testing.math.log.LogBaseN;
import org.testing.math.trig.*;

public class SystemSolver {
    LogBaseN ln;
    CosFunc cos;

    public SystemSolver(final LogBaseN ln, final CosFunc cos)
    {
        this.ln = ln;
        this.cos = cos;
    }

    public Double solveSystem(final double x,final double eps)
    {
        final SinFunc sin = new SinFunc(cos);
        final CotFunc cotFunc = new CotFunc(sin, cos);
        final SecFunc sec = new SecFunc(cos);

        final LogBase log = new LogBase(ln);
        if (x <= 0) {
            if (x == Double.NEGATIVE_INFINITY){
                return Double.NaN;
            }
            final double cosRes = cos.cos(x, eps),
                    sinRes = sin.solveFunc(x, eps),
                    cotRes = cotFunc.solveFunc(x, eps),
                    secRes = sec.solveFunc(x, eps);
            return Math.pow(cosRes*sinRes*cotRes, 2)*cotRes - secRes/cotRes;
        } else {
            if (x == Double.MAX_VALUE || x == Double.POSITIVE_INFINITY){
                return 0.0;
            }
            final double log3 = log.solveLog(x, 3, eps),
                    log2 = log.solveLog(x, 2, eps),
                    lg = log.solveLog(x, 10, eps),
                    log5 = log.solveLog(x, 5, eps),
                    loge = ln.ln(x, eps);
            final double num = ((1.0-log2)*(loge-log3))*log5;
            final double den = Math.pow(log3, 2)*lg*log3;
            if (num == 0.0 && den == 0.0){
                return 0.0;
            }
            else {
                return num/den;
            }
        }
    }

}
