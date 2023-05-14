package org.testing.domain;


public class SpaceShip extends AbstactSpaceShip{
    String name;
    int fuelTank = MAXFUEL;
    CrewHolder crewHolder;
    ShipController controller;


    public SpaceShip(final String name, final EngineType type) {
        super();
        this.name = name;
        controller = new ShipController(type);
        crewHolder = new CrewHolder();
    }

    public SpaceShip(final String name) {
        super();
        this.name = name;
        controller = new ShipController();
        crewHolder = new CrewHolder();
    }

    public void setFuelTank(final int fuelTank) {
        this.fuelTank = fuelTank;
    }


    @Override
    public String drift() {
        String msgPart = " дрейфует в космосе";
        if (controller.breakDownInDrift()) {
            crewHolder.overBoardAll();
            msgPart = " потерпевает крушение";
        }
        return name + msgPart;
    }

    @Override
    public String takeOff() {
        if (controller.engine == null) {
            throw new IllegalStateException("The ship has no engine!");
        }
        controller.engine.refuelEngine(fuelTank);
        controller.setCondition(Condition.IN_SPACE);
        if (!crewHolder.checkCrew()) {
            return name + " не взлетел";
        }
        controller.spendFuel();
        return name + " взлетел";
    }

    @Override
    public String landOn() {
        controller.setCondition(Condition.ON_LAND);
        return controller.checkFuelForLanding();
    }

    @Override
    public String fly() {
        String msg = " может только дрейофвать";
        final Condition cond = controller.breakDownInFly();
        if (cond == Condition.CRUSHED) {
            crewHolder.overBoardAll();
            msg = " потерпевает крушение";
        }
        if (cond == Condition.IN_SPACE) {
            msg = " летит в космосе ";
        }
        return name + msg;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(final String name) {
        this.name = name;
    }


    public CrewHolder getCrewHolder() {
        return crewHolder;
    }

    public ShipController getController() {
        return controller;
    }
}
