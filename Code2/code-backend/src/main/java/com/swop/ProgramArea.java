package com.swop;

import com.swop.blocks.Block;

import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * A program area that handles drops of blocks in it for constructing program(s).
 * It has no notion of position or width or height.
 */
public class ProgramArea {
    private List<Block> program = new LinkedList<>();

    /**
     * Array recording all blocks currently present in program area
     */
    private List<Block> allBlocks = new ArrayList<>() {
    };

    private Block currentBlock;

    public List<Block> getAllBlocks() {
        return allBlocks;
    }

    /**
     * @pre the position of the block is inside the ui program area.
     */
    public void dropBlock(Block draggedBlock) {
        Point pos = draggedBlock.getPosition();
        draggedBlock.setPosition(pos);
        allBlocks.add(draggedBlock);
        // TODO: add eventually to program and handle eventual connections
    }

    public Block getCurrentBlock() {
        return currentBlock;
    }

    private void setCurrentBlock(Block first) {
        this.currentBlock = first;
    }

    public Block getBlockAt(int x, int y) {
        Optional<Block> found = getAllBlocks().stream().findAny().filter(block1 -> block1.getPosition().equals(new Point(x, y)));
        return found.orElse(null);

    }

    /**
     * Removes the draggedBlock from allBlocks and the program and all blocks that are connected beneath it are also removed from the program
     */
    public void removeBlock(Block draggedBlock) {
        if (program.contains(draggedBlock)) {
            program.subList(program.indexOf(draggedBlock), program.size()).clear();
        }
        allBlocks.remove(draggedBlock);
        // TODO: should the gapsizes be handled ?
        draggedBlock.terminate();
    }

    public void executeNext() {
        // TODO execute(gameworld)
    }

    public void reset() {
        try {
            setCurrentBlock(((LinkedList<Block>) program).getFirst());
        } catch (NoSuchElementException ignore) {
        }
    }
}
