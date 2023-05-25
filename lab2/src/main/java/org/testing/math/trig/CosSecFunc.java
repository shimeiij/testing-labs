package org.testing.math.trig;

public class CosSecFunc extends AbsTrigFunc {

    CosSecFunc(final BaseTrig cos) {
        setBaseTrigFunc(cos);
    }

    CosSecFunc() {};

    @Override
    public Double solveFunc(Long x, Long acc) {
        double sq_sin = 1 - Math.pow(cos.cos(x, acc), 2);
        return 1/Math.sqrt(sq_sin);
    }

    @Override
    public void setBaseTrigFunc(BaseTrig cos) {
        this.cos = cos;
    }
}
