package func;

import org.junit.jupiter.api.*;
import org.testing.math.log.LogBase;
import org.testing.math.log.LogBaseN;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;


class LogTest {
    double testDelta = 1e-5;
    double eps = 1e-10;
    LogBaseN ln = new LogBaseN();
    LogBase log = new LogBase(ln);

    List<String> resList = new ArrayList<>();
    List<Double> xAxis = DoubleStream.iterate(0.1, k -> k+.1).limit(1001).boxed().collect(Collectors.toList());

    void addRes(final double x, final double res) {
        final StringBuilder builder = new StringBuilder();
        builder.append(x).append(',').append(res);
        resList.add(builder.toString());
    }

    @AfterEach
    void writeToCSV() {
        log.writeToCsv(resList);
        resList.clear();
    }

    @TestFactory
    Stream<DynamicTest> testLn() {
        return xAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            final double res = ln.ln(x, eps);
            assertEquals(Math.log(x), res, testDelta);
            addRes(x, res);
        }));
    }

    @TestFactory
    Stream<DynamicTest> testLog2() {
        return xAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
                final double res = log.solveLog(x, 2, 1e-7);
                assertEquals(Math.log(x)/Math.log(2), res, testDelta);
                addRes(x, res);
        }));
    }

    @TestFactory
    Stream<DynamicTest> testLog3() {
        return xAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            final double res = log.solveLog(x, 3, eps);
            assertEquals(Math.log(x)/Math.log(3), res, testDelta);
            addRes(x, res);
        }));
    }

    @TestFactory
    Stream<DynamicTest> testLog5() {
        return xAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            final double res = log.solveLog(x, 5, eps);
            assertEquals(Math.log(x)/Math.log(5), res, testDelta);
            addRes(x, res);
        }));
    }

    @TestFactory
    Stream<DynamicTest> testLog10() {
        return xAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            final double res = log.solveLog(x, 10, eps);
            assertEquals(Math.log(x)/Math.log(10), res, 1e-3);
            addRes(x, res);
        }));
    }


}
