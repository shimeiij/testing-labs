package org.testing.math.log;

import org.testing.math.CSVInterface;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public abstract class AbsLogFunc implements CSVInterface {
    LogBaseN ln;

    public abstract Double solveLog(double x, double base, double eps);
//    public abstract void setLn(LogBaseN ln);


}
