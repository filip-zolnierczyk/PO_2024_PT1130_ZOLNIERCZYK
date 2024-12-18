package agh.ics.oop.presenter;

import agh.ics.oop.model.*;
import agh.ics.oop.*;
import agh.ics.oop.model.exceptions.IncorrectPositionException;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.geometry.HPos;

import java.util.List;

public class SimulationPresenter {
    @FXML
    private GridPane mapGrid;


    @FXML
    private TextField movesField;

    @FXML
    private Button startButton;

    private AbstractWorldMap worldMap;

    public void initialize() {

        worldMap = new GrassField(5);
        //worldMap = new RectangularMap(10, 10);
        try {
            worldMap.place(new Animal(new Vector2d(2, 4)));
            worldMap.place(new Animal(new Vector2d(5, 6)));
        } catch (IncorrectPositionException e) {
            throw new RuntimeException(e);
        }
        drawMap();
    }

    @FXML
    public void onSimulationStartClicked() {
        String movesInput = movesField.getText();
        try {
            List<MoveDirection> directions = OptionsParser.parse(movesInput.split(" "));
            runSimulation(directions);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    private void runSimulation(List<MoveDirection> directions) {
        new Thread(() -> {
            int animalIndex = 0;
            List<Animal> animalsList = List.copyOf(worldMap.animals.values());

            for (MoveDirection direction : directions) {
                int currentIndex = animalIndex % animalsList.size();
                Animal currentAnimal = animalsList.get(currentIndex);

                Platform.runLater(() -> {
                    worldMap.move(currentAnimal, direction);
                    drawMap();
                });

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println("Simulation interrupted");
                }

                animalIndex++;
            }
        }).start();
    }

    public void drawMap() {
        mapGrid.getChildren().clear();
        int cellSize = 50;

        Boundary boundary = worldMap.getCurrentBounds();
        Vector2d lowerLeft = boundary.lowerLeft();
        Vector2d upperRight = boundary.upperRight();


        for (int y = lowerLeft.y; y <= upperRight.y; y++) {
            for (int x = lowerLeft.x; x <= upperRight.x; x++) {
                Label background = new Label();
                background.setMinSize(cellSize, cellSize);
                background.setStyle("-fx-border-color: lightgrey; -fx-border-width: 1;");
                mapGrid.add(background, x - lowerLeft.x + 1, upperRight.y - y + 1);
            }
        }

        for (int x = lowerLeft.x; x <= upperRight.x; x++) {
            Label label = new Label(String.valueOf(x));
            mapGrid.add(label, x - lowerLeft.x + 1, 0);
        }
        for (int y = lowerLeft.y; y <= upperRight.y; y++) {
            Label label = new Label(String.valueOf(y));
            mapGrid.add(label, 0, upperRight.y - y + 1);
        }

        for (int y = lowerLeft.y; y <= upperRight.y; y++) {
            for (int x = lowerLeft.x; x <= upperRight.x; x++) {
                Vector2d position = new Vector2d(x, y);
                WorldElement element = worldMap.objectAt(position);

                Label label = new Label();
                label.setMinSize(cellSize, cellSize);
                GridPane.setHalignment(label, HPos.CENTER);

                if (element instanceof Animal animal) {
                    switch (animal.getOrientation()) {
                        case NORTH: label.setText("^"); break;
                        case SOUTH: label.setText("v"); break;
                        case EAST:  label.setText(">"); break;
                        case WEST:  label.setText("<"); break;
                    }
                }
                if (element instanceof Grass) {
                    label.setText("*");
                }

                if (!label.getText().isEmpty()) {
                    mapGrid.add(label, x - lowerLeft.x + 1, upperRight.y - y + 1);
                }
            }
        }
    }

}