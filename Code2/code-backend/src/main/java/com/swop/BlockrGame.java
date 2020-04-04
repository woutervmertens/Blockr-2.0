package com.swop;

import com.swop.blocks.Block;

import java.awt.*;
import java.util.List;

public class BlockrGame {
    private final ProgramArea programArea;
    private GameWorld gameWorld;
    private final GameWorldType gameWorldType;
    private final int maxBlocks;

    public BlockrGame(int maxBlocks,GameWorldType gameWorldType) {
        this.maxBlocks = maxBlocks;
        this.programArea = new ProgramArea();
        this.gameWorldType = gameWorldType;
        this.gameWorld = gameWorldType.createNewInstance();
    }

    public Point getBlockPosition(Block block) {
        return block.getPosition();
    }

    public void dropBlockInPA(Block block) {
        if (block == null) throw new IllegalArgumentException();
        programArea.dropBlock(block);
    }

    public void removeBlockFromPA(Block draggedBlock) {
        programArea.removeBlock(draggedBlock);
    }


    public void executeNext() {
        // TODO
        programArea.executeNext();
    }

    public Block getCurrentBlock() {
        return programArea.getCurrentBlock();
    }

    public void resetProgramExecution() {
        programArea.reset();
        gameWorld = gameWorldType.createNewInstance();
    }

    public int getNumbBlocksInPA() {
        return programArea.getAllBlocks().size();
    }

    public List<Block> getAllBlocksInPA(){return programArea.getAllBlocks();}

    public Block getBlockInPaAt(int x, int y) {
        return programArea.getBlockAt(x,y);
    }

    public boolean isPaletteHidden(){return (maxBlocks - getNumbBlocksInPA()) <= 0;}

    public GameWorld getGameWorld() {
        return gameWorld;
    }

}
