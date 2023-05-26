package org.testing.math.trig;

import org.testing.math.CSVInterface;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public abstract class AbsTrigFunc implements CSVInterface {
    CosFunc cos;

    public abstract Double solveFunc(double x, double acc);
    public abstract void setBaseTrigFunc(CosFunc cos);

    @Override
    public void writeToCsv(String str, String file) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(file, true));
            writer.append(str);
            writer.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
