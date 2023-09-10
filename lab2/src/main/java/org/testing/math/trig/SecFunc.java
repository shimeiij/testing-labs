package org.testing.math.trig;


import java.util.ArrayList;
import java.util.List;

public class SecFunc extends AbsTrigFunc {
    List<String> resList;

    public SecFunc(final CosFunc cos) {
        super();
        this.cos = cos;
        resList = new ArrayList<>();
    }

    @Override
    public Double solveFunc(final double x,final double eps) {
        return 1/cos.cos(x, eps);
    }

    public List<String> getResList() {
        return resList;
    }

}
