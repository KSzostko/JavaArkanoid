package com.company;

import com.company.commands.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game {
    private JFrame frame;
    private Ranking ranking;
    //
    private LevelFileReader levelFileReader;

    public Game() {
        // @TODO: add positionStrategy field
        ranking = new Ranking();
        levelFileReader = LevelFileReader.getInstance();
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

    public void displayStartView() {
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

    public void displayAuthorsView() {
        clearScreen();
        frame.setLayout(new FlowLayout());

        String text = "This is an university project, which idea was to get accustomed to implementing design patterns.";
        JLabel infoLabel = new JLabel(text, JLabel.CENTER);
        infoLabel.setSize(new Dimension(600, 400));

        JButton button = new JButton("Go Back");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = new GoBackCommand(Game.this);
                executeCommand(command);
            }
        });

        frame.add(infoLabel);
        frame.add(button);

        frame.setVisible(true);
    }

    public void showRanking() {
        clearScreen();
        frame.setLayout(new BorderLayout(20, 60));

        JLabel leadersLabel = new JLabel("Leaderboard", JLabel.CENTER);

        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new BoxLayout(controlPanel, BoxLayout.Y_AXIS));
        controlPanel.setBackground(Color.WHITE);

        frame.add(leadersLabel, BorderLayout.PAGE_START);

        RankingAdapter adapter = new RankingAdapter();
        adapter.setTableData(ranking);
        JTable table = new JTable(adapter);
        JScrollPane scrollPane = new JScrollPane(table);

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.add(controlPanel, BorderLayout.PAGE_END);

        addNavButtons(controlPanel, false);

        frame.setVisible(true);
    }

    public void startLevel() {
        clearScreen();

        // this is for platform movement test only
        Level level = new Level(new Platform(), new Ball());
        frame.getContentPane().add(level);

        level.requestFocusInWindow();
        frame.setVisible(true);
    }

    private void executeCommand(Command command) {
        command.execute();
    }

    private void clearScreen() {
        frame.getContentPane().removeAll();
        frame.validate();
        frame.repaint();
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

        JButton goBackButton = new JButton("Go Back");
        goBackButton.setAlignmentX(Component.CENTER_ALIGNMENT);

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

        if(isStart) {
            panel.add(authorsButton);
            panel.add(Box.createVerticalStrut(20));
            panel.add(rankingButton);
        } else {
            panel.add(goBackButton);
        }
        panel.add(Box.createVerticalStrut(20));

        panel.add(exitButton);
        panel.add(Box.createVerticalStrut(20));
    }
}
