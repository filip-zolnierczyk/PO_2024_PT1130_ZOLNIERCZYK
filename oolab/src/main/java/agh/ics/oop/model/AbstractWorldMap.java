package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;
import java.util.*;
import agh.ics.oop.model.exceptions.IncorrectPositionException;

abstract class AbstractWorldMap implements WorldMap {
    private final UUID id;
    public final Map<Vector2d, Animal> animals = new HashMap<>();
    private final List<MapChangeListener> observers = new ArrayList<>();
    MapVisualizer visualizer = new MapVisualizer(this);

    public AbstractWorldMap() {
        this.id = UUID.randomUUID(); // Automatically generate a unique ID
    }

    public void addObserver(ConsoleMapDisplay observer) {
        observers.add(observer);
    }

    public void removeObserver(ConsoleMapDisplay observer) {
        observers.remove(observer);
    }

    protected void notifyObservers(String message) {
        for (MapChangeListener observer : observers) {
            observer.mapChanged(this, message);
        }
    }


    @Override
    public UUID getId() {
        return id;
    }


    @Override
    public void place(Animal animal) throws IncorrectPositionException {
        Vector2d position = animal.getPosition();
        if (!canMoveTo(position)) {
            throw new IncorrectPositionException(position);
        }
        animals.put(position, animal);
        notifyObservers("Animal placed at " + animal.getPosition());
    }

    @Override
    public void move(Animal animal, MoveDirection direction) {
        animals.remove(animal.getPosition());
        animal.move(direction, this);
        animals.put(animal.getPosition(), animal);
        notifyObservers("Animal moved to " + animal.getPosition());
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        return animals.get(position);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return animals.containsKey(position);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        return !isOccupied(position);
    }

    @Override
    public Collection<WorldElement> getElements() {
        return new ArrayList<>(animals.values());
    }

    public abstract Boundary getCurrentBounds();

    @Override
    public String toString() {
        Boundary bounds = getCurrentBounds();
        return visualizer.draw(bounds.lowerLeft(), bounds.upperRight());
    }
}



