package org.testing.domain;


public class SpaceShip extends AbstactSpaceShip{
    String name;
    int fuelTank = MAXFUEL;
    CrewHolder crewHolder;
    ShipController controller;


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
        return name + controller.checkFuelForLanding();
    }

    @Override
    public String fly() {
        String msg = null;
        final Condition cond = controller.breakDownInFly();
        if (cond == Condition.CRUSHED) {
            crewHolder.overBoardAll();
            msg = " потерпевает крушение";
        }
        if (cond == Condition.IN_SPACE) {
            msg = " летит в космосе";
        }
        if (cond == Condition.DRIFT) {
            msg = " может только дрейфовать!";
        }
        return name + msg;
    }


    @Override
    public String getName() {
        return this.name;
    }

    public CrewHolder getCrewHolder() {
        return crewHolder;
    }

    public ShipController getController() {
        return controller;
    }
}
