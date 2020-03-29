package com.swop;

import com.swop.blocks.Block;
import com.swop.worldElements.Character;
import com.swop.worldElements.GameWorld;
import com.swop.worldElements.Square;

import java.awt.*;
import java.util.ArrayList;

public class BlockrGame {
    private final ProgramArea programArea;
    private final GameWorld gameWorld;
    private final int maxBlocks;

    public BlockrGame(int maxBlocks,int[] gridDimension, int[] goalPosition) {
        this.maxBlocks = maxBlocks;
        this.programArea = new ProgramArea();
        this.gameWorld = new GameWorld(gridDimension,goalPosition);
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

    public Character getCharacter() {
        return gameWorld.getCharacter();
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
        gameWorld.reset();
    }

    public int getNumbBlocksInPA() {
        return programArea.getAllBlocks().size();
    }

    public ArrayList<Block> getAllBlocksInPA(){return programArea.getAllBlocks();}

    public Block getBlockInPaAt(int x, int y) {
        return programArea.getBlockAt(x,y);
    }

    public boolean isPaletteHidden(){return (maxBlocks - getNumbBlocksInPA()) <= 0;}

    public void removeBlockFromPA(Block draggedBlock) {
        programArea.removeBlock(draggedBlock);
        // TODO: adjust program
    }

    public Square[][] getGameWorldGrid() {
        return gameWorld.getGrid();
    }
}
