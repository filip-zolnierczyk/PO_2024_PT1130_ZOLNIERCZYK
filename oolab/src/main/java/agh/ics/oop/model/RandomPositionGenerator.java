package agh.ics.oop.model;

import java.util.*;

public class RandomPositionGenerator implements Iterable<Vector2d> {
    private final List<Vector2d> availablePositions;
    private final Random random;
    private final int grassCount;

    public RandomPositionGenerator(int maxWidth, int maxHeight, int grassCount, Random random) {
        this.random = random;
        this.grassCount = grassCount;

        // Generujemy wszystkie możliwe pozycje
        this.availablePositions = new ArrayList<>();
        for (int x = 0; x <= maxWidth; x++) {
            for (int y = 0; y <= maxHeight; y++) {
                availablePositions.add(new Vector2d(x, y));
            }
        }

        // Sprawdzamy, czy liczba żądanych pozycji nie przekracza dostępnych
        if (grassCount > availablePositions.size()) {
            throw new IllegalArgumentException("Grass count exceeds available positions.");
        }
    }

    public RandomPositionGenerator(int maxWidth, int maxHeight, int grassCount) {
        this(maxWidth, maxHeight, grassCount, new Random());
    }

    @Override
    public Iterator<Vector2d> iterator() {
        return new RandomPositionIterator();
    }

    private class RandomPositionIterator implements Iterator<Vector2d> {
        private int remaining = grassCount;

        @Override
        public boolean hasNext() {
            return remaining > 0;
        }

        @Override
        public Vector2d next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            // Losujemy indeks bez mieszania całej listy
            int index = random.nextInt(availablePositions.size());
            Vector2d position = availablePositions.remove(index); // Usuwamy, aby uniknąć duplikatów
            remaining--;
            return position;
        }
    }
}

/*
PRZYKŁAD UŻYCIA W GRASSFIELD:
protected void generateGrassFields() {
    int maxCoordinate = (int) Math.sqrt(this.grassCount * 10);
    RandomPositionGenerator randomPositionGenerator = new RandomPositionGenerator(maxCoordinate, maxCoordinate, grassCount, random);
    Iterator<Vector2d> positionsIterator = randomPositionGenerator.iterator();
    while (positionsIterator.hasNext()) {
        Vector2d grassPosition = positionsIterator.next();
        grasses.put(grassPosition, new Grass(grassPosition));
    }
}
 */