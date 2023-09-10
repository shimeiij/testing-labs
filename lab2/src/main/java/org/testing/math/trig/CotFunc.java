package org.testing.math.trig;

import java.util.ArrayList;
import java.util.List;

public class CotFunc extends AbsTrigFunc{
    AbsTrigFunc sinFunc;
    List<String> resList;

    public CotFunc(final AbsTrigFunc sinFunc,final CosFunc cos)
    {
        super();
        this.cos = cos;
        this.sinFunc = sinFunc;
        resList = new ArrayList<>();
    }

    public void addRes(final double x, final double res) {
        final StringBuilder builder = new StringBuilder();
        builder.append(x).append(',').append(res);
        resList.add(builder.toString());
    }

    @Override
    public Double solveFunc(final double x,final double eps) {
        final double res = cos.cos(x, eps)/sinFunc.solveFunc(x, eps);
        this.MSG.add(buildCSVRes(x, res));
        addRes(x, res);
        return res;
    }

    public List<String> getResList() {
        return resList;
    }

    //    @Override
//    public void setBaseTrigFunc(final CosFunc cos) {
//        this.cos = cos;
//    }
}
