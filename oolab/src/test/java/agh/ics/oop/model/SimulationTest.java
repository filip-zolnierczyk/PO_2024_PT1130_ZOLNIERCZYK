package agh.ics.oop.model;
import agh.ics.oop.Simulation;
import agh.ics.oop.model.util.MapVisualizer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;

class SimulationTest {

    private RectangularMap map;
    private List<MoveDirection> moves;
    private List<Vector2d> initialPositions;

    @BeforeEach
    void setup() {
        map = new RectangularMap(5, 5);
        moves = Arrays.asList(
                MoveDirection.FORWARD, MoveDirection.RIGHT, MoveDirection.FORWARD,
                MoveDirection.LEFT, MoveDirection.BACKWARD, MoveDirection.FORWARD
        );
        initialPositions = Arrays.asList(new Vector2d(2, 2), new Vector2d(1, 1));
    }

    @Test
    void testInitialization() {
        Simulation simulation = new Simulation(initialPositions, moves, map);

        assertTrue(map.isOccupied(new Vector2d(2, 2)));
        assertTrue(map.isOccupied(new Vector2d(1, 1)));
    }

    @Test
    void testRunSimulation() {
        Simulation simulation = new Simulation(initialPositions, moves, map);

        simulation.run();

        WorldElement animal1 = map.objectAt(new Vector2d(2, 3));
        WorldElement animal2 = map.objectAt(new Vector2d(1, 2));

        assertNotNull(animal1);
        assertNotNull(animal2);

        assertEquals(new Vector2d(2, 3), animal1.getPosition());
        assertEquals(new Vector2d(1, 2), animal2.getPosition());
    }

    @Test
    void testMapVisualizationAfterSimulation() {
        Simulation simulation = new Simulation(initialPositions, moves, map);

        simulation.run();

        MapVisualizer visualizer = new MapVisualizer(map);
        String mapState = visualizer.draw(new Vector2d(0, 0), new Vector2d(4, 4));

        System.out.println("Final map state:\n" + mapState);

        assertNotNull(mapState);
    }
}
