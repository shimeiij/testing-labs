package org.testing.math.trig;

public class SecFunc extends AbsTrigFunc {

    SecFunc(final BaseTrig cos) {
        setBaseTrigFunc(cos);
    }

    SecFunc() {}

    @Override
    public Double solveFunc(Long x, Long acc) {
        return 1/cos.cos(x, acc);
    }

    @Override
    public void setBaseTrigFunc(BaseTrig cos) {
        this.cos = cos;
    }
}
