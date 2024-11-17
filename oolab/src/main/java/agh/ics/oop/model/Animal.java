package agh.ics.oop.model;

public class Animal implements WorldElement{
    private MapDirection orientation;
    private Vector2d position;

    public Animal() {
        this.position = new Vector2d(2, 2);
        this.orientation = MapDirection.NORTH;
    }

    public Animal(Vector2d initialPosition) {
        this.position = initialPosition;
        this.orientation = MapDirection.NORTH;
    }

    public void move(MoveDirection direction, MoveValidator validator) {
        Vector2d movement = switch (direction) {
            case FORWARD -> orientation.toUnitVector();
            case BACKWARD -> orientation.toUnitVector().opposite();
            case LEFT -> {
                orientation = orientation.previous();
                yield null;
            }
            case RIGHT -> {
                orientation = orientation.next();
                yield null;
            }
        };

        if (movement != null) {
            Vector2d newPosition = position.add(movement);
            if (validator.canMoveTo(newPosition)) {
                position = newPosition;
            }
        }
    }

    @Override
    public String toString() {
        return switch (orientation) {
            case NORTH -> "^";
            case SOUTH -> "v";
            case EAST -> ">";
            case WEST -> "<";
        };
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

