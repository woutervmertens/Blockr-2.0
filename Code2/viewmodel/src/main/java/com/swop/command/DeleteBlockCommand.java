package com.swop.command;

import com.swop.blocks.BlockModel;

import java.awt.*;

public class DeleteBlockCommand extends ProgramAreaCommand {
    private final BlockModel blockModel;
    private final Point previousPosition;

    public DeleteBlockCommand(ProgramArea programArea, BlockModel blockModel) {
        super(programArea);
        this.blockModel = blockModel;
        previousPosition = blockModel.getPreviousDropPosition();
    }

    @Override
    public void execute() {
        programArea.removeBlockFromPA(blockModel);
    }

    @Override
    public void undo() {
        if (previousPosition != null) blockModel.setPosition(previousPosition);
        programArea.dropBlock(blockModel);
    }
}
