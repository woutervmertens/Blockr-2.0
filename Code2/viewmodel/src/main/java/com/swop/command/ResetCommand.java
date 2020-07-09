package com.swop.command;

import com.swop.BlockrGame;
import com.swop.Snapshot;
import com.swop.blocks.Block;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ResetCommand extends BlockrGameCommand{
    private Snapshot snapshot;
    private Block nextProgramBlock;
    private List<Block> allBlocks;
    private List<Block> program;

    public ResetCommand(BlockrGame blockrGame) {
        super(blockrGame);
    }

    @Override
    public void execute() {
        snapshot = blockrGame.getGameWorld().createSnapshot();
        nextProgramBlock = blockrGame.getProgramArea().getNextProgramBlock();
        allBlocks = new ArrayList<>(blockrGame.getAllBlocksInPA());
        program = new LinkedList<>(blockrGame.getProgram());
        blockrGame.resetEverything();
    }

    @Override
    public void undo() {
        blockrGame.getGameWorld().restoreSnapshot(snapshot);
        blockrGame.getProgramArea().restore(allBlocks,program,nextProgramBlock);
    }
}
