package org.testing.math.trig;

import org.testing.math.CSVInterface;

public class SecFunc extends AbsTrigFunc {

    public SecFunc(final CosFunc cos) {
        setBaseTrigFunc(cos);
    }

    @Override
    public Double solveFunc(double x, double eps) {
        double res = 1/cos.cos(x, eps);
        writeToCsv(buildCSVRes(x, res, null), FILENAME);
        return res;
    }

    @Override
    public void setBaseTrigFunc(CosFunc cos) {
        this.cos = cos;
    }
}
