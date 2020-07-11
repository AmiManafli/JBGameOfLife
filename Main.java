package life;
public class Main {

    public static void main(String[] args) {
        int startingUniverseSize = 20;
        Universe universe = new Universe(startingUniverseSize);
        UniverseView universeView = new UniverseView();
        UniverseController universeController = new UniverseController(universe, universeView, new GameOfLife());
        universeController.advanceGeneration();
    }
}
