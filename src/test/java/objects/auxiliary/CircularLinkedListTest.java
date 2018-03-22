package objects.auxiliary;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Test;

/**
 * @author Toby T. van Willegen
 * @version 1.1, 2018-03-22.
 */
public class CircularLinkedListTest {
	@Test
	public void testEqualsOnlyHead() {
		CircularLinkedList<String> stringCLL1 = new CircularLinkedList<>();
		CircularLinkedList<String> stringCLL2 = new CircularLinkedList<>();

		String string = "A";
		stringCLL1.add(string);
		stringCLL2.add(string);
		assertEquals(stringCLL1, stringCLL2);
	}
	@Test
	public void testEqualsTwoItems() {
		CircularLinkedList<String> stringCLL1 = new CircularLinkedList<>();
		CircularLinkedList<String> stringCLL2 = new CircularLinkedList<>();

		String stringA = "A";
		String stringB = "B";
		stringCLL1.add(stringA);
		stringCLL2.add(stringA);
		stringCLL1.add(stringB);
		stringCLL2.add(stringB);
		assertEquals(stringCLL1, stringCLL2);
	}
	@Test
	public void testEqualsManyItems() {
		CircularLinkedList<String> stringCLL1 = new CircularLinkedList<>();
		CircularLinkedList<String> stringCLL2 = new CircularLinkedList<>();

		String stringA = "A";
		String stringB = "B";
		String stringC = "C";
		String stringD = "D";
		String stringE = "E";
		String stringF = "F";
		String stringG = "G";
		String stringH = "H";
		stringCLL1.add(stringA);
		stringCLL2.add(stringA);
		stringCLL1.add(stringB);
		stringCLL2.add(stringB);
		stringCLL1.add(stringC);
		stringCLL2.add(stringC);
		stringCLL1.add(stringD);
		stringCLL2.add(stringD);
		stringCLL1.add(stringE);
		stringCLL2.add(stringE);
		stringCLL1.add(stringF);
		stringCLL2.add(stringF);
		stringCLL1.add(stringG);
		stringCLL2.add(stringG);
		stringCLL1.add(stringH);
		stringCLL2.add(stringH);
		assertEquals(stringCLL1, stringCLL2);
	}
	@Test
	public void testNotEqualsManyItemsDiffSize() {
		CircularLinkedList<String> stringCLL1 = new CircularLinkedList<>();
		CircularLinkedList<String> stringCLL2 = new CircularLinkedList<>();

		String stringA = "A";
		String stringB = "B";
		String stringC = "C";
		String stringD = "D";
		String stringE = "E";
		String stringF = "F";
		String stringG = "G";
		String stringH = "H";
		stringCLL1.add(stringA);
		stringCLL2.add(stringA);
		stringCLL1.add(stringB);
		stringCLL2.add(stringB);
		stringCLL1.add(stringC);
		stringCLL2.add(stringC);
		stringCLL1.add(stringD);
		stringCLL2.add(stringD);
		stringCLL1.add(stringE);
		stringCLL2.add(stringE);
		stringCLL1.add(stringF);
		stringCLL2.add(stringF);
		stringCLL1.add(stringG);
		stringCLL2.add(stringG);
		stringCLL1.add(stringH);
		assertNotEquals(stringCLL1, stringCLL2);
	}
	@Test
	public void testNotEqualsManyItemsDiffElem() {
		CircularLinkedList<String> stringCLL1 = new CircularLinkedList<>();
		CircularLinkedList<String> stringCLL2 = new CircularLinkedList<>();

		String stringA = "A";
		String stringB = "B";
		String stringC = "C";
		String stringD = "D";
		String stringE = "E";
		String stringF = "F";
		String stringG = "G";
		String stringH = "H";
		String stringZ = "Z";
		stringCLL1.add(stringA);
		stringCLL2.add(stringA);
		stringCLL1.add(stringB);
		stringCLL2.add(stringB);
		stringCLL1.add(stringC);
		stringCLL2.add(stringC);
		stringCLL1.add(stringD);
		stringCLL2.add(stringD);
		stringCLL1.add(stringE);
		stringCLL2.add(stringE);
		stringCLL1.add(stringF);
		stringCLL2.add(stringF);
		stringCLL1.add(stringG);
		stringCLL2.add(stringG);
		stringCLL1.add(stringH);
		stringCLL2.add(stringZ);
		assertNotEquals(stringCLL1, stringCLL2);
	}
	@Test
	public void testEqualsEmpty() {
		CircularLinkedList<String> stringCLL1 = new CircularLinkedList<>();
		CircularLinkedList<String> stringCLL2 = new CircularLinkedList<>();

		assertEquals(stringCLL1, stringCLL2);
	}
	@Test
	public void testNotEqualsDifferentSize() {
		CircularLinkedList<String> stringCLL1 = new CircularLinkedList<>();
		CircularLinkedList<String> stringCLL2 = new CircularLinkedList<>();

		String stringA = "A";
		String stringB = "B";
		stringCLL1.add(stringA);
		stringCLL2.add(stringA);
		stringCLL1.add(stringB);
		assertNotEquals(stringCLL1, stringCLL2);
	}
	@Test
	public void testNotEqualsDifferentSize1Empty() {
		CircularLinkedList<String> stringCLL1 = new CircularLinkedList<>();
		CircularLinkedList<String> stringCLL2 = new CircularLinkedList<>();

		String stringA = "A";
		stringCLL2.add(stringA);
		assertNotEquals(stringCLL1, stringCLL2);
	}
	@Test
	public void testNotEqualsDifferentSize2Empty() {
		CircularLinkedList<String> stringCLL1 = new CircularLinkedList<>();
		CircularLinkedList<String> stringCLL2 = new CircularLinkedList<>();

		String stringB = "B";
		stringCLL1.add(stringB);
		assertNotEquals(stringCLL1, stringCLL2);
	}
}
