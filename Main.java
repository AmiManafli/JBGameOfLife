package life;

import java.util.Random;
import java.util.Scanner;
public class Main {

    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int universeSize = scanner.nextInt();
        int universeSize = 20;
        Random random = new Random();
        Universe universe = new Universe(random, universeSize);
        UniverseView universeView = new UniverseView();
        UniverseController universeController = new UniverseController(universe, universeView, new GameOfLife());
        universeController.advanceGeneration();
    }
}
