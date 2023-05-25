package org.testing.math.trig;

public class SecFunc extends TrigonometryFunc{
    BaseTrigonometric cos;

    SecFunc(final BaseTrigonometric cos) {
        setBaseTrigFunc(cos);
    }

    SecFunc() {};

    @Override
    public Double solveFunc(Long x, Long acc) {
        return 1/cos.cos(x, acc);
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
