package org.testing.math;

import org.testing.math.log.LogBase;
import org.testing.math.log.LogBaseN;
import org.testing.math.trig.*;

import java.util.ArrayList;
import java.util.List;

public class SystemSolver implements CSVInterface{
    LogBaseN ln;
    CosFunc cos;
    SinFunc sinFunc;
    CotFunc cotFunc;
    SecFunc secFunc;
    LogBase logBase;
    List<String> systemRes = new ArrayList<>();


    public SystemSolver(final LogBaseN ln,
                        final CosFunc cos)  {
        super();
        this.ln = ln;
        this.cos = cos;
    }

    public Double solveSystem(final double x,
                              final double eps)
    {
        if (x <= 0) {
            sinFunc = new SinFunc(cos);
            cotFunc = new CotFunc(sinFunc, cos);
            secFunc = new SecFunc(cos);

            final double cotRes = cotFunc.solveFunc(x, eps);
            if (cotRes == Double.POSITIVE_INFINITY || cotRes == Double.NEGATIVE_INFINITY) {
                return cotRes;
            }
            final double cosRes = cos.cos(x, eps),
                    sinRes = sinFunc.solveFunc(x, eps),
                    secRes = secFunc.solveFunc(x, eps);
            return Math.pow(cosRes*sinRes*cotRes, 2)*cotRes - secRes/cotRes;
        } else {
            logBase = new LogBase(ln);
            if (x == Double.MAX_VALUE || x == Double.POSITIVE_INFINITY){
                return 0.0;
            }
            final double log3 = logBase.solveLog(x, 3, eps),
                    log2 = logBase.solveLog(x, 2, eps),
                    lg = logBase.solveLog(x, 10, eps),
                    log5 = logBase.solveLog(x, 5, eps),
                    loge = ln.ln(x, eps);
            final double num = ((1.0-log2)*(loge-log3))*log5;
            final double den = Math.pow(log3, 2)*lg*log3;
            if (num == 0.0 && den == 0.0){
                return Double.POSITIVE_INFINITY;
            }
            else {
                return num/den;
            }
        }
    }

    public void setSinFunc(final SinFunc sinFunc) {
        this.sinFunc = sinFunc;
    }

    public void setCotFunc(final CotFunc cotFunc) {
        this.cotFunc = cotFunc;
    }

    public void setSecFunc(final SecFunc secFunc) {
        this.secFunc = secFunc;
    }

    public void setLogBase(final LogBase logBase) {
        this.logBase = logBase;
    }

    public LogBaseN getLn() {
        return ln;
    }

    public CosFunc getCos() {
        return cos;
    }

    public SinFunc getSinFunc() {
        return sinFunc;
    }

    public CotFunc getCotFunc() {
        return cotFunc;
    }

    public SecFunc getSecFunc() {
        return secFunc;
    }

    public LogBase getLogBase() {
        return logBase;
    }

    public void addRes(final double x, final double res) {
        final StringBuilder builder = new StringBuilder();
        builder.append(x).append(',').append(res);
        systemRes.add(builder.toString());
    }

    public List<String> getSystemRes() {
        return systemRes;
    }

}
