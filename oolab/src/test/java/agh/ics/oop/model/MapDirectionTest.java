package agh.ics.oop.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MapDirectionTest {

    @Test
    public void testNextNorth() {
        assertEquals(MapDirection.EAST, MapDirection.NORTH.next(), "NORTH.next() should return EAST");
    }

    @Test
    public void testNextEast() {
        assertEquals(MapDirection.SOUTH, MapDirection.EAST.next(), "EAST.next() should return SOUTH");
    }

    @Test
    public void testNextSouth() {
        assertEquals(MapDirection.WEST, MapDirection.SOUTH.next(), "SOUTH.next() should return WEST");
    }

    @Test
    public void testNextWest() {
        assertEquals(MapDirection.NORTH, MapDirection.WEST.next(), "WEST.next() should return NORTH");
    }


    @Test
    public void testPreviousNorth() {
        assertEquals(MapDirection.WEST, MapDirection.NORTH.previous(), "North.previous() should return WEST");
    }

    @Test
    public void testPrevoiusEast() {
        assertEquals(MapDirection.NORTH, MapDirection.EAST.previous(),"EAST.previous() should return NORTH");
    }

    @Test
    public void testPreviousSouth() {
        assertEquals(MapDirection.EAST, MapDirection.SOUTH.previous(), "SOUTH.previous() should return EAST");
    }

    @Test
    public void testPreviousWest() {
        assertEquals(MapDirection.SOUTH, MapDirection.WEST.previous(), "WEST.previous() should return SOUTH");
    }
}

