package org.testing.math.trig;

public class CosSecFunc extends AbsTrigFunc {

    public CosSecFunc(final CosFunc cos) {
        setBaseTrigFunc(cos);
    }

    @Override
    public Double solveFunc(double x, double eps) {
        return 1/cos.cos(Math.PI/2 - x, eps);
    }

    @Override
    public void setBaseTrigFunc(CosFunc cos) {
        this.cos = cos;
    }
}
