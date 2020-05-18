package com.swop.command;

import com.swop.GameWorld;
import com.swop.Snapshot;
import com.swop.blocks.Block;
import com.swop.blocks.Executable;
import com.swop.blocks.ExecuteType;

public class ExecuteCommand extends GameWorldCommand {
    private Snapshot snapshot;
    private final Block block;
//    TODO: fix het next block issue (highlight in frontend)
//    private final Block previouslyToBeExecutedBlock;
//    private final Block nextToBeExecutedBlock;

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
    }

    @Override
    public void undo() {
        gameWorld.restoreSnapshot(snapshot);
    }
}
