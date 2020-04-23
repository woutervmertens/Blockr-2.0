package com.swop.command;

import com.swop.Snapshot;
import com.swop.blocks.Block;
import com.swop.blocks.Executable;
import com.swop.blocks.ExecuteType;

public class ExecuteCommand extends GameWorldCommand{
    private Snapshot snapshot;
    private Block block;
    public ExecuteCommand(Block block){
        this.block = block;
    }

    @Override
    public void execute() {
        //TODO: highlight?
        snapshot = gameWorld.createSnapshot();
        if(block.getExecuteType() != ExecuteType.NonExecutable) {
            ((Executable) block).execute();
        }
    }

    @Override
    public void undo() {
        gameWorld.restoreSnapshot(snapshot);
    }
}
