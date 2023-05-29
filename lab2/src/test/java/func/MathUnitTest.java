package func;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testing.math.log.LogBase;
import org.testing.math.log.LogBaseN;
import org.testing.math.trig.CosFunc;
import org.testing.math.trig.CotFunc;
import org.testing.math.trig.SecFunc;
import org.testing.math.trig.SinFunc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DisplayName("math-unit-tests")
class MathUnitTest {
//    double delta = 1e-7;

    @Nested
    class LogarithmTest {
        double testDelta = 1e-5;
        double eps = 1e-8;
        LogBaseN ln = new LogBaseN();
        LogBase log = new LogBase(ln);


        @ParameterizedTest
        @ValueSource(doubles = {0.01, 0.5, 1.0, 3.0, Math.E, 1e10})
        void testLn(final double x) {
            assertEquals(Math.log(x), ln.ln(x, eps), testDelta);
        }

        @ParameterizedTest
        @ValueSource(doubles = {0.01, 0.5, 1.0, 2.0, 8.0, 2^200})
        void testLogBase2(final double x) {
            assertEquals(Math.log(x)/Math.log(2), log.solveLog(x, 2, eps), testDelta);
        }

        @ParameterizedTest
        @ValueSource(doubles = {0.01, 0.3, 1.0, 3.0, 9.0, 3^200})
        void testLogBase3(final double x) {
            assertEquals(Math.log(x)/Math.log(3), log.solveLog(x, 3, eps), testDelta);
        }

        @ParameterizedTest
        @ValueSource(doubles = {0.01, 0.2, 1.0, 3.0, 9.0, 3^200})
        void testLogBase5(final double x) {
            assertEquals(Math.log(x)/Math.log(5), log.solveLog(x, 5, eps), testDelta);
        }

        @ParameterizedTest
        @ValueSource(doubles = {0.01, 1.0, 10.0, 100.0, 10^200})
        void testLogBase10(final double x) {
            assertEquals(Math.log(x)/Math.log(10), log.solveLog(x, 10, eps), testDelta);
        }

        @ParameterizedTest
        @ValueSource(doubles = {-1.0, 0.0})
        void testInvalidArg(final double x) {
            final Exception exception = assertThrows(ArithmeticException.class, () -> ln.ln(x, eps));
            assertEquals("argument must be > 0", exception.getMessage());
        }

        @ParameterizedTest
        @ValueSource(doubles = {-1.0, 0.0, 1.0})
        void testInvalidBase(final double base) {
            final Exception exception = assertThrows(ArithmeticException.class, () -> log.solveLog(1, base, eps));
            assertEquals("invalid base", exception.getMessage());
        }

    }

    @Nested
    class TrigTest {
        double testDelta = 1e-6;
        double eps = 1e-10;

        CosFunc cos = new CosFunc();

        @ParameterizedTest
        @ValueSource(doubles = {-Math.PI, 0.0, Math.PI/4, Math.PI/2, Math.PI, 3*Math.PI/2, 8*Math.PI})
        void testCos(final double x) {
            assertEquals(Math.cos(x), cos.cos(x, eps), testDelta);
        }

        @ParameterizedTest
        @ValueSource(doubles = {-Math.PI, 0.0, Math.PI/4, Math.PI/2, Math.PI, 3*Math.PI/2, 8*Math.PI})
        void testSin(final double x) {
            final SinFunc sinFunc = new SinFunc(cos);
            assertEquals(Math.sin(x), sinFunc.solveFunc(x, eps), testDelta);
        }

        @ParameterizedTest
        @ValueSource(doubles = {Math.PI/4, Math.PI/2, 3*Math.PI/2})
        void testCot(final double x) {
            final SinFunc sinFunc = new SinFunc(cos);
            final CotFunc cotFunc = new CotFunc(sinFunc, cos);
            assertEquals(1.0/Math.tan(x), cotFunc.solveFunc(x, eps), testDelta);
        }

        @ParameterizedTest
        @ValueSource(doubles = {-Math.PI, 0.0, Math.PI/4, Math.PI, 8*Math.PI})
        void testSec(final double x) {
            final SecFunc secFunc = new SecFunc(cos);
            assertEquals(1.0/Math.cos(x), secFunc.solveFunc(x, eps), testDelta);
        }

        @Test
        void testInvalid() {
            final SecFunc secFunc = new SecFunc(cos);
            final SinFunc sinFunc = new SinFunc(cos);
            final CotFunc cotFunc = new CotFunc(sinFunc, cos);
            assertEquals(Double.NEGATIVE_INFINITY, cotFunc.solveFunc(0.0, eps), testDelta);
            assertEquals(Double.POSITIVE_INFINITY, cotFunc.solveFunc(-Math.PI, eps), testDelta);
            assertEquals(Double.NEGATIVE_INFINITY, secFunc.solveFunc(Math.PI/2, eps), testDelta);
        }

    }

}
