package org.testing.math.log;

public class LogBase extends AbsLogFunc {

    public LogBase(LogBaseN ln) {
        setLn(ln);
    }

    @Override
    public Double solveLog(double x, long base, double eps) {
        if (base == 1 || base <= 0) {
            throw new ArithmeticException("invalid base");
        }
        return ln.ln(x, eps)/ln.ln(base, eps);
    }

    @Override
    public void setLn(LogBaseN ln) {
        this.ln = ln;
    }

}
