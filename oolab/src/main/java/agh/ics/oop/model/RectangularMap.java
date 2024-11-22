package agh.ics.oop.model;

import agh.ics.oop.model.util.MapVisualizer;

public class RectangularMap extends AbstractWorldMap implements WorldMap{
    private final int width;
    private final int height;
    private final MapVisualizer visualizer;
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
        this.visualizer = new MapVisualizer(this);
        this.lowerLeft = new Vector2d(0, 0);
        this.upperRight = new Vector2d(width - 1, height - 1);
    }

    @Override
    public boolean canMoveTo(Vector2d position) {

        return isInBounds(position) && super.canMoveTo(position);
    }

    private boolean isInBounds(Vector2d position) {
        return position.follows(lowerLeft) && position.precedes(upperRight);
    }

    @Override
    public String toString() {
        return visualizer.draw(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));
    }

}