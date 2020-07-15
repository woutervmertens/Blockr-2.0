package com.swop.command;

import com.swop.BlockrGame;
import com.swop.Snapshot;
import com.swop.blocks.BlockModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ResetCommand extends BlockrGameCommand{
    private Snapshot snapshot;
    private BlockModel nextProgramBlockModel;
    private List<BlockModel> allBlockModels;
    private List<BlockModel> program;

    public ResetCommand(BlockrGame blockrGame) {
        super(blockrGame);
    }

    @Override
    public void execute() {
        snapshot = blockrGame.getGameWorld().createSnapshot();
        nextProgramBlockModel = blockrGame.getProgramArea().getNextProgramBlockModel();
        allBlockModels = new ArrayList<>(blockrGame.getAllBlocksInPA());
        program = new LinkedList<>(blockrGame.getProgram());
        blockrGame.resetEverything();
    }

    @Override
    public void undo() {
        blockrGame.getGameWorld().restoreSnapshot(snapshot);
        blockrGame.getProgramArea().restore(allBlockModels,program, nextProgramBlockModel);
    }
}
