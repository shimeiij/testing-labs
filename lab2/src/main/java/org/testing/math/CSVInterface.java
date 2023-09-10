package org.testing.math;

import org.testing.math.log.AbsLogFunc;

import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public interface CSVInterface {

    default void writeToCsv(final List<String> msg)
    {
        final StringBuilder builder = new StringBuilder("src/test/resources/");
        builder.append(getClass().getSimpleName());
        if (getClass().getSuperclass() == AbsLogFunc.class) {
            try {
                final Field field = getClass().getDeclaredField("base");
                field.setAccessible(true);
                final Object value = field.get(this);
                builder.append(value);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                Logger.getLogger(CSVInterface.class.getName()).log(Level.WARNING, e.getMessage());
            }
        }
        builder.append(".csv");
        try (BufferedWriter writer = new BufferedWriter(Files.newBufferedWriter(Path.of(builder.toString())))) {
            writer.write("x, func(x)" + System.lineSeparator());
            for (final String str : msg) {
                writer.write(str + System.lineSeparator());
            }
        } catch (IOException e) {
            Logger.getLogger(CSVInterface.class.getName()).log(Level.WARNING, e.getMessage());
        }
    }


}
