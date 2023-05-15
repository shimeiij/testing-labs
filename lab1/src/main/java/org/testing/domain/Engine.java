package org.testing.domain;

public class Engine implements Entity{
    String name;
    boolean isRefueled;
    long fuel;

    Engine(final EngineType type) {
        this.name = type.name();
    }

    public void refuelEngine(final int fuel) {
        this.fuel = fuel;
        isRefueled = true;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
