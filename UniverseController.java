package life;

import java.io.IOException;

public class UniverseController {
    private Universe universe;
    private UniverseView universeView;
    private GameOfLife gameOfLife;

    public UniverseController(Universe universe, UniverseView universeView, GameOfLife gameOfLife) {
        this.universe = universe;
        this.universeView = universeView;
        this.gameOfLife = gameOfLife;
    }

    public void advanceGeneration() {
        while (true) {
            switch (gameOfLife.state) {
                case RUNNING:
                    universe.nextGeneration();
                    updateView();
                    break;
                case PAUSED:
                    break;
                case RESET:
                    gameOfLife.state = State.RUNNING;
                    universe = new Universe(gameOfLife.universeSize);
                    break;
            }
        }
    }

    public void updateView() {
        try {
            System.out.println("Current speed is " + gameOfLife.speed);
            Thread.sleep(1000 / gameOfLife.speed);
        } catch (InterruptedException ignored) {
        }
        gameOfLife.generationLabel.setText("Generation #" + universe.countGeneration);
        gameOfLife.aliveLabel.setText("Alive: " + universe.countAlive);
        gameOfLife.speedLabel.setText("Speed: " + gameOfLife.speed);
        gameOfLife.gameBoard.draw(universe);
        universe.countGeneration++;
        universe.countAlive = 0;
    }
}