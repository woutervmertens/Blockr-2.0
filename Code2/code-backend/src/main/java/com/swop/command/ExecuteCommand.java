package com.swop.command;

import com.swop.Snapshot;
import com.swop.blocks.ActionBlock;

public class ExecuteCommand extends GameWorldCommand{
    private Snapshot snapshot;
    private ActionBlock actionBlock;
    public ExecuteCommand(ActionBlock actionBlock){
        this.actionBlock = actionBlock;
    }

    @Override
    public void execute() {
        snapshot = gameWorld.createSnapshot();
        actionBlock.execute();
    }

    @Override
    public void undo() {
        gameWorld.restoreSnapshot(snapshot);
    }
}
