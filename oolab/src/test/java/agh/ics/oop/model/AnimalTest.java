package agh.ics.oop.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void testInitialPositionAndOrientation() {
        Animal animal = new Animal();
        assertEquals(new Vector2d(2, 2), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }

    @Test
    void testOrientationChange() {
        Animal animal = new Animal();

        animal.move(MoveDirection.RIGHT);
        assertNotEquals(MapDirection.NORTH, animal.getOrientation());
        assertEquals(MapDirection.EAST, animal.getOrientation());

        animal.move(MoveDirection.RIGHT);
        assertNotEquals(MapDirection.EAST, animal.getOrientation());
        assertEquals(MapDirection.SOUTH, animal.getOrientation());

        animal.move(MoveDirection.LEFT);
        assertNotEquals(MapDirection.SOUTH, animal.getOrientation());
        assertEquals(MapDirection.EAST, animal.getOrientation());

        animal.move(MoveDirection.LEFT);
        assertNotEquals(MapDirection.EAST, animal.getOrientation());
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }

    @Test
    void testMovementWithinMapBoundaries() {
        Animal animal = new Animal();

        animal.move(MoveDirection.FORWARD);
        assertNotEquals(new Vector2d(2, 2), animal.getPosition());
        assertEquals(new Vector2d(2, 3), animal.getPosition());

        animal.move(MoveDirection.BACKWARD);
        assertNotEquals(new Vector2d(2, 3), animal.getPosition());
        assertEquals(new Vector2d(2, 2), animal.getPosition());

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertNotEquals(new Vector2d(2, 2), animal.getPosition());
        assertEquals(new Vector2d(1, 2), animal.getPosition());
    }

    @Test
    void testMapBoundaryConstraints() {
        Animal animal = new Animal(new Vector2d(0, 0));

        animal.move(MoveDirection.BACKWARD);
        assertEquals(new Vector2d(0, 0), animal.getPosition());

        animal.move(MoveDirection.LEFT);
        animal.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(0, 0), animal.getPosition());

        Animal animal2 = new Animal(new Vector2d(4, 4));
        animal2.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(4, 4), animal2.getPosition());

        animal2.move(MoveDirection.RIGHT);
        animal2.move(MoveDirection.FORWARD);
        assertEquals(new Vector2d(4, 4), animal2.getPosition());
    }

    @Test
    void testInputInterpretation() {
        Animal animal = new Animal();

        MoveDirection[] directions = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.BACKWARD};

        for (MoveDirection direction : directions) {
            animal.move(direction);
        }

        assertNotEquals(new Vector2d(2, 2), animal.getPosition());
        assertEquals(new Vector2d(3, 2), animal.getPosition());
        assertNotEquals(new Vector2d(3, 3), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }
}
