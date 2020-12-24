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
        JButton rankingButton = new JButton("Show Ranking");
        rankingButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Command command = new ShowRankingCommand(Game.this);
                executeCommand(command);
            }
        });

        frame.add(rankingButton);

        frame.setVisible(true);
    }

    private void executeCommand(Command command) {
        command.execute();
    }
}
