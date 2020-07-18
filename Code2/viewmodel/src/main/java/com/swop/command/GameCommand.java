package com.swop.command;

import com.swop.GameController;
import com.swop.GameSnapshot;

public abstract class GameCommand implements ICommand {
    protected GameController gameController;
    private GameSnapshot snapshot;

    public GameCommand(GameController gameController) {
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
