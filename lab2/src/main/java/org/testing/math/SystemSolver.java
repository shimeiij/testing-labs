package org.testing.math;

import org.testing.math.log.LogBase;
import org.testing.math.log.LogBaseN;
import org.testing.math.trig.*;

public class SystemSolver {
    LogBaseN ln;
    CosFunc cos;

    public SystemSolver(LogBaseN ln, CosFunc cos)
    {
        this.ln = ln;
        this.cos = cos;
    }

    public Double solveSystem(double x, double eps)
    {
        SinFunc sin = new SinFunc(cos);
        CotFunc cotFunc = new CotFunc(sin, cos);
        SecFunc sec = new SecFunc(cos);

        LogBase log = new LogBase(ln);
        if (x <= 0) {
            if (x == Double.NEGATIVE_INFINITY) return Double.NaN;
            double cos_res = cos.cos(x, eps), sin_res = sin.solveFunc(x, eps),
                    cot_res = cotFunc.solveFunc(x, eps), sec_res = sec.solveFunc(x, eps);
            return Math.pow(cos_res*sin_res*cot_res, 2)*cot_res - sec_res/cot_res;
        } else {
            if (x == Double.MAX_VALUE || x == Double.POSITIVE_INFINITY) return 0.0;
            double log3 = log.solveLog(x, 3, eps), log2 = log.solveLog(x, 2, eps),
                    lg = log.solveLog(x, 10, eps), log5 = log.solveLog(x, 5, eps), loge = ln.ln(x, eps);
            double num = ((1.0-log2)*(loge-log3))*log5;
            double den = Math.pow(log3, 2)*lg*log3;
            if (num == 0.0 && den == 0.0) return 0.0;
            else return num/den;
        }
    }

}
