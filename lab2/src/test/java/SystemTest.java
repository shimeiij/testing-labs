import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.testing.math.SystemSolver;
import org.testing.math.log.LogBase;
import org.testing.math.log.LogBaseN;
import org.testing.math.trig.CosFunc;
import org.testing.math.trig.CotFunc;
import org.testing.math.trig.SecFunc;
import org.testing.math.trig.SinFunc;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SystemTest {
    double eps = 1e-10;
    SystemSolver solver;
    LogBaseN ln = new LogBaseN();
    CosFunc cos = new CosFunc();


    @Test
    void testSystem()
    {
        LogBase logBase = Mockito.mock(LogBase.class);
        CotFunc cotFunc = Mockito.mock(CotFunc.class);
        SecFunc secFunc = Mockito.mock(SecFunc.class);
        SinFunc sinFunc = Mockito.mock(SinFunc.class);

        solver = new SystemSolver(ln, cos);
        solver.setLogBase(logBase);
        solver.setCotFunc(cotFunc);
        solver.setSecFunc(secFunc);
        solver.setSinFunc(sinFunc);


        double breakPoint1 = -Math.PI;
        double breakPoint2 = -Math.PI/2;
        double breakPoint3 = 0.0;
        Mockito.when(secFunc.solveFunc(breakPoint1, eps)).thenReturn(-1.0);
        Mockito.when(secFunc.solveFunc(breakPoint2, eps)).thenReturn(Double.NEGATIVE_INFINITY);
        Mockito.when(secFunc.solveFunc(breakPoint3, eps)).thenReturn(1.0);

        Mockito.when(sinFunc.solveFunc(breakPoint1, eps)).thenReturn(0.0);
        Mockito.when(sinFunc.solveFunc(breakPoint2, eps)).thenReturn(-1.0);
        Mockito.when(sinFunc.solveFunc(breakPoint3, eps)).thenReturn(0.0);

        Mockito.when(cotFunc.solveFunc(breakPoint1, eps)).thenReturn(Double.POSITIVE_INFINITY);
        Mockito.when(cotFunc.solveFunc(breakPoint2, eps)).thenReturn(0.0);
        Mockito.when(cotFunc.solveFunc(breakPoint3, eps)).thenReturn(Double.NEGATIVE_INFINITY);

        assertEquals(Double.POSITIVE_INFINITY, solver.solveSystem(breakPoint1, eps));

        double breakPoint4 = 1.0;
        Mockito.when(logBase.solveLog(breakPoint4, 2, eps)).thenReturn(0.0);
        Mockito.when(logBase.solveLog(breakPoint4, 3, eps)).thenReturn(1.0);
        Mockito.when(logBase.solveLog(breakPoint4, 5, eps)).thenReturn(1.0);
        Mockito.when(logBase.solveLog(breakPoint4, 10, eps)).thenReturn(0.0);

        assertEquals(Double.NEGATIVE_INFINITY, solver.solveSystem(breakPoint4, eps));
    }
}
