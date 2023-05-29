package org.testing.math.trig;


public class SecFunc extends AbsTrigFunc {

    public SecFunc(final CosFunc cos) {
        super();
        this.cos = cos;
    }

    @Override
    public Double solveFunc(final double x,final double eps) {
        final double res = 1/cos.cos(x, eps);
        this.msg.add(buildCSVRes(x, res));
        return res;
    }

//    @Override
//    public void setBaseTrigFunc(final CosFunc cos) {
//        this.cos = cos;
//    }
}
