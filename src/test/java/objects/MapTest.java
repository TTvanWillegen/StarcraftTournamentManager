package objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2017-09-26.
 */
class MapTest {
    @Test
    void testEqualsTrue() {
        Map map1 = new Map("MapA", 1);
        Map map2 = new Map("MapA", 1);
        assertTrue(map1.equals(map2));
    }
}