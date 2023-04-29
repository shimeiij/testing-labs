import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import org.testing.alg.ClosedHashingProb;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("test hash")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AlgTest {
    static String[] keys = {
            "games", "blogs", "cartoons", "music", "books", "health", "dining",
            "sunday", "store", "classified", "mobile", "update", "services"
    };

    ClosedHashingProb<String> set = new ClosedHashingProb<>(30);

//    @BeforeEach
//    void fillSet() {
//        for (String str: keys) {
//            assertTrue(set.insert(str));
//        }
//    }

    @ParameterizedTest
    @EnumSource(ClosedHashingProb.Probe.class)
    void testElement(ClosedHashingProb.Probe probe) {
        assertNotNull(probe);
        set.setProb(probe);
        String last_str = null;
        for (String str: keys) {
            assertTrue(set.insert(str));
            assertTrue(set.contains(str));
            last_str = str;
        }
        assertTrue(set.remove(last_str));
        assertFalse(set.remove(last_str));
        assertFalse(set.contains(last_str));

        String finalLast_str = last_str;
        assertEquals("no such el :c", assertThrows(NoSuchElementException.class, () -> set.find(finalLast_str)).getMessage());
        assertEquals("no such el :c", assertThrows(NoSuchElementException.class, () -> set.find("foo")).getMessage());
    }




    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class TestLinearProbe {
        ClosedHashingProb<String> set = new ClosedHashingProb<>(keys.length, ClosedHashingProb.Probe.LINEAR);

        @BeforeAll
        void fillSet() {
            for (String str: keys) {
                set.insert(str);
            }
        }

        @ParameterizedTest
        @CsvSource({
                "games, 12", "blogs, 0", "cartoons, 9", "music, 7", "books, 4", "health, 1", "dining, 5",
                "sunday, 11", "store, 6", "classified, 10", "mobile, 2", "update, 8", "services, 3"
        })
        void testFind(String str, int ind) {
            assertTrue(set.contains(str));
            assertEquals(ind, set.find(str));
        }

        @Test
        void testLinearOverflow() {
            Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> set.insert("aaa"));
            assertEquals("all array is full", exception.getMessage());
        }

    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class TestDoubleProbe {
        ClosedHashingProb<String> set = new ClosedHashingProb<>(keys.length, ClosedHashingProb.Probe.DOUBLE);

        @BeforeAll
        void fillSet() {
            for (String str: keys) {
                set.insert(str);
            }
        }

        @ParameterizedTest
        @CsvSource({
                "games, 12", "blogs, 9", "cartoons, 3", "music, 7", "books, 4", "health, 0", "dining, 2",
                "sunday, 11", "store, 10", "classified, 5", "mobile, 1", "update, 8", "services, 6"
        })
        void testFind(String str, int ind) {
            assertTrue(set.contains(str));
            assertEquals(ind, set.find(str));
        }

        @Test
        void testDoubleOverflow() {
            Exception exception = assertThrows(ArrayIndexOutOfBoundsException.class, () -> set.insert("aaa"));
            assertEquals("all array is full", exception.getMessage());
        }

    }

    @Nested
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    class TestQuadProbe {
        ClosedHashingProb<String> set = new ClosedHashingProb<>(30, ClosedHashingProb.Probe.QUAD);
        static int[] expected_ind = {22, 23, 6, 7, 25, 23, 24, 8, 9, 28, 27, 0, 30};



        @BeforeAll
        void fillSet() {
            for (String str: keys) {
                set.insert(str);
            }
        }


        @ParameterizedTest
        @CsvSource({
                "games, 22", "blogs, 23", "cartoons, 6", "music, 7", "books, 26", "health, 24", "dining, 25",
                "sunday, 8", "store, 9", "classified, 28", "mobile, 27", "update, 0", "services, 29"
        })
        void testFind(String arg, int i) {
            assertTrue(set.contains(arg));
            assertEquals(i, set.find(arg));
        }


    }


    @Nested
    class TestSameKeys {
        ClosedHashingProb<String> set = new ClosedHashingProb<>(30);

        @ParameterizedTest
        @EnumSource(ClosedHashingProb.Probe.class)
        void testSameKeys(ClosedHashingProb.Probe probe) {
            String str = "a";
            set.setProb(probe);
            assertTrue(set.insert(str));
            assertFalse(set.insert(str));
        }

    }


}
