package org.testing.domain;

public class Engine implements Entity{
    String name;
    boolean isRefueled = false;
    int fuel = 0;

    Engine(EngineType type) {
        this.name = type.name();
    }

    public void refuelEngine(int fuel) {
        this.fuel = fuel;
        isRefueled = true;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public boolean isRefueled() {
        return isRefueled;
    }

    public int getFuel() {
        return fuel;
    }
}
