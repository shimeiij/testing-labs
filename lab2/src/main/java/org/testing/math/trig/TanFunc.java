package org.testing.math.trig;

public class TanFunc extends AbsTrigFunc {
    SinFunc sin;

    TanFunc(BaseTrig cos, SinFunc sin) {
        setBaseTrigFunc(cos);
        this.sin = sin;
    }

    @Override
    public Double solveFunc(Long x, Long acc) {
        return sin.solveFunc(x, acc)/cos.cos(x, acc);
    }

    @Override
    public void setBaseTrigFunc(BaseTrig cos) {
        this.cos = cos;
    }

}
