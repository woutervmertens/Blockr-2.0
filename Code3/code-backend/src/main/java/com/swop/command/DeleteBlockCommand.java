package com.swop.command;

import com.swop.ProgramArea;
import com.swop.blocks.Block;

import java.awt.*;

public class DeleteBlockCommand extends ProgramAreaCommand {
    private final Block block;
    private final Point previousPosition;

    public DeleteBlockCommand(ProgramArea programArea, Block block) {
        super(programArea);
        this.block = block;
        previousPosition = block.getPreviousDropPosition();
    }

    @Override
    public void execute() {
        programArea.removeBlockFromPA(block);
    }

    @Override
    public void undo() {
        if (previousPosition != null) block.setPosition(previousPosition);
        programArea.dropBlock(block);
    }
}
