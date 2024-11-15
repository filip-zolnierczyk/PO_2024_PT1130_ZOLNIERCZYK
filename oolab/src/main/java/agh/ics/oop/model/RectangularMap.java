package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import java.util.HashMap;
import java.util.Map;

public class RectangularMap implements WorldMap {
    private final int width;
    private final int height;
    private final Map<Vector2d, Animal> animals = new HashMap<>();
    private final MapVisualizer visualizer;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.visualizer = new MapVisualizer(this);
    }


    @Override
    public boolean place(Animal animal) {
        Vector2d position = animal.getPosition();
        if (!canMoveTo(position)) {
            System.out.println("Cannot place animal at " + position + ". Position is occupied or out of bounds.");
            return false;
        }
        animals.put(position, animal);
        return true;
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        animals.remove(animal.getPosition());
        animal.move(direction, this);
        animals.put(animal.getPosition(), animal);
    }


    

    @Override
    public boolean canMoveTo(Vector2d position) {
        return isInBounds(position) && !isOccupied(position);
    }

    private boolean isInBounds(Vector2d position) {
        return position.follows(new Vector2d(0, 0)) && position.precedes(new Vector2d(width - 1, height - 1));
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public Animal objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public String toString() {
        return visualizer.draw(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
    }
}
