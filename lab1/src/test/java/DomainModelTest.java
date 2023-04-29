import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.testing.alg.ClosedHashingProb;
import org.testing.domain.CrewMember;
import org.testing.domain.EngineType;
import org.testing.domain.SpaceShip;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class DomainModelTest {
    SpaceShip ship = new SpaceShip("Золотое сердце");
    static List<CrewMember> crew = List.of(new CrewMember[]{
            new CrewMember("aa", 1, CrewMember.Reason.PHYSICAL_PRINCIPLE, CrewMember.StatusType.PILOT),
            new CrewMember("gojo", 100, CrewMember.Reason.ACCIDENT, CrewMember.StatusType.CAPTAIN),
            new CrewMember("haat guy", 123, CrewMember.Reason.ORDER, CrewMember.StatusType.ENGINEER)
    });

    @ParameterizedTest
    @MethodSource("provideEngineArgs")
    void testDifEngineFlying(EngineType type, int fuelCap) {
        ship.setCrew(crew);
        ship.setEngine(type);
        ship.setFuelTank(fuelCap);
        assertEquals(type, ship.getEngineType());
        assertEquals(ship.getName() + " взлетел", ship.takeOff());
        assertEquals(ship.getName() + " летит в космосе", ship.fly());
        assertEquals(ship.getName() + " может только дрейфовать!", ship.fly());
        assertEquals(ship.getName() + " дрейфует в космосе", ship.drift());
        assertEquals(ship.getName() + " потерпевает крушение", ship.drift());
        assertEquals(SpaceShip.Condition.CRUSHED, ship.getCondition());
        assertTrue(ship.getCrew().isEmpty());
    }

    @ParameterizedTest
    @MethodSource("provideSuccessLandArg")
    void testSuccessLanding(EngineType type, int fuel) {
        ship.setCrew(crew);
        ship.setEngine(type);
        ship.setFuelTank(fuel);
        assertEquals(type, ship.getEngineType());
        assertEquals(ship.getName() + " взлетел", ship.takeOff());
        assertEquals(ship.getName() + " летит в космосе", ship.fly());
        assertEquals(ship.getName() + " успешно приземлился", ship.landOn());
    }

    @ParameterizedTest
    @MethodSource("provideEngineArgs")
    void testFailedLanding(EngineType type, int fuel) {
        ship.setCrew(crew);
        ship.setEngine(type);
        ship.setFuelTank(fuel);
        assertEquals(type, ship.getEngineType());
        assertEquals(ship.getName() + " взлетел", ship.takeOff());
        assertEquals(ship.getName() + " летит в космосе", ship.fly());
        assertEquals(ship.getName() + " недостаточно топлива для приземления!", ship.landOn());
    }

    @ParameterizedTest
    @MethodSource("provideAllCrewMembers")
    void testCrewMembers(CrewMember person) {
        ship.addCrewMember(person);
        assertTrue(ship.isCrewMember(person));
    }

    @Test
    void testCheckCrew() {
        CrewMember member = new CrewMember("aska", 13, CrewMember.Reason.WILL, CrewMember.StatusType.PILOT);
        ship.addCrewMember(member);
        assertTrue(ship.isCrewMember(member));
        ship.overBoard(member);
        assertFalse(ship.isCrewMember(member));
        ship.addCrewMember(member);
        ship.setCrew(crew);
        ship.overBoardAll();
        assertTrue(ship.getCrew().isEmpty());
        Exception exception = assertThrows(IllegalStateException.class, () -> ship.checkCrew());
        assertEquals("not enough crew members!", exception.getMessage());
    }

    @Test
    void testTakeOff() {
        assertEquals(SpaceShip.Condition.ON_LAND, ship.getCondition());
        ship.setCrew(crew);
        assertEquals(ship.getName() + " взлетел", ship.takeOff());
        assertEquals(SpaceShip.Condition.IN_SPACE, ship.getCondition());
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
                Arguments.of(EngineType.CHEMICAL, 10500),
                Arguments.of(EngineType.NUCLEAR, 8800)
        );
    }


}
