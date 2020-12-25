package com.company.commands;

import com.company.Game;

import javax.swing.*;

public class ExitCommand extends Command {
    public ExitCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        JFrame frame = game.getFrame();
        System.exit(0);
        frame.dispose();
        frame.setVisible(false);
    }
}
