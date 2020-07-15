package com.swop.command;

import com.swop.blocks.BlockModel;

import java.awt.*;

public class DropBlockCommand extends ProgramAreaCommand {
    private final BlockModel blockModel;
    private final Point position;
    private final Point previousPosition;

    public DropBlockCommand(ProgramArea programArea, BlockModel blockModel, int x, int y) {
        super(programArea);
        this.blockModel = blockModel;
        this.position = new Point(x,y);
        previousPosition = blockModel.getPreviousDropPosition();
    }

    @Override
    public void execute() {
        programArea.dropBlockIn(blockModel, position);
    }

    @Override
    public void undo() {
        try {
            blockModel.setPosition(previousPosition);
            programArea.dropBlock(blockModel);
        } catch (IllegalArgumentException e) {
            programArea.removeBlockFromPA(blockModel);
        }
    }
}
