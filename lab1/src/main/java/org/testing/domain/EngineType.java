package org.testing.domain;

public enum EngineType {
    CHEMICAL(250),
    NUCLEAR(200),
    ELECTRIC(150),
    PHOTONIC(100);

    private int charge;

    EngineType(final int charge) {
        this.charge = charge;
    }

    EngineType() {}


    public int getCharge() {
        return charge;
    }

    public EngineType getEngineTypeByValue(final int val) {
        for (final EngineType type: values()) {
            if (type.getCharge() == val) {
                return type;
            }
        }
        return null;
    }


}
