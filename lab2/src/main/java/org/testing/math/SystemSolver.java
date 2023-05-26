package org.testing.math;

import org.testing.math.trig.CosFunc;
import org.testing.math.trig.CosSecFunc;
import org.testing.math.trig.SinFunc;
import org.testing.math.trig.TanFunc;

public class SystemSolver {

    public static void main(String[] args) {
        CosFunc cos = new CosFunc();
        SinFunc sin = new SinFunc(cos);
        TanFunc tan = new TanFunc(cos, sin);
        CosSecFunc cosSec = new CosSecFunc(cos);
        System.out.println(sin.solveFunc(Math.PI, 0.000001));
        System.out.println(sin.solveFunc(Math.PI, 0.000001));

//        System.out.println(cos.cos(Math.PI/2, 0.00001));
    }

}
