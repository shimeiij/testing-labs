package org.testing.math.trig;

public class CosSecFunc extends AbsTrigFunc {

    public CosSecFunc(final CosFunc cos) {
        setBaseTrigFunc(cos);
    }

    @Override
    public Double solveFunc(double x, double acc) {
        return 1/cos.cos(Math.PI/2 - x, acc);
    }

    @Override
    public void setBaseTrigFunc(CosFunc cos) {
        this.cos = cos;
    }
}
