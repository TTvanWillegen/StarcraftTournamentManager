package objects.auxiliary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * Tests for the {@link CircularLinkedList} class.
 *
 * @author Toby T. van Willegen
 * @version 1.1, 2018-03-22.
 */
public class CircularLinkedListTest {
    @Test
    public void testEqualsOnlyHead() {
        CircularLinkedList<String> stringCll1 = new CircularLinkedList<>();
        CircularLinkedList<String> stringCll2 = new CircularLinkedList<>();

        String string = "A";
        stringCll1.add(string);
        stringCll2.add(string);
        assertEquals(stringCll1, stringCll2);
    }

    @Test
    public void testEqualsTwoItems() {
        CircularLinkedList<String> stringCll1 = new CircularLinkedList<>();
        CircularLinkedList<String> stringCll2 = new CircularLinkedList<>();

        String stringA = "A";
        String stringB = "B";
        stringCll1.add(stringA);
        stringCll2.add(stringA);
        stringCll1.add(stringB);
        stringCll2.add(stringB);
        assertEquals(stringCll1, stringCll2);
    }

    @Test
    public void testEqualsManyItems() {
        CircularLinkedList<String> stringCll1 = new CircularLinkedList<>();
        CircularLinkedList<String> stringCll2 = new CircularLinkedList<>();

        String stringA = "A";
        stringCll1.add(stringA);
        stringCll2.add(stringA);
        String stringB = "B";
        stringCll1.add(stringB);
        stringCll2.add(stringB);
        String stringC = "C";
        stringCll1.add(stringC);
        stringCll2.add(stringC);
        String stringD = "D";
        stringCll1.add(stringD);
        stringCll2.add(stringD);
        String stringE = "E";
        stringCll1.add(stringE);
        stringCll2.add(stringE);
        String stringF = "F";
        stringCll1.add(stringF);
        stringCll2.add(stringF);
        String stringG = "G";
        stringCll1.add(stringG);
        stringCll2.add(stringG);
        String stringH = "H";
        stringCll1.add(stringH);
        stringCll2.add(stringH);
        assertEquals(stringCll1, stringCll2);
    }

    @Test
    public void testNotEqualsManyItemsDiffSize() {
        CircularLinkedList<String> stringCll1 = new CircularLinkedList<>();
        CircularLinkedList<String> stringCll2 = new CircularLinkedList<>();

        String stringA = "A";
        stringCll1.add(stringA);
        stringCll2.add(stringA);
        String stringB = "B";
        stringCll1.add(stringB);
        stringCll2.add(stringB);
        String stringC = "C";
        stringCll1.add(stringC);
        stringCll2.add(stringC);
        String stringD = "D";
        stringCll1.add(stringD);
        stringCll2.add(stringD);
        String stringE = "E";
        stringCll1.add(stringE);
        stringCll2.add(stringE);
        String stringF = "F";
        stringCll1.add(stringF);
        stringCll2.add(stringF);
        String stringG = "G";
        stringCll1.add(stringG);
        stringCll2.add(stringG);
        String stringH = "H";
        stringCll1.add(stringH);
        assertNotEquals(stringCll1, stringCll2);
    }

    @Test
    public void testNotEqualsManyItemsDiffElem() {
        CircularLinkedList<String> stringCll1 = new CircularLinkedList<>();
        CircularLinkedList<String> stringCll2 = new CircularLinkedList<>();

        String stringA = "A";
        stringCll1.add(stringA);
        stringCll2.add(stringA);
        String stringB = "B";
        stringCll1.add(stringB);
        stringCll2.add(stringB);
        String stringC = "C";
        stringCll1.add(stringC);
        stringCll2.add(stringC);
        String stringD = "D";
        stringCll1.add(stringD);
        stringCll2.add(stringD);
        String stringE = "E";
        stringCll1.add(stringE);
        stringCll2.add(stringE);
        String stringF = "F";
        stringCll1.add(stringF);
        stringCll2.add(stringF);
        String stringG = "G";
        stringCll1.add(stringG);
        stringCll2.add(stringG);
        String stringH = "H";
        stringCll1.add(stringH);
        String stringZ = "Z";
        stringCll2.add(stringZ);
        assertNotEquals(stringCll1, stringCll2);
    }

    @Test
    public void testEqualsEmpty() {
        CircularLinkedList<String> stringCll1 = new CircularLinkedList<>();
        CircularLinkedList<String> stringCll2 = new CircularLinkedList<>();

        assertEquals(stringCll1, stringCll2);
    }

    @Test
    public void testNotEqualsDifferentSize() {
        CircularLinkedList<String> stringCll1 = new CircularLinkedList<>();
        CircularLinkedList<String> stringCll2 = new CircularLinkedList<>();

        String stringA = "A";
        String stringB = "B";
        stringCll1.add(stringA);
        stringCll2.add(stringA);
        stringCll1.add(stringB);
        assertNotEquals(stringCll1, stringCll2);
    }

    @Test
    public void testNotEqualsDifferentSize1Empty() {
        CircularLinkedList<String> stringCll1 = new CircularLinkedList<>();
        CircularLinkedList<String> stringCll2 = new CircularLinkedList<>();

        String stringA = "A";
        stringCll2.add(stringA);
        assertNotEquals(stringCll1, stringCll2);
    }

    @Test
    public void testNotEqualsDifferentSize2Empty() {
        CircularLinkedList<String> stringCll1 = new CircularLinkedList<>();
        CircularLinkedList<String> stringCll2 = new CircularLinkedList<>();

        String stringB = "B";
        stringCll1.add(stringB);
        assertNotEquals(stringCll1, stringCll2);
    }
}
