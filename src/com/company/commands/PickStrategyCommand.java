package com.company.commands;

import com.company.Game;
import com.company.PositionStrategy;

public class PickStrategyCommand extends Command {
    private PositionStrategy strategy;

    public PickStrategyCommand(Game game, PositionStrategy strategy) {
        super(game);
        this.strategy = strategy;
    }

    @Override
    public void execute() {
        game.setStrategy(strategy);
        game.startLevel();
    }
}
