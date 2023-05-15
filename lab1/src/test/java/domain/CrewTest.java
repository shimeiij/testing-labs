package domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.testing.domain.CrewHolder;
import org.testing.domain.CrewMember;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CrewTest {
    CrewHolder crewHolder = new CrewHolder();
    List<CrewMember> crew = new ArrayList<>(Arrays.asList(new CrewMember("welt", 1, CrewMember.Reason.PHYSICAL_PRINCIPLE, CrewMember.StatusType.PILOT),
            new CrewMember("gojo", 100, CrewMember.Reason.ACCIDENT, CrewMember.StatusType.CAPTAIN),
            new CrewMember("haat guy", 123, CrewMember.Reason.ORDER, CrewMember.StatusType.ENGINEER)));

    @ParameterizedTest
    @MethodSource("provideAllCrewMembers")
    void testCrewMembers(final CrewMember person) {
        crewHolder.addCrewMember(person);
        assertTrue(crewHolder.isCrewMember(person));
    }

    @Test
    void testCheckCrew() {
        final CrewMember member = new CrewMember("aska", 13, CrewMember.Reason.WILL, CrewMember.StatusType.PILOT);
        crewHolder.addCrewMember(member);
        assertTrue(crewHolder.isCrewMember(member));
        crewHolder.overBoard(member);
        assertFalse(crewHolder.isCrewMember(member));
        crewHolder.addCrewMember(member);
        crewHolder.setCrew(crew);
        crewHolder.overBoardAll();
        assertTrue(crewHolder.isCrewEmpty());
        final Exception exception = assertThrows(IllegalStateException.class, () -> crewHolder.checkCrew());
        assertEquals("not enough crew members!", exception.getMessage());
    }

    static Stream<CrewMember> provideAllCrewMembers() {
        return Stream.of(
                new CrewMember("aa", 1, CrewMember.Reason.PHYSICAL_PRINCIPLE, CrewMember.StatusType.PILOT),
                new CrewMember("gojo", 100, CrewMember.Reason.ACCIDENT, CrewMember.StatusType.CAPTAIN),
                new CrewMember("haat guy", 123, CrewMember.Reason.ORDER, CrewMember.StatusType.ENGINEER),
                new CrewMember("fly", 3, CrewMember.Reason.WILL, CrewMember.StatusType.PASSANGER)
        );
    }
}
