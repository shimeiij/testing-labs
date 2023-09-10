package func;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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



    @TestFactory
    Stream<DynamicTest> testCos() {
        return xAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            final double res = cos.cos(x, eps);
            assertEquals(Math.cos(x), res, testDelta);
        }));
    }

    @TestFactory
    Stream<DynamicTest> testSin() {
        return xAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            final double res = sinFunc.solveFunc(x, eps);
            assertEquals(Math.sin(x), res, testDelta);
        }));
    }

    @TestFactory
    Stream<DynamicTest> testCot() {
        return cotxAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            final double res = cotFunc.solveFunc(x, eps);
            assertEquals(1.0/Math.tan(x), res, 1e-4);
        }));
    }

    @TestFactory
    Stream<DynamicTest> testSec() {
        return xAxis.stream().map(x -> DynamicTest.dynamicTest("argument: " + x, () -> {
            assertEquals(1.0/Math.cos(x), secFunc.solveFunc(x, eps), testDelta);
        }));
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

    @AfterAll
    void writeToCSV() {
        cos.writeToCsv(cos.getResList());
        sinFunc.writeToCsv(sinFunc.getResList());
        cotFunc.writeToCsv(cotFunc.getResList());
        secFunc.writeToCsv(secFunc.getResList());
    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
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

        @AfterAll
        void writeToCSV() {
            cos.writeToCsv(cos.getResList());
            sinFunc.writeToCsv(sinFunc.getResList());
            cotFunc.writeToCsv(cotFunc.getResList());
            secFunc.writeToCsv(secFunc.getResList());
        }

    }

}
