package com.swop.command;

import com.swop.Snapshot;
import com.swop.blocks.BlockModel;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ExecuteCommand extends BlockrGameCommand {
    private Snapshot snapshot;
    private final BlockModel blockModel;
    private BlockModel nextProgramBlockModel;
    private List<BlockModel> allBlockModels;
    private List<BlockModel> program;

    public ExecuteCommand(BlockrGame blockrGame, BlockModel blockModel) {
        super(blockrGame);
        this.blockModel = blockModel;
    }

    @Override
    public void execute() {
        snapshot = blockrGame.getGameWorld().createSnapshot();
        nextProgramBlockModel = blockrGame.getProgramArea().getNextProgramBlockModel();
        allBlockModels = new ArrayList<>(blockrGame.getAllBlocksInPA());
        program = new LinkedList<>(blockrGame.getProgram());


    }

    @Override
    public void undo() {
        blockrGame.getGameWorld().restoreSnapshot(snapshot);
        blockrGame.getProgramArea().restore(allBlockModels, program, nextProgramBlockModel);
    }

}
