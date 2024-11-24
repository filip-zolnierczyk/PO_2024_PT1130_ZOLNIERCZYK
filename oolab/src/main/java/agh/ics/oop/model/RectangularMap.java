package agh.ics.oop.model;

public class RectangularMap extends AbstractWorldMap implements WorldMap {
    private final int width;
    private final int height;
    private final Vector2d lowerLeft;
    private final Vector2d upperRight;

    public RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
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
    public Boundary getCurrentBounds() {
        return new Boundary(new Vector2d(0, 0), new Vector2d(width - 1, height - 1));

    }
}