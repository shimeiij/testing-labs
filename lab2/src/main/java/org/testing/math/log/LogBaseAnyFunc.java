package org.testing.math.log;

public class LogBaseAnyFunc extends AbsLogFunc {

    LogBaseAnyFunc(BaseLog ln) {
        setLn(ln);
    }

    @Override
    public Double solveLog(Long x, Long base, Long acc) {
        return ln.ln(x, acc)/ln.ln(base, acc);
    }

    @Override
    public void setLn(BaseLog ln) {
        this.ln = ln;
    }

}
