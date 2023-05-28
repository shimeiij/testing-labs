package org.testing.math.log;

public class LogBase extends AbsLogFunc {

    public LogBase(LogBaseN ln) {
        setLn(ln);
    }

    @Override
    public Double solveLog(double x, double base, double eps) {
        if (base == 1 || base <= 0) {
            throw new ArithmeticException("invalid base");
        }
        double res = ln.ln(x, eps)/ln.ln(base, eps);
        writeToCsv(buildCSVRes(x, res, base), FILENAME);
        return res;
    }

    @Override
    public void setLn(LogBaseN ln) {
        this.ln = ln;
    }

}
