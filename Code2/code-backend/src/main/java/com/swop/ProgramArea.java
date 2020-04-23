package com.swop;

import com.swop.blocks.*;

import java.awt.*;
import java.util.List;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * A program area that handles drops of blocks in it for constructing program(s).
 * It has no notion of position or width or height.
 */
public class ProgramArea implements PushBlocks {
    private final static AtomicReference<ProgramArea> instance = new AtomicReference<>();

    private final int radius = 10;  // Radius for connections
    /**
     * List recording all the blocks that belong to the current program of this program area WITHOUT nested blocks.
     * Nested blocks (inside StatementBlocks) should be accessed using ''
     */
    private List<Block> program = new LinkedList<>();
    /**
     * List recording all blocks currently present in program area
     */
    private List<Block> allBlocks = new ArrayList<>();
    private Block currentBlock;

    public List<Block> getAllBlocks() {
        return allBlocks;
    }

    public List<Block> getProgram() {
        return program;
    }

    public synchronized static ProgramArea getInstance() {
        if (instance.get() == null) instance.set(new ProgramArea());
        return instance.get();
    }

    // TODO: remove method ?
    public void dropBlockIn(Block draggedBlock, Point position) {
        draggedBlock.setPosition(position);
        dropBlock(draggedBlock);
    }

    public void dropBlock(Block draggedBlock) {
        if (!allBlocks.contains(draggedBlock)) allBlocks.add(draggedBlock);
        resetProgramExecution();
        if (allBlocks.size() == 1) {
            program.add(draggedBlock);
            resetProgramExecution();
            return;
        }

        // 1) Find close block and connection point
        Block closeBlock;
        if (!(draggedBlock instanceof ConditionBlock)) {
            // 1) plug
            closeBlock = getBlockWithPlugForBlockWithinRadius(draggedBlock, radius);
            if (closeBlock != null) {
                if (program.contains(closeBlock)) {
                    addProgramBlockAfter(draggedBlock, closeBlock);
                } else if (closeBlock.getParentStatement() != null) {
                    closeBlock.getParentStatement().addBodyBlockAfter(draggedBlock, closeBlock);
                }
                draggedBlock.setPosition(getConnectionPoint(draggedBlock, closeBlock));
            } else {
                // 2) socket
                closeBlock = getBlockWithSocketForBlockWithinRadius(draggedBlock, radius);
                if (closeBlock != null) {
                    if (program.contains(closeBlock)) {
                        addProgramBlockBefore(draggedBlock, closeBlock);
                    } else if (closeBlock.getParentStatement() != null) {
                        closeBlock.getParentStatement().addBodyBlockBefore(draggedBlock, closeBlock);
                    }
                    draggedBlock.setPosition(getConnectionPoint(draggedBlock, closeBlock));
                } else {
                    // 3) statement body
                    closeBlock = getStatementBlockBodyPlugWithinRadius(draggedBlock, radius);
                    if (closeBlock != null) {
                        draggedBlock.setPosition(((StatementBlock) closeBlock).getBodyPlugPosition());
                        ((StatementBlock) closeBlock).insertBodyBlockAtIndex(draggedBlock, 0);
                    }
                }
            }
        } else {
            // 4) statement condition
            closeBlock = getStatementBlockConditionPlugWithinRadius(draggedBlock, radius);
            if (closeBlock != null) {
                draggedBlock.setPosition(((StatementBlock) closeBlock).getConditionPlugPosition());
                ((StatementBlock) closeBlock).addConditionBlock((ConditionBlock) draggedBlock);
            }
            // TODO: connect condition to other conditions
        }

        // 2) Push program blocks if dragged block was added to a statement body
        if (draggedBlock.getParentStatement() != null) {
            Block currentBlock = draggedBlock;
            while (currentBlock.getParentStatement() != null) {
                currentBlock = currentBlock.getParentStatement();
            }

            if (getProgram().contains(currentBlock)) {
                // Now currentBlock is a block from the program
                int distance = draggedBlock.getHeight() + draggedBlock.getStep();
                if (draggedBlock instanceof StatementBlock) distance += ((StatementBlock) draggedBlock).getGapSize();
                PushBlocks.pushFrom(getProgram(), getProgram().indexOf(currentBlock) + 1, distance);
            }
        }

        System.out.println("Program has " + getProgram().size() + " blocks !");
    }

    /**
     * @return Returns current block from the program area
     */
    public Block getCurrentBlock() {
        return currentBlock;
    }

    /**
     * Sets the next block as the current block
     */
    public void setNextCurrentBlock() {
        int i = program.indexOf(currentBlock);
        Block b = (i + 1 < program.size()) ? program.get(i + 1) : null;
        setCurrentBlock(b);
    }

    /**
     * Sets the givven block as the current block
     *
     * @param first given block
     */
    private void setCurrentBlock(Block first) {
        this.currentBlock = first;
    }

    /**
     * @param x
     * @param y
     * @return returns the block at the given position (x,y) if that block exists otherwise null will be returned.
     */
    public Block getBlockAt(int x, int y) {
        Optional<Block> found = getAllBlocks().stream().filter(block1 -> block1.isPositionOn(x, y)).findAny();
        System.out.println(found);
        return found.orElse(null);
    }

    /**
     * @param b Point1
     * @param p Point2
     * @return Returns the distance between the two given points.
     */
    private static int getDistance(Point b, Point p) {
        return (int) Math.sqrt((p.getX() - b.getX()) * (p.getX() - b.getX()) + (p.getY() - b.getY()) * (p.getY() - b.getY()));
    }

//    private static boolean isAbove(Block block1, Block block2) {
//        return block1.getPosition().y < block2.getPosition().y;
//    }

    /**
     * @param draggedBlock block that is dragged
     * @param closeBlock   closest block to the dragged block
     * @return Returns the connection point if precondition is valid
     * @pre Both blocks are close enough to each other for connection
     */
    public Point getConnectionPoint(Block draggedBlock, Block closeBlock) {
        if (draggedBlock.isUnder(closeBlock)) return closeBlock.getPlugPosition();
        else
            return new Point(closeBlock.getSocketPosition().x, closeBlock.getSocketPosition().y - draggedBlock.getHeight() - 10);
    }

    /**
     * @param block  given block
     * @param radius given radius
     * @return Returns the block with plug that is within the given radius of the given block
     */
    private Block getBlockWithPlugForBlockWithinRadius(Block block, int radius) {
        for (Block b : getAllBlocks()) {
            if (b == block || (block instanceof HorizontallyConnectable && !(b instanceof HorizontallyConnectable))
                    || (block instanceof VerticallyConnectable && !(b instanceof VerticallyConnectable)))
                continue;

            // TODO: maybe type cast with interfaces
            if (getDistance(block.getSocketPosition(), b.getPlugPosition()) <= radius) {
                return b;
            }

        }
        return null;
    }

    /**
     * @param uiBlock given block
     * @param radius  given radius
     * @return Returns the block with socket within the given radius of the given block
     */
    private Block getBlockWithSocketForBlockWithinRadius(Block uiBlock, int radius) {
        for (Block b : getAllBlocks()) {
            if (b == uiBlock || (uiBlock instanceof HorizontallyConnectable && !(b instanceof HorizontallyConnectable))
                    || (uiBlock instanceof VerticallyConnectable && !(b instanceof VerticallyConnectable)))
                continue;

            // TODO: maybe type cast with interfaces
            if (getDistance(uiBlock.getPlugPosition(), b.getSocketPosition()) <= radius) {
                return b;
            }

        }
        return null;
    }

    // TODO: connect to last body block fix

    /**
     * @param block  given block
     * @param radius given radius
     * @return Returns the statement block body plug within the given radius of the given block
     */
    private Block getStatementBlockBodyPlugWithinRadius(Block block, int radius) {
        for (Block b : getAllBlocks()) {
            if (b == block || !(b instanceof StatementBlock) /*|| !((StatementBlock) b).getBodyBlocks().isEmpty()*/)
                continue;

            if (getDistance(block.getSocketPosition(), ((StatementBlock) b).getBodyPlugPosition()) <= radius) {
                return b;
            }
        }
        return null;
    }

    // TODO: connect to last condition of the conditions of statement (add getConditionPlugWithinRadius() )

    /**
     * @param block  given block
     * @param radius given radius
     * @return Returns the statement block condition plug within the given radius of the given block
     */
    private Block getStatementBlockConditionPlugWithinRadius(Block block, int radius) {
        for (Block b : getAllBlocks()) {
            if (b == block || !(b instanceof StatementBlock) || !((StatementBlock) b).getConditions().isEmpty())
                continue;

            if (getDistance(block.getSocketPosition(), ((StatementBlock) b).getConditionPlugPosition()) <= radius) {
                return b;
            }
        }
        return null;
    }

    /**
     * Add given block to the program of this program area and
     * insert it in the program list after the given existing block (if not null).
     *
     * @param block         given block
     * @param existingBlock given existing block in program
     */
    private void addProgramBlockAfter(Block block, Block existingBlock) {
        assert program.contains(existingBlock);
        insertProgramBlockAtIndex(block, program.indexOf(existingBlock) + 1);
    }

    /**
     * Add given block to the program of this program area and
     * insert it in the program list before the given existing block (if not null).
     *
     * @param block         given block
     * @param existingBlock given existing block in program
     */
    private void addProgramBlockBefore(Block block, Block existingBlock) {
        assert program.contains(existingBlock);
        insertProgramBlockAtIndex(block, program.indexOf(existingBlock));
    }

    private void insertProgramBlockAtIndex(Block block, int index) {
        program.add(index, block);
        int distance = block.getHeight() + block.getStep();
        if (block instanceof StatementBlock) distance += ((StatementBlock) block).getGapSize();
        PushBlocks.pushFrom(program, index + 1, distance);
    }

    /**
     * @param block given block
     *              Remove the given block from the program of this program area.
     *              This does not mean that the given block is removed or outside the PA.
     * @pre getProgram().contains(block)
     */
    private void removeProgramBlock(Block block) {
        assert getProgram().contains(block);
        int index = program.indexOf(block);
        program.remove(block);

        int distance = -block.getHeight() - block.getStep();
        if (block instanceof StatementBlock) distance -= ((StatementBlock) block).getGapSize();
        PushBlocks.pushFrom(program, index, distance);

        // TODO: Correct method

        getProgram().remove(block);

        // TODO: remove from allBlocks as well ? Or is it already done ?
    }

    /**
     * Remove the clickedBlock from this program area
     *
     * @param clickedBlock block that's dragged
     */
    public void removeBlockFromPA(Block clickedBlock) {
        if (!(clickedBlock instanceof ConditionBlock)) {
            StatementBlock parentStatement = clickedBlock.getParentStatement();
            if (parentStatement != null) {
                // 1) Remove the body and push all superior body-blocks up
                parentStatement.removeBodyBlock(clickedBlock);
                // 2) Push program up
                // 2.1) Find most superior program block
                while (parentStatement.getParentStatement() != null) {
                    parentStatement = parentStatement.getParentStatement();
                }
                if (getProgram().contains(parentStatement)) {
                    int distance = -clickedBlock.getHeight() - clickedBlock.getStep();
                    if (clickedBlock instanceof StatementBlock)
                        distance -= ((StatementBlock) clickedBlock).getGapSize();
                    PushBlocks.pushFrom(program, program.indexOf(parentStatement) + 1, distance);
                }
            }

        } else {
            // TODO: remove ConditionBlock
        }

        allBlocks.remove(clickedBlock);
        if (getProgram().contains(clickedBlock)) {
            removeProgramBlock(clickedBlock);
            // TODO: check if this body is correct
        }
        // TODO: remove correctly (for statement block gaps etc.) --> inverse of drop
    }

    /**
     * Resets the program area, first block will be current block
     */
    public void resetProgramExecution() {
        try {
            setCurrentBlock(((LinkedList<Block>) program).getFirst());
        } catch (NoSuchElementException ignore) {
        }
    }
}
