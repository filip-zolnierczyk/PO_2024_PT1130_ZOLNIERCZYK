package agh.ics.oop.model;

import agh.ics.oop.model.exceptions.IncorrectPositionException;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

public class GrassFieldTest {
    private GrassField grassField;
    private final int grassCount = 10;

    @BeforeEach
    public void setUp() {
        grassField = new GrassField(grassCount, new Random(42));
        System.out.println("Initial map state:\n" + grassField.toString());
    }

    @Test
    public void testGrassGenerationConsistency() {
        assertEquals(grassCount, grassField.getElements().stream()
                .filter(element -> element instanceof Grass).count());
    }

    @Test
    public void testObjectAt() {
        WorldElement element1 = grassField.objectAt(new Vector2d(0, 9));
        Animal animal = new Animal(new Vector2d(2, 2));
        assertDoesNotThrow(() -> grassField.place(animal));
        assertInstanceOf(Animal.class, grassField.objectAt(new Vector2d(2, 2)));
        assertInstanceOf(Grass.class, element1);
        assertNull(grassField.objectAt(new Vector2d(0, 0)));
    }

    @Test
    public void testIsOccupied() {
        Animal animal = new Animal(new Vector2d(2, 2));
        assertDoesNotThrow(() -> grassField.place(animal));
        assertTrue(grassField.isOccupied(new Vector2d(2, 2)));
        assertFalse(grassField.isOccupied(new Vector2d(0, 0)));
        assertTrue(grassField.isOccupied(new Vector2d(0, 9)));
    }

    @Test
    public void testPlaceAnimal() {
        Animal animal = new Animal(new Vector2d(2, 2));
        Animal animal2 = new Animal(new Vector2d(0, 9));
        Animal animal3 = new Animal(new Vector2d(2, 2));

        assertDoesNotThrow(() -> grassField.place(animal)); // Should succeed
        assertThrows(IncorrectPositionException.class, () -> grassField.place(animal2)); // Grass position occupied
        assertThrows(IncorrectPositionException.class, () -> grassField.place(animal3)); // Same position as animal
    }

    @Test
    public void testMoveAnimal() {
        Animal animal = new Animal(new Vector2d(0, 8));
        Animal animal2 = new Animal(new Vector2d(2, 8));
        assertDoesNotThrow(() -> grassField.place(animal));
        assertDoesNotThrow(() -> grassField.place(animal2));

        grassField.move(animal, MoveDirection.FORWARD);
        assertNotEquals(new Vector2d(0, 9), animal.getPosition());
        assertEquals(new Vector2d(0, 8), animal.getPosition());

        grassField.move(animal, MoveDirection.RIGHT);
        grassField.move(animal, MoveDirection.FORWARD);
        assertEquals(new Vector2d(1, 8), animal.getPosition());
        grassField.move(animal, MoveDirection.FORWARD);
        assertNotEquals(new Vector2d(2, 8), animal.getPosition());
    }

    @Test
    public void testGetElements() {
        Animal animal = new Animal(new Vector2d(1, 1));
        assertDoesNotThrow(() -> grassField.place(animal));
        Collection<WorldElement> elements = grassField.getElements();

        long animalCount = elements.stream().filter(element -> element instanceof Animal).count();
        long grassCount = elements.stream().filter(element -> element instanceof Grass).count();

        assertEquals(1, animalCount);
        assertEquals(10, grassCount);
        assertEquals(11, elements.size());
    }
}
