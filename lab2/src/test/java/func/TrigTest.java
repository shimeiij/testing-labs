package func;
import org.junit.jupiter.api.*;
import org.testing.math.trig.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TrigTest {
    double testDelta = 1e-6;
    double eps = 1e-10;
    CosFunc cos = new CosFunc();
    final SinFunc sinFunc = new SinFunc(cos);
    final CotFunc cotFunc = new CotFunc(sinFunc, cos);
    final SecFunc secFunc = new SecFunc(cos);

    List<Double> xAxis = DoubleStream.iterate(-5, k -> k+.1).limit(102).boxed().collect(Collectors.toList());
    List<Double> cotxAxis = DoubleStream.iterate(-5, k -> k+.105).limit(101).boxed().collect(Collectors.toList());

    void addRes(final double x, final double res, List<String> resList) {
        final StringBuilder builder = new StringBuilder();
        builder.append(x).append(',').append(res);
        resList.add(builder.toString());
    }

    @AfterAll
    void writeToCSV() {
        cos.writeToCsv(cos.getResList());
        sinFunc.writeToCsv(sinFunc.getResList());
        cotFunc.writeToCsv(cotFunc.getResList());
        secFunc.writeToCsv(secFunc.getResList());
    }

    @TestFactory
    Stream<DynamicTest> testCos() {
        return xAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            final double res = cos.cos(x, eps);
            assertEquals(Math.cos(x), res, testDelta);
            addRes(x, res, cos.getResList());
        }));
    }

    @TestFactory
    Stream<DynamicTest> testSin() {
        return xAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            final double res = sinFunc.solveFunc(x, eps);
            assertEquals(Math.sin(x), res, testDelta);
            addRes(x, res, sinFunc.getResList());
        }));
    }

    @TestFactory
    Stream<DynamicTest> testCot() {
        return cotxAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            final double res = cotFunc.solveFunc(x, eps);
            assertEquals(1.0/Math.tan(x), res, 1e-4);
            addRes(x, res, cotFunc.getResList());
        }));
    }

    @TestFactory
    Stream<DynamicTest> testSec() {
        return xAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            final double res = secFunc.solveFunc(x, eps);
            assertEquals(1.0/Math.cos(x), res, testDelta);
            addRes(x, res, secFunc.getResList());
        }));
    }


}
