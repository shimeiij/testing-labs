package math;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testing.MathSolver;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("test asin")
class MathTest {
    double delta = 1e-4;


    @ParameterizedTest(name = "{index}: x = {0}")
    @ValueSource(doubles = {Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NaN})
    void testInvalid(final double arg) {
        assertEquals(Double.NaN, MathSolver.calcAsin(arg));
    }

    @ParameterizedTest(name = "{index}: x = {0}")
    @ValueSource(doubles = {0.0, 0.5, 0.707_106_781_18, 1})
    void testPositive(final double arg) {
        assertEquals(Math.asin(arg), MathSolver.calcAsin(arg), delta);
    }

    @ParameterizedTest(name = "{index}: x = {0}")
    @ValueSource(doubles = {-1.0, -0.707_106_781_18, -0.5})
    void testNegative(final double arg) {
        assertEquals(Math.asin(arg), MathSolver.calcAsin(arg), delta);
    }

    @ParameterizedTest(name = "{index}: x = {0}")
    @ValueSource(doubles = {0.8, 0.85, 0.9, 0.999})
    void testRightConv(final double arg) {
        assertEquals(Math.asin(arg), MathSolver.calcAsin(arg), delta);
    }

    @ParameterizedTest(name = "{index}: x = {0}")
    @ValueSource(doubles = {-0.8, -0.85, -0.9, -0.999})
    void testLeftConv(final double arg) {
        assertEquals(Math.asin(arg), MathSolver.calcAsin(arg), delta);
    }

}
