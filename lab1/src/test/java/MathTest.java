import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testing.MathSolver;

import static org.junit.jupiter.api.Assertions.assertEquals;


@DisplayName("test asin")
public class MathTest {
    double delta = 1e-4;

    @ParameterizedTest(name = "{index}: x = {0}")
    @ValueSource(doubles = {0.0})
    void testZero(double arg) {
        assertEquals(0, MathSolver.calcAsin(arg));
    }

    @ParameterizedTest(name = "{index}: x = {0}")
    @ValueSource(doubles = {Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY, Double.NaN})
    void testInvalid(double arg) {
        assertEquals(Double.NaN, MathSolver.calcAsin(arg));
    }

    @ParameterizedTest(name = "{index}: x = {0}")
    @ValueSource(doubles = {0.5, 0.70710678118, 1})
    void testPositive(double arg) {
        assertEquals(Math.asin(arg), MathSolver.calcAsin(arg), delta);
    }

    @ParameterizedTest(name = "{index}: x = {0}")
    @ValueSource(doubles = {-1.0, -0.70710678118, -0.5})
    void testNegative(double arg) {
        assertEquals(Math.asin(arg), MathSolver.calcAsin(arg), delta);
    }

    @ParameterizedTest(name = "{index}: x = {0}")
    @ValueSource(doubles = {0.8, 0.85, 0.9, 0.999})
    void testRightConv(double arg) {
        assertEquals(Math.asin(arg), MathSolver.calcAsin(arg), delta);
    }

    @ParameterizedTest(name = "{index}: x = {0}")
    @ValueSource(doubles = {-0.8, -0.85, -0.9, -0.999})
    void testLeftConv(double arg) {
        assertEquals(Math.asin(arg), MathSolver.calcAsin(arg), delta);
    }

}
