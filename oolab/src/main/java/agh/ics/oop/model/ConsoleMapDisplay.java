package agh.ics.oop.model;

public class ConsoleMapDisplay implements MapChangeListener {
    private int updateCount = 0;

    @Override
    public synchronized void mapChanged(WorldMap worldMap, String message) {
        updateCount++;
        System.out.println("Map ID: " + worldMap.getId() + "\n" + "Update #" + updateCount + "\n" + "Message: " + message + "\n" + worldMap);
    }
}
