package org.testing.math;

import org.testing.math.log.LogBase;
import org.testing.math.log.LogBaseN;

public class SystemSolver {

    public static void main(String[] args) {
        double eps = 1e-10;
        LogBaseN ln = new LogBaseN();
        LogBase log = new LogBase(ln);
        double x = 8;
        System.out.println(ln.ln(x, eps));
        System.out.println(log.solveLog(x, 2, 1e3));
    }

}
