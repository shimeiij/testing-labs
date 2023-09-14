package org.testing.math;

import java.util.List;

public class ResAdder {


    public void addRes(final double x, final double res, final List<String> resList) {
        final StringBuilder builder = new StringBuilder();
        builder.append(x).append(',').append(res);
        resList.add(builder.toString());
    }


}
