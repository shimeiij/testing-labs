package org.testing.math.log;

public class LogBase extends AbsLogFunc {

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
        final double res = ln.ln(x, eps)/ln.ln(base, eps);
        this.msg.add(buildCSVRes(x, res));
        return res;
    }

//    @Override
//    public void setLn(final LogBaseN ln) {
//        this.ln = ln;
//    }
}
