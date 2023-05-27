package org.testing.math.log;

import org.testing.math.CSVInterface;

public abstract class AbsLogFunc implements CSVInterface {
    LogBaseN ln;

    public abstract Double solveLog(double x, long base, double eps);
    public abstract void setLn(LogBaseN ln);

}
