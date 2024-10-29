package agh.ics.oop.model;

import agh.ics.oop.Vector2d;

public enum MapDirection {
    NORTH, EAST, SOUTH, WEST;

    @Override
    public String toString() {
        return switch (this) {
            case NORTH -> "Północ";
            case EAST -> "Wschód";
            case SOUTH -> "Południe";
            case WEST -> "Zachód";
            default -> throw new IllegalArgumentException("Nieznany kierunek" + this);
        };
    }
    public  MapDirection next() {
        return switch (this) {
            case NORTH -> EAST;
            case EAST -> SOUTH;
            case SOUTH -> WEST;
            case WEST -> NORTH;
            default -> throw new IllegalArgumentException("Nieznany kierunek" + this);
        };
    }

    public  MapDirection previous() {
        return switch (this) {
            case NORTH -> WEST;
            case EAST -> NORTH;
            case SOUTH -> EAST;
            case WEST -> SOUTH;
            default -> throw new IllegalArgumentException("Nieznany kierunek" + this);
        };
    }

    public Vector2d toUnitVector() {
        return switch (this) {
            case NORTH -> new Vector2d(0, 1);
            case EAST -> new Vector2d(1, 0);
            case SOUTH -> new Vector2d(0, -1);
            case WEST -> new Vector2d(-1, 0);
            default -> throw new IllegalArgumentException("Nieznany kierunek" + this);
        };
    }
}