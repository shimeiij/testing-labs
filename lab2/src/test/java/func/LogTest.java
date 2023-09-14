package func;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.testing.math.ResAdder;
import org.testing.math.log.LogBase;
import org.testing.math.log.LogBaseN;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogTest {
    double testDelta = 1e-5;
    double eps = 1e-10;
    LogBaseN ln = new LogBaseN();
    LogBase log = new LogBase(ln);

    final ResAdder resAdder = new ResAdder();
    List<String> resList = new ArrayList<>();

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class LnTest {
        @AfterAll
        void write() {
            log.writeToCsv(resList);
            resList.clear();
        }

        @ParameterizedTest
        @MethodSource("provideArgs")
        void testLn(final Double x) {
            final double res = ln.ln(x, eps);
            assertEquals(Math.log(x), res, testDelta);
            resAdder.addRes(x, res, resList);
        }

        static DoubleStream provideArgs() {
            return DoubleStream.iterate(0.1, k -> k+.1).limit(1001);
        }

    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class Log2Test {
        @AfterAll
        void write() {
            log.writeToCsv(resList);
            resList.clear();
        }

        @ParameterizedTest
        @MethodSource("provideArgs")
        void testLog2(final Double x) {
            final double res = log.solveLog(x, 2, 1e-7);
            assertEquals(Math.log(x)/Math.log(2), res, testDelta);
            resAdder.addRes(x, res, resList);
        }

        static DoubleStream provideArgs() {
            return DoubleStream.iterate(0.1, k -> k+.1).limit(1001);
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class Log3Test {
        @AfterAll
        void write() {
            log.writeToCsv(resList);
            resList.clear();
        }

        @ParameterizedTest
        @MethodSource("provideArgs")
        void testLog3(final Double x) {
            final double res = log.solveLog(x, 3, eps);
            assertEquals(Math.log(x)/Math.log(3), res, testDelta);
            resAdder.addRes(x, res, resList);
        }

        static DoubleStream provideArgs() {
            return DoubleStream.iterate(0.1, k -> k+.1).limit(1001);
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class Log5Test {
        @AfterAll
        void write() {
            log.writeToCsv(resList);
            resList.clear();
        }

        @ParameterizedTest
        @MethodSource("provideArgs")
        void testLog5(final Double x) {
            final double res = log.solveLog(x, 5, eps);
            assertEquals(Math.log(x)/Math.log(5), res, testDelta);
            resAdder.addRes(x, res, resList);
        }

        static DoubleStream provideArgs() {
            return DoubleStream.iterate(0.1, k -> k+.1).limit(1001);
        }
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class Log10Test {
        @AfterAll
        void write() {
            log.writeToCsv(resList);
            resList.clear();
        }

        @ParameterizedTest
        @MethodSource("provideArgs")
        void testLog10(final Double x) {
            final double res = log.solveLog(x, 10, eps);
            assertEquals(Math.log(x)/Math.log(10), res, 1e-3);
            resAdder.addRes(x, res, resList);
        }

        static DoubleStream provideArgs() {
            return DoubleStream.iterate(0.1, k -> k+.1).limit(1001);
        }
    }

}
