package agh.ics.oop.model;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    private static final Vector2d LOWER_BOUND = new Vector2d(0, 0);
    private static final Vector2d UPPER_BOUND = new Vector2d(4, 4);

    public Animal() {
        this.position = new Vector2d(2, 2);
        this.orientation = MapDirection.NORTH;
    }

    public Animal(Vector2d initialPosition) {
        this.position = initialPosition;
        this.orientation = MapDirection.NORTH;
    }

    public void move(MoveDirection direction) {
        switch (direction) {
            case FORWARD -> attemptMove(orientation.toUnitVector());
            case BACKWARD -> attemptMove(orientation.toUnitVector().opposite());
            case LEFT -> orientation = orientation.previous();
            case RIGHT -> orientation = orientation.next();
        }
    }

    private void attemptMove(Vector2d movement) {
        Vector2d newPosition = position.add(movement);
        if (newPosition.follows(LOWER_BOUND) && newPosition.precedes(UPPER_BOUND))  {
            position = newPosition;
        }
    }

    @Override
    public String toString() {
        return "Animal at " + position.toString() + " facing " + orientation.toString();
    }

    public boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }
}
