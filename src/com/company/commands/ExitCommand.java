package com.company.commands;

import com.company.Game;

public class ExitCommand extends Command {
    public ExitCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        System.out.println("Exit command executed");
    }
}
