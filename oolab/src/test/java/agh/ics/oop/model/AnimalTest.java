package agh.ics.oop.model;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {


    private final MoveValidator validator = position ->
            position.x >= 0 && position.x <= 4 && position.y >= 0 && position.y <= 4;

    @Test
    void testInitialPositionAndOrientation() {
        Animal animal = new Animal();
        assertEquals(new Vector2d(2, 2), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }

    @Test
    void testOrientationChange() {
        Animal animal = new Animal();

        animal.move(MoveDirection.RIGHT, validator);
        assertNotEquals(MapDirection.NORTH, animal.getOrientation());
        assertEquals(MapDirection.EAST, animal.getOrientation());

        animal.move(MoveDirection.RIGHT, validator);
        assertNotEquals(MapDirection.EAST, animal.getOrientation());
        assertEquals(MapDirection.SOUTH, animal.getOrientation());

        animal.move(MoveDirection.LEFT, validator);
        assertNotEquals(MapDirection.SOUTH, animal.getOrientation());
        assertEquals(MapDirection.EAST, animal.getOrientation());

        animal.move(MoveDirection.LEFT, validator);
        assertNotEquals(MapDirection.EAST, animal.getOrientation());
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }



    @Test
    void testInputInterpretation() {
        Animal animal = new Animal();

        MoveDirection[] directions = {MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD, MoveDirection.LEFT, MoveDirection.BACKWARD};

        for (MoveDirection direction : directions) {
            animal.move(direction, validator);
        }

        assertNotEquals(new Vector2d(2, 2), animal.getPosition());
        assertEquals(new Vector2d(3, 2), animal.getPosition());
        assertNotEquals(new Vector2d(3, 3), animal.getPosition());
        assertEquals(MapDirection.NORTH, animal.getOrientation());
    }
}
