package agh.ics.oop;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class SimulationEngine {
    private final List<Simulation> simulations;
    private ExecutorService executorService;

    public SimulationEngine(List<Simulation> simulations) {
        this.simulations = simulations;
    }

    public void runSync() {
        int simulationNumber = 1;
        for (Simulation simulation : simulations) {
            System.out.println("Starting simulation " + simulationNumber);
            simulation.run();
            System.out.println("Simulation " + simulationNumber + " finished.");
            System.out.println("===============================");
            simulationNumber++;
        }
    }

    public void runAsync() {
        int simulationNumber = 1;

        for (Simulation simulation : simulations) {
            int currentSimulation = simulationNumber;
            Thread thread = new Thread(() -> {
                synchronized (System.out) {
                    System.out.println("Starting simulation " + currentSimulation);
                }
                simulation.run();
                synchronized (System.out) {
                    System.out.println("Simulation " + currentSimulation + " finished.");
                    System.out.println("===============================");
                }
            });
            thread.start();
            simulationNumber++;
        }
    }


    public void runAsyncInThreadPool() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        Object lock = new Object();

        int simulationNumber = 1;
        for (Simulation simulation : simulations) {
            int currentSimulation = simulationNumber;
            executorService.submit(() -> {
                synchronized (lock) {
                    System.out.println("Starting simulation " + currentSimulation);
                }
                simulation.run();
                synchronized (lock) {
                    System.out.println("Simulation " + currentSimulation + " finished.");
                    System.out.println("===============================");
                }
            });
            simulationNumber++;
        }

        executorService.shutdown();
    }

    public void awaitSimulationsEnd() {
        if (executorService != null) {
            executorService.shutdown();
            try {
                if (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
                    System.out.println("Timeout: Forcefully shutting down thread pool.");
                    executorService.shutdownNow();
                } else {
                    System.out.println("All simulations completed successfully.");
                }
            } catch (InterruptedException e) {
                System.out.println("Interrupted while waiting for thread pool to terminate.");
                executorService.shutdownNow();
            }
        }
    }
}
