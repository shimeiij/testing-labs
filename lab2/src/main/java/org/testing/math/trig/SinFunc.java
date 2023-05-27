package org.testing.math.trig;


public class SinFunc extends AbsTrigFunc {

    public SinFunc(CosFunc cos) {
        setBaseTrigFunc(cos);
    }

    @Override
    public Double solveFunc(double x, double eps)  {
        double res = cos.cos(Math.PI/2 - x, eps);
        writeToCsv(buildCSVRes(x, res, null), FILENAME);
        return res;
    }

    @Override
    public void setBaseTrigFunc(CosFunc cos) {
        this.cos = cos;
    }
}
