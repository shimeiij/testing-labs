package org.testing.math;

import org.testing.math.log.AbsLogFunc;
import org.testing.math.log.LogBase;
import org.testing.math.log.LogBaseN;
import org.testing.math.trig.*;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SystemSolver {
    LogBaseN ln;
    CosFunc cos;
    AbsTrigFunc sinFunc;
    AbsTrigFunc cotFunc;
    AbsTrigFunc secFunc;
    AbsLogFunc logBase;


    public SystemSolver(final LogBaseN ln,
                        final CosFunc cos)  {
        super();
        this.ln = ln;
        this.cos = cos;
    }

    private void printRes(CSVInterface csvObj) {
        csvObj.writeToCsv();
    }

    public Double solveSystem(final double x,
                              final double eps)
    {
        if (x <= 0) {
            sinFunc = new SinFunc(cos);
            cotFunc = new CotFunc(sinFunc, cos);
            secFunc = new SecFunc(cos);

            final double cotRes = cotFunc.solveFunc(x, eps);
            if (cotRes == Double.POSITIVE_INFINITY || cotRes == Double.NEGATIVE_INFINITY) {
                printRes(cotFunc);
                return cotRes;
            }
            final double cosRes = cos.cos(x, eps),
                    sinRes = sinFunc.solveFunc(x, eps),
                    secRes = secFunc.solveFunc(x, eps);
            printRes(cotFunc);
            return Math.pow(cosRes*sinRes*cotRes, 2)*cotRes - secRes/cotRes;
        } else {
            logBase = new LogBase(ln);
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
            printRes(logBase);
            if (num == 0.0 && den == 0.0){
                return Double.POSITIVE_INFINITY;
            }
            else {
                return num/den;
            }
        }
    }

    public void setSinFunc(final SinFunc sinFunc) {
        this.sinFunc = sinFunc;
    }

    public void setCotFunc(final CotFunc cotFunc) {
        this.cotFunc = cotFunc;
    }

    public void setSecFunc(final SecFunc secFunc) {
        this.secFunc = secFunc;
    }

    public void setLogBase(final LogBase logBase) {
        this.logBase = logBase;
    }



}
