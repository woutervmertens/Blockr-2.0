package com.swop.command;

import com.swop.blocks.Block;

import java.awt.*;

public class DropBlockCommand extends ProgramAreaCommand {
    private final Block block;
    private final Point position;
    private final Point previousPosition;

    public DropBlockCommand(Block block) {
        this.block = block;
        this.position = block.getPosition();
        previousPosition = block.getPreviousDropPosition();
    }

    @Override
    public void execute() {
        programArea.dropBlockIn(block, position);
    }

    @Override
    public void undo() {
        try {
            // block.setPreviousDropPosition(block.getPosition());
            block.setPosition(previousPosition);
            programArea.dropBlock(block);
        } catch (IllegalArgumentException e) {
            programArea.removeBlockFromPA(block);
        }
    }
}
