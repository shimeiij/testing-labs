package func;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testing.math.log.LogBase;
import org.testing.math.log.LogBaseN;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LogUnitTest {
    double testDelta = 1e-5;
    double eps = 1e-10;
    LogBaseN ln = new LogBaseN();
    LogBase log = new LogBase(ln);

    @ParameterizedTest
    @ValueSource(doubles = {1.0, 0.5, 0.01, 3.0, Math.E})
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
        assertEquals(Math.log(x)/Math.log(10), log.solveLog(x, 10, eps), 1e-3);
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
