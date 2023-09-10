package org.testing.math.log;

public class LogBase extends AbsLogFunc {
    double base;

    public LogBase(final LogBaseN ln) {
        super();
        this.ln = ln;
    }

    @Override
    public Double solveLog(final double x,
                           final double base,
                           final double eps) {
        if (base == 1 || base <= 0) {
            throw new ArithmeticException("invalid base");
        }
        this.base = base;
        double natLog = ln.ln(x, eps);
        double res;
        if (base != 10) {
            res = natLog/ln.ln(base, eps);
        } else {
            res = natLog/2.303;
        }
        return res;
    }

}
