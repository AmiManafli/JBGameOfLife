package life;

public class UniverseView {
    public void showMap(Universe universe) {
        System.out.println("Generation #" + universe.countGeneration);
        System.out.println("Alive: " + universe.countAlive);
        for (int i = 0; i < universe.size; i++) {
            for (int j = 0; j < universe.size; j++) {
                System.out.print(universe.map[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

}

