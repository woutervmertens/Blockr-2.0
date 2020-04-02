package com.swop;

import com.swop.blocks.Block;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

/**
 * A program area that handles drops of blocks in it for constructing program(s).
 *   It has no notion of position or width or height.
 */
public class ProgramArea {
    private LinkedList<Block> program = new LinkedList<>();

    /**
     * Array recording all blocks currently present in program area
     */
    private ArrayList<Block> allBlocks = new ArrayList<>() {};

    private Block currentBlock;

    public ArrayList<Block> getAllBlocks() {
        return allBlocks;
    }

    /**
     * @pre the given position is inside the ui program area.
     */
    public void dropBlockAt(Block draggedBlock, int x, int y) {
        draggedBlock.setPosition(new Point(x,y));
    }

    public Block getCurrentBlock() {
        try {
            return program.getFirst();
        }catch (NullPointerException e){
            return null;
        }
    }
    private void setCurrentBlock(Block first) {
        this.currentBlock = first;
    }

    public Block getBlockAt(int x, int y) {
        Optional<Block> found =  getAllBlocks().stream().findAny().filter(block1 -> block1.getPosition().equals(new Point(x, y)));
        return found.orElse(null);

    }

    /**
     * Removes the draggedBlock from allBlocks and the program and all blocks that are connected beneath it are also removed from the program
     * @param draggedBlock
     */
    public void removeBlock(Block draggedBlock) {
        Block last = program.getLast();
        while (!( last == draggedBlock)){
            program.remove(last);
            last = program.getLast();
        }

        allBlocks.remove(draggedBlock);
        program.remove(draggedBlock);

    }

    public void executeNext() {
        // TODO execute(gameworld)
    }

    public void reset() {
        setCurrentBlock(program.getFirst());
    }
}
