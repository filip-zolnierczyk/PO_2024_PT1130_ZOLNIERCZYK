package agh.ics.oop.model;
import java.util.*;


public class GrassField extends AbstractWorldMap {
    private final int grassCount;
    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    private final Random random;

    public GrassField(int grassCount, Random random) {
        super();
        this.grassCount = grassCount;
        this.random = random;
        generateGrassFields();
    }

    public GrassField(int grassCount) {
        this(grassCount, new Random());
    }

    protected void generateGrassFields() {
        int maxCoordinate = (int) Math.sqrt(this.grassCount * 10);
        while (grasses.size() < this.grassCount) {
            int x = random.nextInt(maxCoordinate + 1);
            int y = random.nextInt(maxCoordinate + 1);
            Vector2d position = new Vector2d(x, y);
            if (!grasses.containsKey(position)) {
                grasses.put(position, new Grass(position));
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return super.isOccupied(position) || grasses.containsKey(position);
    }

    @Override
    public WorldElement objectAt(Vector2d position) {
        WorldElement element = super.objectAt(position);
        if (element != null) {
            return element;
        }
        return grasses.get(position);
    }

    @Override
    public Collection<WorldElement> getElements() {
        List<WorldElement> elements = new ArrayList<>(super.getElements());
        elements.addAll(grasses.values());
        return elements;
    }


    @Override
    public Boundary getCurrentBounds() {
        Optional<Vector2d> lowerLeft = getElements().stream()
                .map(WorldElement::getPosition)
                .reduce(Vector2d::lowerLeft);
        Optional<Vector2d> upperRight = getElements().stream()
                .map(WorldElement::getPosition)
                .reduce(Vector2d::upperRight);

        return new Boundary(lowerLeft.orElse(new Vector2d(0, 0)), upperRight.orElse(new Vector2d(0, 0)));
    }
}
