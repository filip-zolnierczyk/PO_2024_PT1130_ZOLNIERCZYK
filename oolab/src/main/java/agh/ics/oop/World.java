package agh.ics.oop;

import agh.ics.oop.model.*;
import java.util.List;

public class World {
    public static void main(String[] args) {
        WorldMap map = new GrassField(10);
        List<MoveDirection> directions = OptionsParser.parse(args);
        List<Vector2d> positions = List.of(new Vector2d(-3, -3), new Vector2d(3, 3));
        Simulation simulation = new Simulation(positions, directions, map);
        simulation.run();
    }
}