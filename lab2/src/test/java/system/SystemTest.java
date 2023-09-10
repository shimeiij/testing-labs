package system;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;
import org.testing.math.SystemSolver;
import org.testing.math.log.LogBase;
import org.testing.math.log.LogBaseN;
import org.testing.math.trig.CosFunc;
import org.testing.math.trig.CotFunc;
import org.testing.math.trig.SecFunc;
import org.testing.math.trig.SinFunc;


import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SystemTest {
    double eps = 1e-10;
    double delta = 1e-7;
    LogBaseN ln = new LogBaseN();
    CosFunc cos = new CosFunc();
    SystemSolver solver = new SystemSolver(ln, cos);
    List<Double> xAxis = DoubleStream.iterate(-5, k -> k+0.1).limit(70).boxed().collect(Collectors.toList());

    @BeforeAll
    void graph() {
        xAxis.forEach(x -> {
            final double res = solver.solveSystem(x, 1e-3);
            solver.addRes(x, res);
        });
        solver.writeToCsv(solver.getSystemRes());
    }

//    @AfterEach
//    void writeToCSV() {
//        solver.writeToCsv(solver.getSystemRes());
//    }



    @Test
    void testSystem()
    {
        final LogBase logBase = Mockito.mock(LogBase.class);
        final CotFunc cotFunc = Mockito.mock(CotFunc.class);
        final SecFunc secFunc = Mockito.mock(SecFunc.class);
        final SinFunc sinFunc = Mockito.mock(SinFunc.class);
        solver.setLogBase(logBase);
        solver.setCotFunc(cotFunc);
        solver.setSecFunc(secFunc);
        solver.setSinFunc(sinFunc);
        final double breakPoint1 = -Math.PI,
                breakPoint2 = -Math.PI/2,
                breakPoint3 = 0.0;
        Mockito.when(secFunc.solveFunc(breakPoint1, eps)).thenReturn(-1.0);
        Mockito.when(secFunc.solveFunc(breakPoint2, eps)).thenReturn(Double.NEGATIVE_INFINITY);
        Mockito.when(secFunc.solveFunc(breakPoint3, eps)).thenReturn(1.0);
        Mockito.when(sinFunc.solveFunc(breakPoint1, eps)).thenReturn(0.0);
        Mockito.when(sinFunc.solveFunc(breakPoint2, eps)).thenReturn(-1.0);
        Mockito.when(sinFunc.solveFunc(breakPoint3, eps)).thenReturn(0.0);
        Mockito.when(cotFunc.solveFunc(breakPoint1, eps)).thenReturn(Double.POSITIVE_INFINITY);
        Mockito.when(cotFunc.solveFunc(breakPoint2, eps)).thenReturn(0.0);
        Mockito.when(cotFunc.solveFunc(breakPoint3, eps)).thenReturn(Double.NEGATIVE_INFINITY);
        double x  = -5.709;
        final double res = solver.solveSystem(x, eps);
        assertEquals(0.0, res, 1e-2);
        solver.addRes(x, res);
        x = -3.791;
        final double res2 = solver.solveSystem(-3.791, eps);
        assertEquals(-1.483, res2, 1e-2);
        solver.addRes(x, res2);
        final double res3 = solver.solveSystem(breakPoint1, eps);
        assertEquals(Double.POSITIVE_INFINITY, res3, 1e-2);
        solver.addRes(breakPoint1, res3);
        x = -2.492;
        final double res4 = solver.solveSystem(x, eps);
        assertEquals(1.483, res4, 1e-2);
        solver.addRes(x, res4);
        final double res5 = solver.solveSystem(breakPoint2, eps);
        assertEquals(Double.POSITIVE_INFINITY, res5, delta);
        solver.addRes(x, res5);

        final double res6 = solver.solveSystem(breakPoint3, eps);
        assertEquals(Double.NEGATIVE_INFINITY, res6, 1e-2);
        solver.addRes(breakPoint3, res6);

        x = 0.5;
        final double res7 = solver.solveSystem(x, 1e-4);
        assertEquals(0.708_827, res7, 1e-2);
        solver.addRes(x, res);

        final double breakPoint4 = 1.0;
        Mockito.when(logBase.solveLog(breakPoint4, 2, eps)).thenReturn(0.0);
        Mockito.when(logBase.solveLog(breakPoint4, 3, eps)).thenReturn(0.0);
        Mockito.when(logBase.solveLog(breakPoint4, 5, eps)).thenReturn(0.0);
        Mockito.when(logBase.solveLog(breakPoint4, 10, eps)).thenReturn(0.0);

        final double res8 = solver.solveSystem(breakPoint4, 1e-8);
        assertEquals(Double.POSITIVE_INFINITY, solver.solveSystem(breakPoint4, 1e-8), delta);
        solver.addRes(breakPoint4, res8);
//        solver.writeToCsv(solver.getSystemRes());
        solver.getCos().writeToCsv(solver.getCos().getResList());
        solver.getCotFunc().writeToCsv(solver.getCotFunc().getResList());
        solver.getSecFunc().writeToCsv(solver.getSecFunc().getResList());
        solver.getSinFunc().writeToCsv(solver.getSinFunc().getResList());

//        solver.getLogBase().writeToCsv(solver.getLogBase().getResList());
    }

    @ParameterizedTest
    @ValueSource(doubles = {Double.MAX_VALUE, Double.POSITIVE_INFINITY})
    void testIndeterminacy(final double x) {
        assertEquals(0.0, solver.solveSystem(x, 1e-8), delta);
    }
}
