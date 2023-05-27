package org.testing.math;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public interface CSVInterface {
    String FILENAME = "res.csv";

    default void writeToCsv(String str, String file) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(str);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    };

    default String buildCSVRes(double x, double res, Double base) {
        StringBuilder stringBuilder = new StringBuilder(getClass().getSimpleName());
        if (base != null) stringBuilder.append(base);
        stringBuilder.append("("); // no comments
        stringBuilder.append(x);
        stringBuilder.append("),");
        stringBuilder.append(res);
        stringBuilder.append("\n");
        return stringBuilder.toString();
    }
}
