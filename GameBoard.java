package life;

import javax.swing.*;
import java.awt.*;

public class GameBoard extends JPanel {
    Universe universe;
    int cellSize;

    public GameBoard(int cellSize) {
        this.cellSize = cellSize;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (universe == null) {
            return;
        }
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
        graphics.setColor(Color.black);
        for (int i = 0; i < universe.size; i++) {
            for (int j = 0; j < universe.size; j++) {
                if (universe.map[i][j] == universe.ALIVE) {
                    graphics.fillRect(i * cellSize, j * cellSize, cellSize, cellSize);
                }
                graphics.drawRect(i * cellSize, j * cellSize, cellSize, cellSize);
            }
        }
    }

    public void draw(Universe universe) {
        this.universe = universe;
        repaint();
    }
}
