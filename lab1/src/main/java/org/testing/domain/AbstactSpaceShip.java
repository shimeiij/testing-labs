package org.testing.domain;

public abstract class AbstactSpaceShip implements Entity{
    static int MAX_FUEL = Integer.MAX_VALUE;

    abstract String drift();
    abstract String takeOff();
    abstract String landOn();
    abstract String fly();

}
