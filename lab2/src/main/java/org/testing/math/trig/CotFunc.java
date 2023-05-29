package org.testing.math.trig;

public class CotFunc extends AbsTrigFunc{
    SinFunc sinFunc;

    public CotFunc(final SinFunc sinFunc,final CosFunc cos)
    {
        super();
        this.cos = cos;
        this.sinFunc = sinFunc;
    }

    @Override
    public Double solveFunc(final double x,final double eps) {
        final double res = cos.cos(x, eps)/sinFunc.solveFunc(x, eps);
        writeToCsv(buildCSVRes(x, res, null), FILENAME);
        return res;
    }

//    @Override
//    public void setBaseTrigFunc(final CosFunc cos) {
//        this.cos = cos;
//    }
}
