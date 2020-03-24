package com.swop;

import com.swop.blocks.Block;

import java.awt.*;

public class BlockrGame {
    private final ProgramArea programArea;
    private final int maxBlocks;

    public BlockrGame(int maxBlocks) {
        this.maxBlocks = maxBlocks;
        this.programArea = new ProgramArea();
    }

    public Point getBlockPosition(Block block) {
        return block.getPosition();
    }

    public void dropBlockInPAAt(Block draggedBlock, int x, int y) {
        programArea.dropBlockAt(draggedBlock, x, y);
    }

    public void dropBlockInPA(Block block) {
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

    public boolean isPaletteHidden(){return (maxBlocks - getNumbBlocksInPA()) <= 0;}

}
