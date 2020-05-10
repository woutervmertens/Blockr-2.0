package com.swop;

import com.swop.blocks.*;

import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * A program area that handles drops of blocks in it for constructing program(s).
 * It has no notion of position or width or height.
 */
public class ProgramArea implements PushBlocks {

    private final int radius = 10;  // Radius for connections
    /**
     * List recording all the blocks that belong to the current program of this program area WITHOUT nested blocks.
     * Nested blocks (inside StatementBlocks) should be accessed using ''
     */
    private final List<Block> program = new LinkedList<>();
    /**
     * List recording all blocks currently present in program area
     */
    private final List<Block> allBlocks = new ArrayList<>();
    private Block currentBlock;

    /**
     * @param b Point1
     * @param p Point2
     * @return Returns the distance between the two given points.
     */
    private static int getDistance(Point b, Point p) {
        return (int) Math.sqrt((p.getX() - b.getX()) * (p.getX() - b.getX()) + (p.getY() - b.getY()) * (p.getY() - b.getY()));
    }

    public List<Block> getAllBlocks() {
        return allBlocks;
    }

    public List<Block> getProgram() {
        return program;
    }

    public void dropBlockIn(Block draggedBlock, Point position) {
        draggedBlock.setPosition(position);
        dropBlock(draggedBlock);
    }

    public void dropBlock(Block draggedBlock) {
        if (reasonToReset(draggedBlock)) return;

        // 1) Handle Connection
        handleConnections(draggedBlock);

        // 2) Push program blocks if dragged block was added to a statement body
        // TODO: fix this with some pattern to share information about the program
        pushProgramBlocks(draggedBlock);

        System.out.println("Program has " + getProgram().size() + " blocks !");
    }

    // TODO: 11/05/2020 better name? 
    private boolean reasonToReset(Block draggedBlock) {
        if (!allBlocks.contains(draggedBlock)) allBlocks.add(draggedBlock);
        resetProgramExecution();
        if (allBlocks.size() == 1) {
            program.add(draggedBlock);
            resetProgramExecution();
            return true;
        }
        return false;
    }

    // TODO: 11/05/2020 Better name? 
    private void pushProgramBlocks(Block draggedBlock) {
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
    }


    /**
     * Handle eventual connections of close blocks for the given draggedblock.
     */
    private void handleConnections(Block draggedBlock) {
        if (!(draggedBlock instanceof ConditionBlock)) handleVerticalConnections(draggedBlock);
        else handleHorizontalConnections(draggedBlock);
    }

    private void handleVerticalConnections(Block draggedBlock) {
        assert !(draggedBlock instanceof ConditionBlock);
        Block closeBlock;
        // 1) plug
        closeBlock = getBlockWithPlugForBlockWithinRadius(draggedBlock, radius);
        if (closeBlock != null) {
            plug(draggedBlock, closeBlock);
        } else {
            // 2) socket
            closeBlock = getBlockWithSocketForBlockWithinRadius(draggedBlock, radius);
            if (closeBlock != null) {
                socket(draggedBlock, closeBlock);
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
    }

    // TODO: 11/05/2020 Better name?
    private void plug(Block draggedBlock, Block closeBlock) {
        if (program.contains(closeBlock)) {
            addProgramBlockAfter(draggedBlock, closeBlock);
        } else if (closeBlock.getParentStatement() != null) {
            closeBlock.getParentStatement().addBodyBlockAfter(draggedBlock, closeBlock);
        }
        draggedBlock.setPosition(getConnectionPoint(draggedBlock, closeBlock));
    }

    // TODO: 11/05/2020 better name?
    private void socket(Block draggedBlock, Block closeBlock) {
        if (program.contains(closeBlock)) {
            addProgramBlockBefore(draggedBlock, closeBlock);
        } else if (closeBlock.getParentStatement() != null) {
            closeBlock.getParentStatement().addBodyBlockBefore(draggedBlock, closeBlock);
        }
    }

    private void handleHorizontalConnections(Block draggedBlock) {
        assert draggedBlock instanceof ConditionBlock;
        Block closeBlock;
        closeBlock = getStatementBlockConditionPlugWithinRadius(draggedBlock, radius);
        if (closeBlock != null) {
            draggedBlock.setPosition(((StatementBlock) closeBlock).getConditionPlugPosition());
            ((StatementBlock) closeBlock).addConditionBlock((ConditionBlock) draggedBlock);
        } else {
            closeBlock = getConditionBlockConditionPlugWithinRadius(draggedBlock, radius);
            if (closeBlock != null) {
                draggedBlock.setPosition(closeBlock.getPlugPosition());
                StatementBlock parent = closeBlock.getParentStatement();
                parent.addConditionBlock((ConditionBlock) draggedBlock);
            }
        }
    }

    /**
     * @return Returns current block from the program area
     */
    public Block getCurrentBlock() {
        return currentBlock;
    }

    /**
     * Sets the given block as the current block
     *
     * @param first given block
     */
    private void setCurrentBlock(Block first) {
        this.currentBlock = first;
    }

    /**
     * Sets the next block as the current block.
     */
    public void setNextCurrentBlock() {
        int i = program.indexOf(currentBlock);
        if (i + 1 < program.size()) {
            setCurrentBlock(program.get(i + 1));
        } else {
            setCurrentBlock(null);
        }
    }

    /**
     * @return returns the block at the given position (x,y) if that block exists otherwise null will be returned.
     */
    public Block getBlockAt(int x, int y) {
        Optional<Block> found = getAllBlocks().stream().filter(block1 -> block1.isPositionOn(x, y)).findAny();
        System.out.println(found);
        return found.orElse(null);
    }

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

            if (getDistance(uiBlock.getPlugPosition(), b.getSocketPosition()) <= radius) {
                return b;
            }

        }
        return null;
    }

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
     * @param block  given block
     * @param radius given radius
     * @return Returns the condition block condition plug within the given radius of the given block
     */
    private Block getConditionBlockConditionPlugWithinRadius(Block block, int radius) {
        for (Block b : getAllBlocks()) {
            if (b == block || !(b instanceof ConditionBlock))
                continue;

            if (getDistance(block.getSocketPosition(), b.getPlugPosition()) <= radius) {
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
        getProgram().remove(block);
        allBlocks.remove(block);
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
                pushUp(clickedBlock, parentStatement);
            }
        } else {
            clickedBlock.getParentStatement().removeConditionBlock((ConditionBlock) clickedBlock);
        }
        allBlocks.remove(clickedBlock);
        if (getProgram().contains(clickedBlock)) {
            removeProgramBlock(clickedBlock);
        }
    }

    // TODO: 11/05/2020 better name?
    private void pushUp(Block clickedBlock, StatementBlock parentStatement) {
        // 1) Remove the body and push all superior body-blocks up
        parentStatement.removeBodyBlock(clickedBlock);
        // 2) Push program up
        // 2.1) Find most superior program block
        while (parentStatement.getParentStatement() != null) {
            parentStatement = parentStatement.getParentStatement();
        }
        // 2.2) Push
        if (getProgram().contains(parentStatement)) {
            int distance = -clickedBlock.getHeight() - clickedBlock.getStep();
            if (clickedBlock instanceof StatementBlock)
                distance -= ((StatementBlock) clickedBlock).getGapSize();
            PushBlocks.pushFrom(program, program.indexOf(parentStatement) + 1, distance);
        }
    }

    /**
     * Resets the program area, first block will be current block
     */
    public void resetProgramExecution() {
        for (Block block : getProgram()) {
            if (block instanceof StatementBlock) {
                ((StatementBlock) block).resetExecution();
            }
        }
        try {
            setCurrentBlock(((LinkedList<Block>) program).getFirst());
        } catch (NoSuchElementException ignore) {
        }
    }
}
