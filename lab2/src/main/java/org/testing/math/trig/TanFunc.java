package org.testing.math.trig;

public class TanFunc extends AbsTrigFunc {
    SinFunc sin;

    public TanFunc(CosFunc cos, SinFunc sin) {
        setBaseTrigFunc(cos);
        this.sin = sin;
    }

    @Override
    public Double solveFunc(double x, double acc) {
        return sin.solveFunc(x, acc)/cos.cos(x, acc);
    }

    @Override
    public void setBaseTrigFunc(CosFunc cos) {
        this.cos = cos;
    }

}
