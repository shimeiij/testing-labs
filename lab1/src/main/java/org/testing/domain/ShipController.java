package org.testing.domain;

public class ShipController {
    Condition condition = Condition.ON_LAND;
    Engine engine = new Engine(EngineType.PHOTONIC);
    private static final int K = 10;

    ShipController(final EngineType type) {
        engine = new Engine(type);
    }


    ShipController() {}

    public boolean breakDownInDrift() {
        final EngineType type = EngineType.valueOf(engine.getName());
        if (engine.fuel < type.getCharge()) {
            condition = Condition.CRUSHED;
            return true;
        }
        engine.fuel -= type.getCharge();
        return false;
    }

    public Condition breakDownInFly() {
        final EngineType type = EngineType.valueOf(engine.getName());
        if (engine.fuel <= (long) type.getCharge() * K) {
            if (engine.fuel < type.getCharge()) {
                this.condition = Condition.CRUSHED;
                return condition;
            }
            engine.fuel -= type.getCharge();
            return condition;
        }
        condition = Condition.IN_SPACE;
        engine.fuel -= (long) type.getCharge() * K;
        return condition;
    }

    public void spendFuel() {
        final EngineType type = EngineType.valueOf(engine.getName());
        engine.fuel -= type.getCharge();
    }

    public String checkFuelForLanding() {
        final EngineType type = EngineType.valueOf(engine.getName());
        if (engine.fuel < (long) type.getCharge() * K) {
            return " недостаточно топлива для приземления!";
        } else {
            engine.fuel -= (long) type.getCharge() * K;
            return " успешно приземлился";
        }
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(final Condition condition) {
        this.condition = condition;
    }

    public EngineType getEngineType() {
        return EngineType.valueOf(engine.name);
    }


    public void setEngine(final EngineType type) {
        this.engine = new Engine(type);
    }
}
