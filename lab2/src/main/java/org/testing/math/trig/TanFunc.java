package org.testing.math.trig;

public class TanFunc extends TrigonometryFunc{
    BaseTrigonometric cos;
    SinFunc sin;

    TanFunc(BaseTrigonometric cos, SinFunc sin) {
        setBaseTrigFunc(cos);
        this.sin = sin;
    }

    @Override
    public Double solveFunc(Long x, Long acc) {
        return sin.solveFunc(x, acc)/cos.cos(x, acc);
    }

    @Override
    public void setBaseTrigFunc(BaseTrigonometric cos) {
        this.cos = cos;
    }

    @Override
    public BaseTrigonometric getBaseTrigonometricFunc() {
        return cos;
    }
}
