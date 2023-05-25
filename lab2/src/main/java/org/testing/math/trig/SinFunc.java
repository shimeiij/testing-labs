package org.testing.math.trig;

public class SinFunc extends AbsTrigFunc {

    SinFunc(BaseTrig cos) {
        super();
        setBaseTrigFunc(cos);
    }

    SinFunc() {}

    @Override
    public Double solveFunc(Long x, Long acc) {
        double sq_sin = 1 - Math.pow(cos.cos(x, acc), 2);
        return Math.sqrt(sq_sin);
    }

    @Override
    public void setBaseTrigFunc(BaseTrig cos) {
        this.cos = cos;
    }
}
