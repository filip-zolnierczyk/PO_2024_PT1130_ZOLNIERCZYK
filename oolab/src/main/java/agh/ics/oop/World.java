package agh.ics.oop;

import agh.ics.oop.model.*;
import java.util.List;

public class World {
    public static void main(String[] args) {
        try {
            WorldMap map = new GrassField(10);

            ConsoleMapDisplay display = new ConsoleMapDisplay();
            map.addObserver(display);

            List<MoveDirection> directions = OptionsParser.parse(args);
            List<Vector2d> positions = List.of(new Vector2d(-3, -3), new Vector2d(3, 3));
            Simulation simulation = new Simulation(positions, directions, map);
            simulation.run();
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);  // Exit with error status
        }
    }
}
