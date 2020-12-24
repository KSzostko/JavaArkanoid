package com.company.commands;

import com.company.Game;

public class ShowAuthorsCommand extends Command {
    public ShowAuthorsCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        System.out.println("Show authors command executed");
    }
}
