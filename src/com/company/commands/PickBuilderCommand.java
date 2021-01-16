package com.company.commands;

import com.company.Game;
import com.company.LevelBuilder;

public class PickBuilderCommand extends Command {
    private LevelBuilder builder;

    public PickBuilderCommand(Game game, LevelBuilder builder) {
        super(game);
        this.builder = builder;
    }

    @Override
    public void execute() {
        game.setBuilder(builder);
        // choose strategy screen
    }
}
