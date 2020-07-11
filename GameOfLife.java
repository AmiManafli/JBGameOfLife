package life;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

enum State {
    RUNNING, PAUSED, RESET,
}

public class GameOfLife extends JFrame {
    GameBoard gameBoard;
    JLabel generationLabel;
    JLabel aliveLabel;
    JLabel speedLabel;
    int cellSize = 20;
    volatile State state = State.RUNNING;
    volatile int speed = 1;
    volatile int universeSize = 20;

    public GameOfLife() {
        super("Game of Life");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel gamePanel = new JPanel();
        gamePanel.setMinimumSize(new Dimension(100, 100));
        gamePanel.setPreferredSize(new Dimension(500, 500));
        gamePanel.setMaximumSize(new Dimension(1000, 1000));

        gamePanel.setLayout(new BoxLayout(gamePanel, BoxLayout.Y_AXIS));

        JPanel controlPanel = new JPanel();
        createControlPanel(controlPanel);

        gameBoard = new GameBoard(cellSize);

        gamePanel.add(gameBoard);

        getContentPane().add(controlPanel, BorderLayout.WEST);
        getContentPane().add(gamePanel, BorderLayout.CENTER);
        pack();
        setVisible(true);
    }

    private void createControlPanel(JPanel controlPanel) {
        GroupLayout layout = new GroupLayout(controlPanel);
        controlPanel.setLayout(layout);

        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        generationLabel = new JLabel();
        generationLabel.setName("GenerationLabel");
        generationLabel.setText("Generation #0");

        aliveLabel = new JLabel();
        aliveLabel.setName("AliveLabel");
        aliveLabel.setText("Alive: 0");

        JToggleButton pauseButton = new JToggleButton("Pause/Resume");
        pauseButton.setName("PlayToggleButton");
        pauseButton.addActionListener(actionEvent -> {
            switch (state) {
                case PAUSED:
                    state = State.RUNNING;
                    break;
                case RUNNING:
                    state = State.PAUSED;
                    break;
            }
        });

        JLabel setSizeLabel = new JLabel();
        setSizeLabel.setText("Universe Size: ");

        JTextField setSizeField = new JTextField();
        setSizeField.setText("20");
        setSizeField.setSize(new Dimension(10, 20));


        JButton resetButton = new JButton("Reset");
        resetButton.setName("ResetButton");
        resetButton.addActionListener(actionEvent -> {
            try {
                universeSize = Integer.parseInt(setSizeField.getText());
            } catch (NumberFormatException ignored) {
            }
            state = State.RESET;
        });

        speedLabel = new JLabel();
        speedLabel.setName("SpeedLabel");
        speedLabel.setText("Speed: " + speed);

        JSlider speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 10, 1);
        speedSlider.setMinorTickSpacing(1);
        speedSlider.setMajorTickSpacing(2);
        speedSlider.setPaintTicks(true);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(changeEvent -> speed = speedSlider.getValue());

        JPanel setParamsPanel = new JPanel();
        setParamsPanel.setLayout(new BoxLayout(setParamsPanel, BoxLayout.X_AXIS));
        setParamsPanel.add(setSizeLabel);
        setParamsPanel.add(setSizeField);
        setParamsPanel.add(resetButton);

        layout.setHorizontalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                .addComponent(pauseButton)
                                .addComponent(generationLabel)
                                .addComponent(aliveLabel)
                                .addComponent(speedLabel)
                                .addComponent(speedSlider)
                                .addComponent(setParamsPanel))
        );
        layout.setVerticalGroup(
                layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(pauseButton))
                        .addComponent(generationLabel)
                        .addComponent(aliveLabel)
                        .addComponent(speedLabel)
                        .addComponent(speedSlider)
                        .addComponent(setParamsPanel, 20, 30, 30)
        );
    }
}