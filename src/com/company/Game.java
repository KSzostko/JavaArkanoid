package com.company;

import com.company.commands.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Game {
    private JFrame frame;

    public Game() {
        // @TODO: add positionStrategy field
        initScreen();
    }

    public JFrame getFrame() {
        return frame;
    }

    private void initScreen() {
        frame = new JFrame("Arkanoid");
        frame.setSize(600, 500);
        frame.getContentPane().setBackground(Color.white);
        frame.setLayout(new FlowLayout());
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        displayStartView();
    }

    private void displayStartView() {
        clearScreen();
        frame.setLayout(new BorderLayout(20, 60));

        JLabel titleLabel = new JLabel("Arkanoid", JLabel.CENTER);
        titleLabel.setFont(new Font("serif", Font.BOLD, 25));

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(Color.WHITE);

        frame.add(titleLabel, BorderLayout.PAGE_START);
        frame.add(controlPanel, BorderLayout.CENTER);

        addNavButtons(controlPanel, true);

        frame.setVisible(true);
    }

    private void executeCommand(Command command) {
        command.execute();
    }

    private void clearScreen() {
        frame.getContentPane().removeAll();
        frame.validate();
    }

    private void addNavButtons(JPanel panel, boolean isStart) {
        JButton startButton = new JButton("New Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton rankingButton = new JButton("Show Ranking");
        rankingButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton authorsButton = new JButton("Authors");
        authorsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton exitButton = new JButton("Exit");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton goBackButton = new JButton("goBack");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = new StartLevelCommand(Game.this);
                executeCommand(command);
            }
        });

        authorsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = new ShowAuthorsCommand(Game.this);
                executeCommand(command);
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = new ExitCommand(Game.this);
                executeCommand(command);
            }
        });

        rankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = new ShowRankingCommand(Game.this);
                executeCommand(command);
            }
        });

        goBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = new GoBackCommand(Game.this);
                executeCommand(command);
            }
        });

        panel.add(startButton);
        panel.add(Box.createVerticalStrut(20));

        panel.add(authorsButton);
        panel.add(Box.createVerticalStrut(20));

        if(isStart) {
            panel.add(rankingButton);
        } else {
            panel.add(goBackButton);
        }
        panel.add(Box.createVerticalStrut(20));

        panel.add(exitButton);
        panel.add(Box.createVerticalStrut(20));
    }
}
