package org.testing.math;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface CSVInterface {
    String FILENAME = "res.csv";

    default void writeToCsv(final String str, final String file) {
        try (BufferedWriter writer = new BufferedWriter(Files.newBufferedWriter(Paths.get(file)))) {
            writer.append(str);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        default String buildCSVRes(final double x,
                               final double res,
                               final Double base) {
        final StringBuilder stringBuilder = new StringBuilder(getClass().getSimpleName());
        if (base != null){
            stringBuilder.append(base);
        }
        stringBuilder.append('(').append(x).append("),")
                .append(res)
                .append('\n');
        return stringBuilder.toString();
    }
}
