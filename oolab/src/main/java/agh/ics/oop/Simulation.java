package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;

import java.util.ArrayList;
import java.util.List;

public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> moves;

    public Simulation(List<Vector2d> initialPositions, List<MoveDirection> moves) {
        this.animals = new ArrayList<>();
        this.moves = moves;

        for (Vector2d position : initialPositions) {
            animals.add(new Animal(position));
        }
    }
    public void run() {
        int numberOfAnimals = animals.size();
        int moveIndex = 0;

        while (moveIndex < moves.size()) {
            for (int i = 0; i < numberOfAnimals; i++) {
                Animal animal = animals.get(i);
                animal.move(moves.get(moveIndex));
                Vector2d position = animal.getPosition();
                System.out.println("Zwierzę" + i + ": " + position);
                moveIndex++;
            }
        }
    }
}
