package org.testing.domain;

import java.util.ArrayList;
import java.util.List;

public class SpaceShip extends AbstactSpaceShip{
    String name;
    Engine engine = new Engine(EngineType.PHOTONIC);
    int fuelTank = MAX_FUEL;
    List<CrewMember> crew = new ArrayList<>();
    Condition condition = Condition.ON_LAND;

    public enum Condition {
        IN_SPACE,
        ON_LAND,
        CRUSHED
    }

    SpaceShip(String name, EngineType type) {
        this.name = name;
        engine = new Engine(type);
    }

    public SpaceShip(String name) {
        this.name = name;
    }

    public void setFuelTank(int fuelTank) {
        this.fuelTank = fuelTank;
    }

    public void addCrewMember(CrewMember member) {
        crew.add(member);
    }

    public boolean isCrewMember(CrewMember member) {
        return crew.contains(member);
    }

    public void overBoard(CrewMember member) {
        if (isCrewMember(member)) crew.remove(member);
    }

    public void overBoardAll() {
        crew.clear();
    }

    @Override
    public String drift() {
        EngineType type = EngineType.valueOf(engine.getName());
        switch (type) {
            case NUCLEAR:
                if (engine.fuel < 200) {
                    this.condition = Condition.CRUSHED;
                    overBoardAll();
                    return name + " потерпевает крушение";
                }
                engine.fuel -= 200;
                break;
            case CHEMICAL:
                if (engine.fuel < 250) {
                    this.condition = Condition.CRUSHED;
                    overBoardAll();
                    return name + " потерпевает крушение";
                }
                engine.fuel -= 250;
                break;
            case ELECTRIC:
                if (engine.fuel < 150) {
                    this.condition = Condition.CRUSHED;
                    overBoardAll();
                    return name + " потерпевает крушение";
                }
                engine.fuel -= 150;
                break;
            case PHOTONIC:
                if (engine.fuel < 100) {
                    this.condition = Condition.CRUSHED;
                    overBoardAll();
                    return name + " потерпевает крушение";
                }
                engine.fuel -= 100;
                break;
            default: throw new IllegalArgumentException("no such engine type");
        }
        return name + " дрейфует в космосе";
    }

    public boolean checkCrew() {
        boolean findCap = false;
        boolean findPilot = false;
        boolean findEng = false;
        for (CrewMember member: crew) {
            if (member.status.equals(CrewMember.StatusType.CAPTAIN)) findCap = true;
            if (member.status.equals(CrewMember.StatusType.PILOT)) findPilot = true;
            if (member.status.equals(CrewMember.StatusType.ENGINEER)) findEng = true;
        }
        if (!findCap || !findEng || !findPilot) throw new IllegalStateException("not enough crew members!");
        return true;
    }

    @Override
    public String takeOff() {
        if (engine == null) throw new IllegalStateException("The ship has no engine!");
        engine.refuelEngine(fuelTank);
        this.condition = Condition.IN_SPACE;
        if (!checkCrew()) return name + " не взлетел";
        switch(EngineType.valueOf(engine.name)) {
            case ELECTRIC:
                engine.fuel -= 1500;
                return name + " взлетел";
            case NUCLEAR:
                engine.fuel -= 2000;
                return name + " взлетел";
            case PHOTONIC:
                engine.fuel -= 1000;
                return name + " взлетел";
            case CHEMICAL:
                engine.fuel -= 2500;
                return name + " взлетел";
            default:
                System.out.println("fffffffffffffffff");
        }
        return name + " не взлетел";
    }

    @Override
    public String landOn() {
        this.condition = Condition.ON_LAND;
        EngineType type = EngineType.valueOf(engine.getName());
        switch (type) {
            case NUCLEAR:
                if (engine.fuel < 2000) {
                    return name + " недостаточно топлива для приземления!";
                } else {
                    engine.fuel -= 2000;
                    return name + " успешно приземлился";
                }
            case ELECTRIC:
                if (engine.fuel < 1500) {
                    return name + " недостаточно топлива для приземления!";
                } else {
                    engine.fuel -= 1500;
                    return name + " успешно приземлился";
                }
            case CHEMICAL:
                if (engine.fuel < 2500) {
                    return name + " недостаточно топлива для приземления!";
                } else {
                    engine.fuel -= 2500;
                    return name + " успешно приземлился";
                }
            case PHOTONIC:
                if (engine.fuel < 1000) {
                    return name + " недостаточно топлива для приземления!";
                } else {
                    engine.fuel -= 1000;
                    return name + " успешно приземлился";
                }
        }
        return name + " успешно приземлился";
    }

    @Override
    public String fly() {
        EngineType type = EngineType.valueOf(engine.getName());
        switch (type) {
            case NUCLEAR:
                if (engine.fuel <= 2000) {
                    if (engine.fuel < 200) {
                        this.condition = Condition.CRUSHED;
                        crew.clear();
                        return name + " потерпевает крушение";
                    }
                    engine.fuel -= 200;
                    return name + " может только дрейфовать!";
                }
                engine.fuel -= 2000;
                break;
            case CHEMICAL:
                if (engine.fuel <= 2500) {
                    if (engine.fuel < 250) {
                        this.condition = Condition.CRUSHED;
                        crew.clear();
                        return name + " потерпевает крушение";
                    }
                    engine.fuel -= 250;
                    return name + " может только дрейфовать!";
                }
                engine.fuel -= 2500;
                break;
            case ELECTRIC:
                if (engine.fuel <= 1500) {
                    if (engine.fuel < 150) {
                        this.condition = Condition.CRUSHED;
                        crew.clear();
                        return name + " потерпевает крушение";
                    }
                    engine.fuel -= 150;
                    return name + " может только дрейфовать!";
                }
                engine.fuel -= 1500;
                break;
            case PHOTONIC:
                if (engine.fuel <= 1000) {
                    if (engine.fuel < 100) {
                        this.condition = Condition.CRUSHED;
                        crew.clear();
                        return name + " потерпевает крушение";
                    }
                    engine.fuel -= 100;
                    return name + " может только дрейфовать!";
                }
                engine.fuel -= 1000;
                break;
            default: throw new IllegalArgumentException("no such engine type");
        }
        this.condition = Condition.IN_SPACE;
        return name + " летит в космосе";
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public List<CrewMember> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewMember> crew) {
        this.crew.addAll(crew);
    }

    public void setEngine(EngineType type) {
        this.engine = new Engine(type);
    }

    public EngineType getEngineType() {
        return EngineType.valueOf(engine.name);
    }

    public Engine getEngine() {
        return engine;
    }
}
