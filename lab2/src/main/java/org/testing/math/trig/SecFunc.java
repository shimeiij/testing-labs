package org.testing.math.trig;

public class SecFunc extends AbsTrigFunc {

    public SecFunc(final CosFunc cos) {
        setBaseTrigFunc(cos);
    }

    @Override
    public Double solveFunc(double x, double acc) {
        return 1/cos.cos(x, acc);
    }

    @Override
    public void setBaseTrigFunc(CosFunc cos) {
        this.cos = cos;
    }
}
