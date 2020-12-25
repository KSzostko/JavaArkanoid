package com.company.commands;

import com.company.Game;

public class ShowRankingCommand extends Command {
    public ShowRankingCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        game.showRanking();
    }
}
