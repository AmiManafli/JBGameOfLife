package life;

import java.io.IOException;

public class UniverseController {
    private Universe universe;
    private UniverseView universeView;
    private GameOfLife gameOfLife;
    public int numberOfGenerations = 50; //make possible to be changed in the interface

    public UniverseController(Universe universe, UniverseView universeView, GameOfLife gameOfLife) {
        this.universe = universe;
        this.universeView = universeView;
        this.gameOfLife = gameOfLife;
    }

    public void advanceGeneration() {
//        for (int i = 0; i < numberOfGenerations; i++) {
//            universe.nextGeneration();
//            updateView();
//        }
        while (true) {
//            System.out.println("In the while loop");
            switch (gameOfLife.state) {
                case RUNNING:
                    universe.nextGeneration();
                    updateView();
                    break;
                case PAUSED:
                    break;
            }
        }
    }

    public void updateView() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ignored) {}
        gameOfLife.GenerationLabel.setText("Generation #" + universe.countGeneration);
        gameOfLife.AliveLabel.setText("Alive: " + universe.countAlive);
        gameOfLife.gameBoard.draw(universe);
        universe.countGeneration++;
        universe.countAlive = 0;
    }

    private void clearOutputScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        }
        catch (IOException | InterruptedException ignored) {}
    }
}