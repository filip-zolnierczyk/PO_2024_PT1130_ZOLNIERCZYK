package agh.ics.oop.model;
import agh.ics.oop.model.util.MapVisualizer;
import java.util.*;


public class GrassField extends AbstractWorldMap {
    private final int grassCount;
    private final Map<Vector2d, Grass> grasses = new HashMap<>();
    private final Random random;

    public GrassField(int grassCount, Random random) {
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
    public String toString() {
        if (animals.isEmpty() && grasses.isEmpty()) {
            return "Empty map";
        }

        final Map<Vector2d, WorldElement> WorldElements = new HashMap<>();
        WorldElements.putAll(grasses);
        WorldElements.putAll(animals);

        int minY = Integer.MAX_VALUE;
        int minX = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;

        for (Vector2d pos : WorldElements.keySet()) {
            minX = Math.min(minX, pos.x);
            minY = Math.min(minY, pos.y);
            maxX = Math.max(maxX, pos.x);
            maxY = Math.max(maxY, pos.y);
        }


        MapVisualizer visualizer = new MapVisualizer(this);
        return visualizer.draw(new Vector2d(minX, minY), new Vector2d(maxX, maxY));
    }
}