package func;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.testing.math.ResAdder;
import org.testing.math.trig.CosFunc;
import org.testing.math.trig.CotFunc;
import org.testing.math.trig.SecFunc;
import org.testing.math.trig.SinFunc;

import java.util.stream.DoubleStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class TrigTest {
    double testDelta = 1e-6;
    double eps = 1e-10;

    final ResAdder resAdder = new ResAdder();
    CosFunc cos = new CosFunc();
    final SinFunc sinFunc = new SinFunc(cos);
    final CotFunc cotFunc = new CotFunc(sinFunc, cos);
    final SecFunc secFunc = new SecFunc(cos);


    @AfterAll
    void writeToCSV() {
        cos.writeToCsv(cos.getResList());
        sinFunc.writeToCsv(sinFunc.getResList());
        cotFunc.writeToCsv(cotFunc.getResList());
        secFunc.writeToCsv(secFunc.getResList());
    }

    @ParameterizedTest
    @MethodSource("provideArgs")
    void testCos(final Double x) {
        final double res = cos.cos(x, eps);
        assertEquals(Math.cos(x), res, testDelta);
        resAdder.addRes(x, res, cos.getResList());
    }

    @ParameterizedTest
    @MethodSource("provideArgs")
    void testSin(final Double x) {
        final double res = sinFunc.solveFunc(x, eps);
        assertEquals(Math.sin(x), res, testDelta);
        resAdder.addRes(x, res, sinFunc.getResList());
    }

    @ParameterizedTest
    @MethodSource("provideCotArgs")
    void testCot(final Double x) {
        final double res = cotFunc.solveFunc(x, eps);
        assertEquals(1.0/Math.tan(x), res, 1e-4);
        resAdder.addRes(x, res, cotFunc.getResList());
    }

    @ParameterizedTest
    @MethodSource("provideArgs")
    void testSec(final Double x) {
        final double res = secFunc.solveFunc(x, eps);
        assertEquals(1.0/Math.cos(x), res, testDelta);
        resAdder.addRes(x, res, secFunc.getResList());
    }


    static DoubleStream provideArgs() {
        return DoubleStream.iterate(-5, k -> k+.1).limit(102);
    }

    static DoubleStream provideCotArgs() {
        return DoubleStream.iterate(-5, k -> k+.105).limit(101);
    }

}
