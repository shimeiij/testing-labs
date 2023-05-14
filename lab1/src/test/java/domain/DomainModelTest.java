package domain;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.testing.domain.Condition;
import org.testing.domain.CrewMember;
import org.testing.domain.EngineType;
import org.testing.domain.SpaceShip;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DomainModelTest {
    SpaceShip ship = new SpaceShip("Золотое сердце");
    static final List<CrewMember> CREW = List.of(new CrewMember[]{
            new CrewMember("aa", 1, CrewMember.Reason.PHYSICAL_PRINCIPLE, CrewMember.StatusType.PILOT),
            new CrewMember("gojo", 100, CrewMember.Reason.ACCIDENT, CrewMember.StatusType.CAPTAIN),
            new CrewMember("haat guy", 123, CrewMember.Reason.ORDER, CrewMember.StatusType.ENGINEER)
    });

    @ParameterizedTest
    @MethodSource("provideEngineArgs")
    void testDifEngineFlying(final EngineType type, final int fuelCap) {
        ship.getCrewHolder().setCrew(CREW);
        ship.getController().setEngine(type);
        ship.setFuelTank(fuelCap);
        assertEquals(type, ship.getController().getEngineType());
        assertEquals(ship.getName() + " взлетел", ship.takeOff());
        assertEquals(ship.getName() + " летит в космосе", ship.fly());
        assertEquals(ship.getName() + " может только дрейфовать!", ship.fly());
        assertEquals(ship.getName() + " дрейфует в космосе", ship.drift());
        assertEquals(ship.getName() + " потерпевает крушение", ship.drift());
        assertEquals(Condition.CRUSHED, ship.getController().getCondition());
        assertTrue(ship.getCrewHolder().isCrewEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideSuccessLandArg")
    void testSuccessLanding(final EngineType type,final int fuel) {
        ship.getCrewHolder().setCrew(CREW);
        ship.getController().setEngine(type);
        ship.setFuelTank(fuel);
        assertEquals(type, ship.getController().getEngineType());
        assertEquals(ship.getName() + " взлетел", ship.takeOff());
        assertEquals(ship.getName() + " летит в космосе", ship.fly());
        assertEquals(ship.getName() + " успешно приземлился", ship.landOn());
    }

    @ParameterizedTest
    @MethodSource("provideEngineArgs")
    void testFailedLanding(final EngineType type, final int fuel) {
        ship.getCrewHolder().setCrew(CREW);
        ship.getController().setEngine(type);
        ship.setFuelTank(fuel);
        assertEquals(type, ship.getController().getEngineType());
        assertEquals(ship.getName() + " взлетел", ship.takeOff());
        assertEquals(ship.getName() + " летит в космосе", ship.fly());
        assertEquals(ship.getName() + " недостаточно топлива для приземления!", ship.landOn());
    }

    @ParameterizedTest
    @MethodSource("provideAllCrewMembers")
    void testCrewMembers(final CrewMember person) {
        ship.getCrewHolder().addCrewMember(person);
        assertTrue(ship.getCrewHolder().isCrewMember(person));
    }

    @Test
    void testCheckCrew() {
        final CrewMember member = new CrewMember("aska", 13, CrewMember.Reason.WILL, CrewMember.StatusType.PILOT);
        ship.getCrewHolder().addCrewMember(member);
        assertTrue(ship.getCrewHolder().isCrewMember(member));
        ship.getCrewHolder().overBoard(member);
        assertFalse(ship.getCrewHolder().isCrewMember(member));
        ship.getCrewHolder().addCrewMember(member);
        ship.getCrewHolder().setCrew(CREW);
        ship.getCrewHolder().overBoardAll();
        assertTrue(ship.getCrewHolder().isCrewEmpty());
        final Exception exception = assertThrows(IllegalStateException.class, () -> ship.getCrewHolder().checkCrew());
        assertEquals("not enough crew members!", exception.getMessage());
    }

    @Test
    void testTakeOff() {
        assertEquals(Condition.ON_LAND, ship.getController().getCondition());
        ship.getCrewHolder().setCrew(CREW);
        assertEquals(ship.getName() + " взлетел", ship.takeOff());
        assertEquals(Condition.IN_SPACE, ship.getController().getCondition());
    }

    static Stream<CrewMember> provideAllCrewMembers() {
        return Stream.of(
                new CrewMember("aa", 1, CrewMember.Reason.PHYSICAL_PRINCIPLE, CrewMember.StatusType.PILOT),
                new CrewMember("gojo", 100, CrewMember.Reason.ACCIDENT, CrewMember.StatusType.CAPTAIN),
                new CrewMember("haat guy", 123, CrewMember.Reason.ORDER, CrewMember.StatusType.ENGINEER),
                new CrewMember("fly", 3, CrewMember.Reason.WILL, CrewMember.StatusType.PASSANGER)
        );
    }

    static Stream<Arguments> provideEngineArgs() {
        return Stream.of(
                Arguments.of(EngineType.ELECTRIC, 3300),
                Arguments.of(EngineType.PHOTONIC, 2200),
                Arguments.of(EngineType.CHEMICAL, 5500),
                Arguments.of(EngineType.NUCLEAR, 4400)
        );
    }

    static Stream<Arguments> provideSuccessLandArg() {
        return Stream.of(
                Arguments.of(EngineType.ELECTRIC, 6300),
                Arguments.of(EngineType.PHOTONIC, 4200),
                Arguments.of(EngineType.CHEMICAL, 10_500),
                Arguments.of(EngineType.NUCLEAR, 8800)
        );
    }


}
