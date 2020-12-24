package com.company.commands;

import com.company.Game;

public class GoBackCommand extends Command {
    public GoBackCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        System.out.println("Go back command executed");
    }
}
