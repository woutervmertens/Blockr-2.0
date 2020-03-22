package com.swop;

import com.swop.blocks.Block;

import java.awt.*;

public class BlockrGame {
    private final ProgramArea programArea;

    public BlockrGame() {
        this.programArea = new ProgramArea();
    }

    public Point getBlockPosition(Block block) {
        return block.getPosition();
    }

    public void dropBlockInPAAt(Block draggedBlock, int x, int y) {
        programArea.dropBlockAt(draggedBlock, x, y);
    }

    public void dropBlockInPA() {
        // TODO
    }

    public Point getCharacterPos() {
        // TODO
        return null;
    }

    public void executeNext() {
        // TODO
    }

    public Block getCurrentBlock() {
        return programArea.getCurrentBlock();
    }

    public void resetProgramExecution() {
        // TODO: reset program
        // TODO: reset gameWorld
    }

    public int getNumbBlocksInPA() {
        return programArea.getAllBlocks().length;
    }

    public Block getBlockInPaAt(int x, int y) {
        return programArea.getBlockAt(x,y);
    }

}
