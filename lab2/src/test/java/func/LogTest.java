package func;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.testing.math.log.LogBase;
import org.testing.math.log.LogBaseN;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


class LogTest {
    double testDelta = 1e-2;

    double eps = 1e-3;
    LogBaseN ln = new LogBaseN();
    LogBase log = new LogBase(ln);

    List<Double> xAxis = DoubleStream.iterate(0.1, k -> k+.1).limit(10).boxed().collect(Collectors.toList());
    List<Double> log10XAxis = DoubleStream.iterate(0.1, k -> k*10).limit(4).boxed().collect(Collectors.toList());
//    List<Double> log2_x_axis = DoubleStream.iterate(1, k -> k+0.1).limit(10).boxed().collect(Collectors.toList());


    @TestFactory
    Stream<DynamicTest> testLn() {
        return xAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            final double res = ln.ln(x, eps);
            assertEquals(Math.log(x), res, testDelta);
        }));
    }

    @TestFactory
    Stream<DynamicTest> testLog2() {
        return xAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            final double res = log.solveLog(x, 2, 1e-7);
            assertEquals(Math.log(x)/Math.log(2), res, testDelta);
        }));
    }

    @TestFactory
    Stream<DynamicTest> testLog3() {
        return xAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            final double res = log.solveLog(x, 3, eps);
            assertEquals(Math.log(x)/Math.log(3), res, testDelta);
        }));
    }

    @TestFactory
    Stream<DynamicTest> testLog5() {
        return xAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            final double res = log.solveLog(x, 5, eps);
            assertEquals(Math.log(x)/Math.log(5), res, testDelta);
        }));
    }

    @TestFactory
    Stream<DynamicTest> testLog10() {
        return log10XAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            final double res = log.solveLog(x, 10, 1e-7);
            assertEquals(Math.log(x)/Math.log(10), res, testDelta);
        }));
    }


    @AfterEach
    void writeToCSV()
    {
        log.writeToCsv(log.getResList());
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class LogarithmTest {
        double testDelta = 1e-5;
        double eps = 1e-8;
        LogBaseN ln = new LogBaseN();
        LogBase log = new LogBase(ln);

        @AfterAll
        void writeToCSV() {
            log.writeToCsv(log.getResList());
        }

        @ParameterizedTest
        @ValueSource(doubles = {1.0, 0.5, 0.01, 3.0, Math.E, 1e10})
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

}
