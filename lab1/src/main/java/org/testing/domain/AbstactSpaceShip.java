package org.testing.domain;

public abstract class AbstactSpaceShip implements Entity{
    static int MAX_FUEL = Integer.MAX_VALUE;

    abstract String drift();
    abstract boolean takeOff();
    abstract String landOn();
    abstract String fly();

}
