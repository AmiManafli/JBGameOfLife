package life;

import java.util.Random;

public class Universe {
    public static final char ALIVE = 'O';
    public static final char DEAD = ' ';
    public final int size;
    public char[][] map;
    public int countAlive = 0;
    public int countGeneration = 0;
    private Random random = new Random();

    public Universe(int universeSize) {
        this.size = universeSize;
        this.map = new char[universeSize][universeSize];
        for (int i = 0; i < universeSize; i++) {
            for (int j = 0; j < universeSize; j++) {
                if (random.nextBoolean()) {
                    map[i][j] = ALIVE;
                } else {
                    map[i][j] = DEAD;
                }
            }
        }
    }


    public void nextGeneration() {
        var newGen = new char[size][size];
        int aliveNeighbours = 0;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                aliveNeighbours = countAliveNeighbours(i, j);
                if (map[i][j] == ALIVE) {
                    newGen[i][j] = (aliveNeighbours < 2 || aliveNeighbours > 3) ? DEAD : ALIVE;
                } else if (map[i][j] == DEAD) {
                    newGen[i][j] = aliveNeighbours == 3 ? ALIVE : DEAD;
                }

                if (newGen[i][j] == ALIVE) {
                    countAlive++;
                }
            }
        }
        map = newGen;
    }

    public int countAliveNeighbours(int i, int j) {
        int countAliveNeighbours = 0;
        for (int k = i - 1; k <= i + 1; k++) {
            for (int l = j - 1; l <= j + 1; l++) {
                if (k == i && l == j) continue;
                if (map[Math.floorMod(k, size)][Math.floorMod(l, size)] == ALIVE) {
                    countAliveNeighbours++;
                }
            }
        }
        return countAliveNeighbours;
    }
}