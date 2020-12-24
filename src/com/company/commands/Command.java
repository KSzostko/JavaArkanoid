package com.company.commands;

import com.company.Game;

public abstract class Command {
    protected Game game;

    public Command(Game game) {
        this.game = game;
    }

    public abstract void execute();
}
