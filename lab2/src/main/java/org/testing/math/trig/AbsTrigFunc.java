package org.testing.math.trig;

import org.testing.math.CSVInterface;

public abstract class AbsTrigFunc implements CSVInterface {
    CosFunc cos;
    public abstract Double solveFunc(double x, double eps);
}
