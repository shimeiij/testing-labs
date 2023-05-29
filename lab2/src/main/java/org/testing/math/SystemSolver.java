package org.testing.math;

import org.testing.math.log.LogBase;
import org.testing.math.log.LogBaseN;
import org.testing.math.trig.*;

public class SystemSolver {
    LogBaseN ln;
    CosFunc cos;
    SinFunc sinFunc;
    CotFunc cotFunc;
    SecFunc secFunc;
    LogBase logBase;


    public SystemSolver(final LogBaseN ln,
                        final CosFunc cos)
    {
        this.ln = ln;
        this.cos = cos;
        sinFunc = new SinFunc(cos);
        cotFunc = new CotFunc(sinFunc, cos);
        secFunc = new SecFunc(cos);
        logBase = new LogBase(ln);
    }

    public Double solveSystem(final double x,final double eps)
    {

        if (x <= 0) {
            if (x == Double.NEGATIVE_INFINITY){
                return Double.NaN;
            }
            final double cosRes = cos.cos(x, eps),
                    sinRes = sinFunc.solveFunc(x, eps),
                    cotRes = cotFunc.solveFunc(x, eps),
                    secRes = secFunc.solveFunc(x, eps);
            if (cotRes == Double.POSITIVE_INFINITY || cotRes == Double.NEGATIVE_INFINITY) {
                return cotRes;
            }
            return Math.pow(cosRes*sinRes*cotRes, 2)*cotRes - secRes/cotRes;
        } else {
            if (x == Double.MAX_VALUE || x == Double.POSITIVE_INFINITY){
                return 0.0;
            }
            final double log3 = logBase.solveLog(x, 3, eps),
                    log2 = logBase.solveLog(x, 2, eps),
                    lg = logBase.solveLog(x, 10, eps),
                    log5 = logBase.solveLog(x, 5, eps),
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

    public void setSinFunc(SinFunc sinFunc) {
        this.sinFunc = sinFunc;
    }

    public void setCotFunc(CotFunc cotFunc) {
        this.cotFunc = cotFunc;
    }

    public void setSecFunc(SecFunc secFunc) {
        this.secFunc = secFunc;
    }

    public void setLogBase(LogBase logBase) {
        this.logBase = logBase;
    }

}
