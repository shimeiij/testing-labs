package func;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testing.math.trig.CosFunc;
import org.testing.math.trig.CotFunc;
import org.testing.math.trig.SecFunc;
import org.testing.math.trig.SinFunc;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TrigUnitTest {
    double testDelta = 1e-6;
    double eps = 1e-10;

    CosFunc cos = new CosFunc();
    final SinFunc sinFunc = new SinFunc(cos);
    final CotFunc cotFunc = new CotFunc(sinFunc, cos);
    final SecFunc secFunc = new SecFunc(cos);


    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, 0.0, Math.PI/4, Math.PI/2, Math.PI, 3*Math.PI/2, 8*Math.PI})
    void testCos(final double x) {
        assertEquals(Math.cos(x), cos.cos(x, eps), testDelta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, 0.0, Math.PI/4, Math.PI/2, Math.PI, 3*Math.PI/2, 8*Math.PI})
    void testSin(final double x) {
        assertEquals(Math.sin(x), sinFunc.solveFunc(x, eps), testDelta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {Math.PI/4, Math.PI/2, 3*Math.PI/2})
    void testCot(final double x) {
        assertEquals(1.0/Math.tan(x), cotFunc.solveFunc(x, eps), testDelta);
    }

    @ParameterizedTest
    @ValueSource(doubles = {-Math.PI, 0.0, Math.PI/4, Math.PI, 8*Math.PI})
    void testSec(final double x) {
        assertEquals(1.0/Math.cos(x), secFunc.solveFunc(x, eps), testDelta);
    }

    @Test
    void testInfinity() {
        assertEquals(Double.NEGATIVE_INFINITY, cotFunc.solveFunc(0.0, eps), testDelta);
        assertEquals(Double.POSITIVE_INFINITY, cotFunc.solveFunc(-Math.PI, eps), testDelta);
        assertEquals(Double.NEGATIVE_INFINITY, secFunc.solveFunc(Math.PI/2, eps), testDelta);
    }

}
