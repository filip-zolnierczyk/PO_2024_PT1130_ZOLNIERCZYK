package agh.ics.oop.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Vector2dTest {
    @Test
    public void testEquals() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(1, 2);
        assertEquals(v1, v2);
    }
    @Test
    public void testToString() {
        Vector2d v = new Vector2d(1, 2);
        assertEquals("(1,2)", v.toString());
    }

    @Test
    public void testPrecedes() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(2, 3);
        Vector2d v3 = new Vector2d(0, 1);
        assertTrue(v1.precedes(v2));
        assertFalse(v2.precedes(v1));
        assertTrue(v3.precedes(v1));
    }

    @Test
    public void testFollows() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(0, 1);
        Vector2d v3 = new Vector2d(2, 3);
        assertTrue(v1.follows(v2));
        assertFalse(v2.follows(v1));
        assertTrue(v3.follows(v1));
    }

    @Test
    public void testUpperRight() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(2, 1);
        Vector2d result = v1.upperRight(v2);
        assertEquals(new Vector2d(2, 2), result);
    }

    @Test
    public void testLowerLeft() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(2, 1);
        Vector2d result = v1.lowerLeft(v2);
        assertEquals(new Vector2d(1, 1), result);
    }
    @Test
    public void testAdd() {
        Vector2d v1 = new Vector2d(1, 2);
        Vector2d v2 = new Vector2d(2, 3);
        Vector2d result = v1.add(v2);
        assertEquals(new Vector2d(3, 5), result);
    }

    @Test
    public void testSubtract() {
        Vector2d v1 = new Vector2d(5, 4);
        Vector2d v2 = new Vector2d(2, 3);
        Vector2d result = v1.subtract(v2);
        assertEquals(new Vector2d(3, 1), result);
    }

    @Test
    public void testOpposite() {
        Vector2d v = new Vector2d(1, -2);
        Vector2d result = v.opposite();
        assertEquals(new Vector2d(-1, 2), result);
    }
}
