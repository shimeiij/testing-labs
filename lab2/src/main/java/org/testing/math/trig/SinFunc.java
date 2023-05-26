package org.testing.math.trig;

import java.io.IOException;

public class SinFunc extends AbsTrigFunc {

    public SinFunc(CosFunc cos) {
        setBaseTrigFunc(cos);
    }

    @Override
    public Double solveFunc(double x, double acc)  {
        double res = cos.cos(Math.PI/2 - x, acc);
        writeToCsv(buildCSVRes(x, res, null), FILENAME);
        return res;
    }

    @Override
    public void setBaseTrigFunc(CosFunc cos) {
        this.cos = cos;
    }
}
