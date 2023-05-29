package org.testing.math;

import org.testing.math.log.AbsLogFunc;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface CSVInterface {
//    String FILENAME = "./res.csv";
    List<String> msg = new ArrayList<>();

    default void writeToCsv(List<String> msg)
    {
        StringBuilder builder = new StringBuilder("src/test/resources/");
        builder.append(getClass().getSimpleName());
        if (getClass().getSuperclass() == AbsLogFunc.class) {
            try {
                Field field = getClass().getDeclaredField("base");
                field.setAccessible(true);
                Object value = field.get(this);
                builder.append(value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        builder.append(".csv");
        try (BufferedWriter writer = new BufferedWriter(Files.newBufferedWriter(Path.of(builder.toString())))) {
            for (String str : msg) {
                writer.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            Logger.getLogger(CSVInterface.class.getName()).log(Level.WARNING, e.getMessage());
        }

    }


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
