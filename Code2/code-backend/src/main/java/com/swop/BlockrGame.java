package com.swop;

import com.swop.blocks.Block;

import java.awt.*;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class BlockrGame {
    private final ProgramArea programArea;
    private GameWorld gameWorld;
    private final GameWorldType gameWorldType;
    private final int maxBlocks;
    private final static AtomicReference<BlockrGame> instance = new AtomicReference<>();

    public BlockrGame(int maxBlocks,GameWorldType gameWorldType) {
        this.maxBlocks = maxBlocks;
        this.programArea = ProgramArea.getInstance();
        this.gameWorldType = gameWorldType;
        this.gameWorld = gameWorldType.createNewInstance();
    }

    public synchronized static BlockrGame getInstance(){
        if(instance.get() == null) throw new IllegalStateException("BlockrGame instance used before created.");
        return instance.get();
    }

    public List<Block> getProgram() {
        return programArea.getProgram();
    }

    public Point getBlockPosition(Block block) {
        return block.getPosition();
    }

    public void dropBlockInPA(Block block) {
        if (block == null) throw new IllegalArgumentException();
        programArea.dropBlock(block);
    }

    public void removeBlockFromPA(Block draggedBlock) {
        programArea.removeBlockFromPA(draggedBlock);
    }

    public void removeProgramBlock(Block draggedBlock) {
        programArea.removeProgramBlock(draggedBlock);
    }

    public void executeNext() {
        programArea.executeNext();
        // TODO
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
