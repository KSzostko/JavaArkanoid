package com.company.commands;

import com.company.Game;

public class StartLevelCommand extends Command {
    public StartLevelCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        System.out.println("Start level command executed");
    }
}
