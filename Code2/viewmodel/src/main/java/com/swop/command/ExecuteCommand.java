package com.swop.command;

import com.swop.BlockrGame;
import com.swop.Snapshot;
import com.swop.blocks.Block;
import com.swop.blocks.Executable;
import com.swop.blocks.ExecuteType;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExecuteCommand extends BlockrGameCommand {
    private Snapshot snapshot;
    private final Block block;
    private Block nextProgramBlock;
    private List<Block> allBlocks;
    private List<Block> program;

    public ExecuteCommand(BlockrGame blockrGame, Block block) {
        super(blockrGame);
        this.block = block;
    }

    @Override
    public void execute() {
        snapshot = blockrGame.getGameWorld().createSnapshot();
        nextProgramBlock = blockrGame.getProgramArea().getNextProgramBlock();
        allBlocks = new ArrayList<>(blockrGame.getAllBlocksInPA());
        program = new LinkedList<>(blockrGame.getProgram());

        if (block.getExecuteType() != ExecuteType.NonExecutable) {
            ((Executable) block).execute();
        }
        if (nextProgramBlock != null && !nextProgramBlock.isBusy()) {
            blockrGame.getProgramArea().setNextProgramBlock();
        }
    }

    @Override
    public void undo() {
        blockrGame.getGameWorld().restoreSnapshot(snapshot);
        blockrGame.getProgramArea().restore(allBlocks, program, nextProgramBlock);
    }

}
