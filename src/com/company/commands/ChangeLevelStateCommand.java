package com.company.commands;
import com.company.Game;

public class ChangeLevelStateCommand extends Command {
    public ChangeLevelStateCommand(Game game) {
        super(game);
    }

    @Override
    public void execute() {
        game.undo(true);
    }
}