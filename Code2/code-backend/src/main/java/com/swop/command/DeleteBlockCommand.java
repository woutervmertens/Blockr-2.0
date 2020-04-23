package com.swop.command;

import com.swop.blocks.Block;

import java.awt.*;

public class DeleteBlockCommand extends ProgramAreaCommand {
    private final Block block;
    private final Point previousPosition;

    public DeleteBlockCommand(Block block){
        this.block = block;
        previousPosition = block.getPreviousDropPosition();
    }

    @Override
    public void execute() {
        programArea.removeBlockFromPA(block);
    }

    @Override
    public void undo() {
        block.setPosition(previousPosition);
        programArea.dropBlock(block);
    }
}
