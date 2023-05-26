package org.testing.math;

import java.io.IOException;

public interface CSVInterface {
    String FILENAME = "res.csv";

    void writeToCsv(String str, String resource) throws IOException;

    default String buildCSVRes(double x, double res, Double base) {
        StringBuilder stringBuilder = new StringBuilder(getClass().getSimpleName());
        if (base != null) stringBuilder.append(base);
        stringBuilder.append("("); // ...
        stringBuilder.append(x);
        stringBuilder.append("),");
        stringBuilder.append(res);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
