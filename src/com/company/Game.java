package com.company;

import javax.swing.*;
import java.awt.*;
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
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        frame.setVisible(true);
    }
}
