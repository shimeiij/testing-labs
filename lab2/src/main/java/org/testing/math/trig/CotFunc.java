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


    @Override
    public Double solveFunc(final double x,final double eps) {
        return cos.cos(x, eps)/sinFunc.solveFunc(x, eps);
    }

    public List<String> getResList() {
        return resList;
    }

}
