package com.company;

import javax.swing.*;
import java.awt.*;

public class ViewMenu {
    private JFrame frame;
    private ControllerMenu controllerMenu;
    private JLabel scoreLabelNum;
    private JLabel bestScoreLabelNum;

    public void create() {
        frame = new JFrame();
        frame.setSize(600, 450);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setUndecorated(true);
        frame.getContentPane().setBackground(Color.BLACK);

        JLabel scoreLabel = new JLabel("Score");
        scoreLabel.setBounds(143, 180, 100, 100);
        scoreLabel.setForeground(Color.RED);
        scoreLabel.setFont(new Font("", Font.BOLD, 20));
        frame.add(scoreLabel);

        scoreLabelNum = new JLabel("O");
        scoreLabelNum.setBounds(155, 100, 100, 100);
        scoreLabelNum.setForeground(Color.RED);
        scoreLabelNum.setFont(new Font("", Font.BOLD, 40));
        frame.add(scoreLabelNum);

        JLabel bestScoreLabel = new JLabel("Best Score");
        bestScoreLabel.setBounds(350, 180, 200, 100);
        bestScoreLabel.setForeground(Color.RED);
        bestScoreLabel.setFont(new Font("", Font.BOLD, 20));
        frame.add(bestScoreLabel);

        bestScoreLabelNum = new JLabel("O");
        bestScoreLabelNum.setBounds(385, 100, 100, 100);
        bestScoreLabelNum.setForeground(Color.RED);
        bestScoreLabelNum.setFont(new Font("", Font.BOLD, 40));
        frame.add(bestScoreLabelNum);

        JButton exitButton = new JButton("Exit");
        exitButton.setBounds(100, 320, 150, 70);
        exitButton.addActionListener(e -> controllerMenu.handleExitButtonClick());
        frame.add(exitButton);

        JButton playAgainButton = new JButton("Play");
        playAgainButton.setBounds(330, 320, 150, 70);
        playAgainButton.addActionListener(e -> new Thread(() -> controllerMenu.handlePlayButtonClick()).start());
        frame.add(playAgainButton);

        frame.setVisible(true);
    }

    public void setScoreLabelText(int num) {
        scoreLabelNum.setText(String.valueOf(num));
    }

    public void setBestScoreLabel(int num) {
        bestScoreLabelNum.setText(String.valueOf(num));
    }

    public void setController(ControllerMenu controllerMenu) {
        this.controllerMenu = controllerMenu;
    }

    public void disposeFrame() {
        frame.dispose();
    }
}
