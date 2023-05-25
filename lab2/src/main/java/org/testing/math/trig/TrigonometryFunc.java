package org.testing.math.trig;

public abstract class TrigonometryFunc {
    BaseTrigonometric cos;

    public abstract Double solveFunc(Long x, Long acc);
    public abstract void setBaseTrigFunc(BaseTrigonometric cos);
    public abstract BaseTrigonometric getBaseTrigonometricFunc();

}
