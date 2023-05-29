package org.testing.math.log;

import java.util.ArrayList;
import java.util.List;

public class LogBase extends AbsLogFunc {
    List<String> resList;
    double base;

    public LogBase(final LogBaseN ln) {
        super();
        this.ln = ln;
        resList = new ArrayList<>();
    }


    @Override
    public Double solveLog(final double x,
                           final double base,
                           final double eps) {
        if (base == 1 || base <= 0) {
            throw new ArithmeticException("invalid base");
        }
        this.base = base;
        final double res = ln.ln(x, eps)/ln.ln(base, eps);
        this.msg.add(buildCSVRes(x, res));
        addRes(x, res);
        return res;
    }

    public void addRes(final double x, final double res) {
        StringBuilder builder = new StringBuilder();
        builder.append(x).append(',').append(res);
        resList.add(builder.toString());
    }

    public List<String> getResList() {
        return resList;
    }

    //    @Override
//    public void setLn(final LogBaseN ln) {
//        this.ln = ln;
//    }
}
