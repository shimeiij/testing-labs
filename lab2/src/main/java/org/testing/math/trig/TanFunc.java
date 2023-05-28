package org.testing.math.trig;

public class TanFunc extends AbsTrigFunc {
    SinFunc sin;

    public TanFunc(final CosFunc cos, final SinFunc sin) {
        super();
        this.cos = cos;
        this.sin = sin;
    }

    @Override
    public Double solveFunc(final double x,final double eps) {
        final double res = sin.solveFunc(x, eps)/cos.cos(x, eps);
        writeToCsv(buildCSVRes(x, res, null), FILENAME);
        return res;
    }

    @Override
    public void setBaseTrigFunc(final CosFunc cos) {
        this.cos = cos;
    }

}
