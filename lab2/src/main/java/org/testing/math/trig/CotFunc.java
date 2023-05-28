package org.testing.math.trig;

public class CotFunc extends AbsTrigFunc{
    SinFunc sinFunc;

    public CotFunc(SinFunc sinFunc, CosFunc cos)
    {
        setBaseTrigFunc(cos);
        this.sinFunc = sinFunc;
    }

    @Override
    public Double solveFunc(double x, double eps) {
        double res = cos.cos(x, eps)/sinFunc.solveFunc(x, eps);
        writeToCsv(buildCSVRes(x, res, null), FILENAME);
        return res;
    }

    @Override
    public void setBaseTrigFunc(CosFunc cos) {
        this.cos = cos;
    }
}
