package org.testing.math.trig;

public class TanFunc extends AbsTrigFunc {
    SinFunc sin;

    public TanFunc(CosFunc cos, SinFunc sin) {
        setBaseTrigFunc(cos);
        this.sin = sin;
    }

    @Override
    public Double solveFunc(double x, double eps) {
        return sin.solveFunc(x, eps)/cos.cos(x, eps);
    }

    @Override
    public void setBaseTrigFunc(CosFunc cos) {
        this.cos = cos;
    }

}
