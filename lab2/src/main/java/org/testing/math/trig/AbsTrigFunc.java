package org.testing.math.trig;

import org.testing.math.CSVInterface;

import java.util.List;

public abstract class AbsTrigFunc implements CSVInterface {
    CosFunc cos;
    List<String> resList;


    public abstract Double solveFunc(double x, double eps);
//    public abstract void setBaseTrigFunc(CosFunc cos);


}
