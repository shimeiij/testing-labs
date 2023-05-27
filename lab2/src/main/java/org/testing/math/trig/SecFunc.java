package org.testing.math.trig;

public class SecFunc extends AbsTrigFunc {

    public SecFunc(final CosFunc cos) {
        setBaseTrigFunc(cos);
    }

    @Override
    public Double solveFunc(double x, double eps) {
        return 1/cos.cos(x, eps);
    }

    @Override
    public void setBaseTrigFunc(CosFunc cos) {
        this.cos = cos;
    }
}
