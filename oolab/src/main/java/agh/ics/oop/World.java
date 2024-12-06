package agh.ics.oop;

import agh.ics.oop.model.*;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

public class World {
    public static void main(String[] args) {
        try {
            List<Simulation> simulations = new ArrayList<>();
            int numberOfSimulations = 2;

            for (int i = 0; i < numberOfSimulations; i++) {
                List<Vector2d> initialPositions = Arrays.asList(
                        new Vector2d(2, 2),
                        new Vector2d(3, 3)
                );

                List<MoveDirection> directions = OptionsParser.parse(args);

                WorldMap map = new GrassField(10);
                ConsoleMapDisplay mapDisplay = new ConsoleMapDisplay();
                map.addObserver(mapDisplay);
                Simulation simulation = new Simulation(initialPositions, directions, map);
                simulations.add(simulation);
            }

            SimulationEngine engine = new SimulationEngine(simulations);

            engine.runAsync();
            engine.awaitSimulationsEnd();

            System.out.println("System zakończył działanie.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        }
    }
}
