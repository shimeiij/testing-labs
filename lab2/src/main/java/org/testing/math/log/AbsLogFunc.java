package org.testing.math.log;

public abstract class AbsLogFunc {
    BaseLog ln;

    public abstract Double solveLog(Long x, Long base, Long acc);
    public abstract void setLn(BaseLog ln);

}
