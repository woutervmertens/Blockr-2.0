package com.swop.command;

import com.swop.GameWorld;
import com.swop.Snapshot;
import com.swop.blocks.Block;
import com.swop.blocks.Executable;
import com.swop.blocks.ExecuteType;

public class ExecuteCommand extends GameWorldCommand {
    private Snapshot snapshot;
    private final Block block;

    public ExecuteCommand(GameWorld gameWorld, Block block) {
        super(gameWorld);
        this.block = block;
    }

    @Override
    public void execute() {
        snapshot = gameWorld.createSnapshot();
        if (block.getExecuteType() != ExecuteType.NonExecutable) {
            ((Executable) block).execute();
        }
        // TODO: restore highlighted block
    }

    @Override
    public void undo() {
        gameWorld.restoreSnapshot(snapshot);
    }
}
