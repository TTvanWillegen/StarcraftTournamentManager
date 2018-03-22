package objects;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2017-09-26.
 */
public class MapTest {
    @Test
    public void testEqualsTrue() {
        Map map1 = new Map("MapA", 1);
        Map map2 = new Map("MapA", 1);
        assertTrue(map1.equals(map2));
    }
}