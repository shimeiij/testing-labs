package org.testing.math.trig;


import java.util.ArrayList;
import java.util.List;

public class SinFunc extends AbsTrigFunc {
    List<String> resList;

    public SinFunc(final CosFunc cos) {
        super();
        this.cos = cos;
        resList = new ArrayList<>();
    }

    @Override
    public Double solveFunc(final double x, final double eps)  {
        return cos.cos(Math.PI/2 - x, eps);
    }

    public List<String> getResList() {
        return resList;
    }

}
