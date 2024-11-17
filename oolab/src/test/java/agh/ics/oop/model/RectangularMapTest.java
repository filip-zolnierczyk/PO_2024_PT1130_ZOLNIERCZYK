package agh.ics.oop.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import java.util.Collection;
import static org.junit.jupiter.api.Assertions.*;

public class RectangularMapTest {
    private RectangularMap map;

    @BeforeEach
    public void setUp() {
        map = new RectangularMap(5, 5);
    }

    @Test
    public void testPlaceAnimal() {
        Animal animal1 = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(2, 2));
        Animal animal3 = new Animal(new Vector2d(4, 4));

        assertTrue(map.place(animal1));
        assertFalse(map.place(animal2));
        assertTrue(map.place(animal3));
    }

    @Test
    public void testMoveAnimal() {
        Animal animal = new Animal(new Vector2d(2, 2));
        map.place(animal);

        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(2, 3), animal.getPosition());

        map.move(animal, MoveDirection.RIGHT);
        map.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(3, 3), animal.getPosition());
    }

    @Test
    public void testCanMoveTo() {
        Animal animal = new Animal(new Vector2d(2, 2));
        map.place(animal);

        assertFalse(map.canMoveTo(new Vector2d(2, 2)));
        assertTrue(map.canMoveTo(new Vector2d(3, 3)));
        assertFalse(map.canMoveTo(new Vector2d(5, 5)));
    }

    @Test
    public void testIsOccupied() {
        Animal animal = new Animal(new Vector2d(1, 1));
        map.place(animal);

        assertTrue(map.isOccupied(new Vector2d(1, 1)));
        assertFalse(map.isOccupied(new Vector2d(0, 0)));
    }

    @Test
    public void testObjectAt() {
        Animal animal = new Animal(new Vector2d(3, 3));
        map.place(animal);

        assertEquals(animal, map.objectAt(new Vector2d(3, 3)));
        assertNull(map.objectAt(new Vector2d(2, 2)));
    }

    @Test
    public void testGetElements() {
        Animal animal = new Animal(new Vector2d(1, 1));
        map.place(animal);
        Collection<WorldElement> elements = map.getElements();
        long animalCount = elements.stream().filter(element -> element instanceof Animal).count();
        assertEquals(1, animalCount);
        assertEquals(1, elements.size());
    }
}
