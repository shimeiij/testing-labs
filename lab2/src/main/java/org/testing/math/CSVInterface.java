package org.testing.math;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface CSVInterface {
//    String FILENAME = "./res.csv";
    List<String> msg = new ArrayList<>();


    default void writeToCsv() {
        StringBuilder builder = new StringBuilder(getClass().getSuperclass().getSimpleName());
        builder.append(".csv");
        try (BufferedWriter writer = new BufferedWriter(Files.newBufferedWriter(Paths.get(builder.toString())))) {
            for (String str : msg) {
                writer.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            Logger.getLogger(CSVInterface.class.getName()).log(Level.WARNING, e.getMessage());
        }
    }

    default String buildCSVRes(final double x,
                               final double res)
    {
        final StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(x).append(',')
                .append(res);
        return stringBuilder.toString();
    }

}
