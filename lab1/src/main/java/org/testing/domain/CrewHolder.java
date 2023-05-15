package org.testing.domain;

import java.util.ArrayList;
import java.util.List;

public class CrewHolder {
    List<CrewMember> crew = new ArrayList<>();

    public void addCrewMember(final CrewMember member) {
        crew.add(member);
    }

    public void setCrew(final List<CrewMember> crew) {
        this.crew = crew;
    }

    public boolean isCrewMember(final CrewMember member) {
        return crew.contains(member);
    }

    public boolean checkCrew() {
        boolean findCap = false;
        boolean findPilot = false;
        boolean findEng = false;
        for (final CrewMember member: crew) {
            if (member.status.equals(CrewMember.StatusType.CAPTAIN)) {
                findCap = true;
            }
            if (member.status.equals(CrewMember.StatusType.PILOT)){
                findPilot = true;
            }
            if (member.status.equals(CrewMember.StatusType.ENGINEER)){
                findEng = true;
            }
        }
        if (!findCap || !findEng || !findPilot) {
            throw new IllegalStateException("not enough crew members!");
        }
        return true;
    }

    public void overBoard(final CrewMember member) {
        if (isCrewMember(member)) {
            crew.remove(member);
        }
    }

    public void overBoardAll() {
        crew.clear();
    }

    public boolean isCrewEmpty() {
        return crew.isEmpty();
    }

}
