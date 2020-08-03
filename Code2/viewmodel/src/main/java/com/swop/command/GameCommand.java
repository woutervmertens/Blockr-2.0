package com.swop.command;

import com.swop.CommandGameControllerFacade;
import com.swop.GameSnapshot;

public abstract class GameCommand implements ICommand {
    protected CommandGameControllerFacade gameController;
    private GameSnapshot snapshot;

    public GameCommand(CommandGameControllerFacade gameController) {
        this.gameController = gameController;
    }

    @Override
    public void execute() {
        snapshot = gameController.createSnapshot();
    }

    @Override
    public void undo() {
        gameController.restoreSnapshot(snapshot);
    }
}
