package com.swop;

import com.swop.blocks.Block;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * A program area that handles drops of blocks in it for constructing program(s).
 *   It has no notion of position or width or height.
 */
public class ProgramArea {
    private List<Block> program = new LinkedList<>();

    /**
     * Array recording all blocks currently present in program area
     */
    private ArrayList<Block> allBlocks = new ArrayList<>() {};

    public ArrayList<Block> getAllBlocks() {
        return allBlocks;
    }

    /**
     * @pre the given position is inside the ui program area.
     */
    public void dropBlockAt(Block draggedBlock, int x, int y) {
        // TODO
    }

    public Block getCurrentBlock() {
        // TODO
        return null;
    }

    public Block getBlockAt(int x, int y) {
        for (Block block: getAllBlocks()) {
            // TODO: if (block.isPositionOn()
        }
        return null;
    }

    public void removeBlock(Block draggedBlock) {
        allBlocks.remove(draggedBlock);
        program.remove(draggedBlock);
        // TODO: remove all the rest under the dragged block as well from the program
    }

    public void executeNext() {
        // TODO
    }

    public void reset() {
        //TODO
    }
}
