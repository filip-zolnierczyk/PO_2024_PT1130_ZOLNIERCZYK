package agh.ics.oop;

import agh.ics.oop.model.Animal;
import agh.ics.oop.model.MoveDirection;
import agh.ics.oop.model.Vector2d;
import agh.ics.oop.model.WorldMap;
import agh.ics.oop.model.exceptions.IncorrectPositionException;

import java.util.ArrayList;
import java.util.List;


public class Simulation {
    private final List<Animal> animals;
    private final List<MoveDirection> moves;
    private final WorldMap map;

    public Simulation(List<Vector2d> initialPositions, List<MoveDirection> moves, WorldMap map) {
        this.animals = new ArrayList<>();
        this.moves = moves;
        this.map = map;

        for (Vector2d position : initialPositions) {
            Animal animal = new Animal(position);
            try {
                map.place(animal);
                animals.add(animal);
            } catch (IncorrectPositionException e) {
                System.out.println("Skipping animal at invalid position: " + position + ". Reason: " + e.getMessage());
            }
        }
    }

    public void run() {
        int numberOfAnimals = animals.size();
        int moveIndex = 0;
        int animalIndex = 0;
        while (moveIndex < moves.size()) {

                Animal animal = animals.get(animalIndex);
                MoveDirection direction = moves.get(moveIndex);

                map.move(animal, direction);


                moveIndex++;
                animalIndex++;
                if (animalIndex == numberOfAnimals) {
                    animalIndex = 0;
                }

                if (moveIndex >= moves.size()) {
                    break;

            }
        }
    }
}