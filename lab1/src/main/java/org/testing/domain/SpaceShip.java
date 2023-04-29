package org.testing.domain;

import java.util.ArrayList;
import java.util.List;

public class SpaceShip extends AbstactSpaceShip{
    String name;
    Engine engine;
    List<CrewMember> crew = new ArrayList<>();

    SpaceShip(String name, EngineType type) {
        this.name = name;
        engine = new Engine(type);
    }

    void addCrewMember(CrewMember member) {
        crew.add(member);
    }

    void overBoard(CrewMember member) {
        crew.remove(member);
    }

    @Override
    String drift() {
        engine.fuel -= 10;
        if (engine.fuel <= 0) {
            return name + "потерпевает крушение";
        }
        return name + "дрейфует в космосе";
    }

    @Override
    boolean takeOff() {
        engine.refuelEngine(MAX_FUEL);
        boolean findCap = false;
        boolean findPilot = false;
        boolean findEng = false;
        for (CrewMember member: crew) {
            if (member.status.equals(CrewMember.StatusType.CAPTAIN)) findCap = true;
            if (member.status.equals(CrewMember.StatusType.PILOT)) findPilot = true;
            if (member.status.equals(CrewMember.StatusType.ENGINEER)) findEng = true;
        }
        return findCap && findEng && findPilot;
    }

    @Override
    String landOn() {
        return name + "приземлился";
    }

    @Override
    String fly() {
        if (engine.fuel <= 1000) {
            return name + "может только дрейофовать!";
        }
        engine.fuel -= 1000;
        return name + "летит в космосе";
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }
}
