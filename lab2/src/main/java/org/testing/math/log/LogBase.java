package org.testing.math.log;

public class LogBase extends AbsLogFunc {

    LogBase(BaseLog ln) {
        setLn(ln);
    }

    @Override
    public Double solveLog(double x, Long base, Long acc) {
        return ln.ln(x, acc)/ln.ln(base, acc);
    }

    @Override
    public void setLn(BaseLog ln) {
        this.ln = ln;
    }

}
