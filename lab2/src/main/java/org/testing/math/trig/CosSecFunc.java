package org.testing.math.trig;

public class CosSecFunc extends AbsTrigFunc {

    public CosSecFunc(final CosFunc cos) {
        super();
        this.cos = cos;
    }

    @Override
    public Double solveFunc(final double x, final double eps) {
        final double res = 1/cos.cos(Math.PI/2 - x, eps);
        writeToCsv(buildCSVRes(x, res, null), FILENAME);
        return res;
    }

    @Override
    public void setBaseTrigFunc(final CosFunc cos) {
        this.cos = cos;
    }
}
