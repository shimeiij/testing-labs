package org.testing.math;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface CSVInterface {
    String FILENAME = "res.csv";


    default void writeToCsv(final String str, final String file) {
        try (BufferedWriter writer = new BufferedWriter(Files.newBufferedWriter(Paths.get(file)))) {
            writer.append(str);
        } catch (IOException e) {
            Logger.getLogger(CSVInterface.class.getName()).log(Level.WARNING, e.getMessage());
        }
    }
        default String buildCSVRes(final double x,
                               final double res,
                               final Double base) {
        final StringBuilder stringBuilder = new StringBuilder(getClass().getSimpleName());
        if (base != null){
            stringBuilder.append('=').append(base);
        }
        stringBuilder.append('(').append(x).append("),")
                .append(res)
                .append('\n');
        return stringBuilder.toString();
    }
}
