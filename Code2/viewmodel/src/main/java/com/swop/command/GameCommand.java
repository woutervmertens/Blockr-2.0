package com.swop.command;

import com.swop.CommandGameControllerFacade;
import com.swop.GameSnapshot;

/**
 * The base for every command.
 */
public abstract class GameCommand implements ICommand {
    protected CommandGameControllerFacade gameController;
    private GameSnapshot snapshot;

    public GameCommand(CommandGameControllerFacade gameController) {
        this.gameController = gameController;
    }

    /**
     * Creates a snapshot of all window elements
     */
    @Override
    public void execute() {
        snapshot = gameController.createSnapshot();
    }

    /**
     * Restores the snapshot of all window elements
     */
    @Override
    public void undo() {
        gameController.restoreSnapshot(snapshot);
    }
}
