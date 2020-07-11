package life;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum State {
    RUNNING, PAUSED,
}


public class GameOfLife extends JFrame {
    GameBoard gameBoard;
    JLabel GenerationLabel;
    JLabel AliveLabel;
    int cellSize = 20;
    boolean paused = false;
    volatile State state = State.RUNNING;
    volatile int speed = 1;

    public GameOfLife() {
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel();
        gamePanel.setPreferredSize(new Dimension(410, 440));
        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

        JPanel controlPanel = new JPanel();
        createControlPanel(controlPanel);
//        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));


        gameBoard = new GameBoard(cellSize);


        gamePanel.add(gameBoard);

        getContentPane().add(controlPanel, BorderLayout.WEST);
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    private void createControlPanel(JPanel controlPanel) {
        GroupLayout layout = new GroupLayout(controlPanel);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);


        //horizontal: pause button (parallel group left {the rest}), reset button
        //vertical: parallel group{buttons}, generation label, alive label, speed label, slider

        GenerationLabel = new JLabel();
        GenerationLabel.setName("GenerationLabel");
        GenerationLabel.setText("Generation #0");

        AliveLabel = new JLabel();
        AliveLabel.setName("AliveLabel");
        AliveLabel.setText("Alive: 0");

        JToggleButton pauseButton = new JToggleButton("Pause/Resume");
        pauseButton.setName("PlayToggleButton");
        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                switch (state) {
                    case PAUSED:
                        state = State.RUNNING;
                        break;
                    case RUNNING:
                        state = State.PAUSED;
                        break;
                }
            }
        });

        JButton resetButton = new JButton("Reset");
        resetButton.setName("ResetButton");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

            }
        });

        JLabel speedLabel = new JLabel("Speed: " + speed + " generation/sec");

        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setMajorTickSpacing(2);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);


        controlPanel.add(pauseButton);
        controlPanel.add(resetButton);
        controlPanel.add(GenerationLabel);
        controlPanel.add(AliveLabel);
        controlPanel.add(speedLabel);
        controlPanel.add(speedSlider);
    }

}