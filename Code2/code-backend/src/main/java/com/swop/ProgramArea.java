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
     * Nested blocks (inside blocks with bodies) should be accessed using 'getNextBodyBlock()'
     */
    private final List<Block> program = new LinkedList<>();
    /**
     * List recording all blocks currently present in program area
     */
    private final List<Block> allBlocks = new ArrayList<>();
    private Block nextProgramBlock;

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

        allBlocks.add(draggedBlock);

        // 1) Handle Connection
        handleConnections(draggedBlock);

        // 2) Push program blocks if dragged block was added to a statement body
        pushProgramBlocksForBody(draggedBlock);

        if ((program.isEmpty() || program.get(0) == null) && !(draggedBlock instanceof FunctionDefinitionBlock) ){
            program.clear();
            program.add(getMostUpperBlock());
            nextProgramBlock = getMostUpperBlock();
        }





        System.out.println("Program has " + getProgram().size() + " blocks !");
    }

    /**
     * Push program blocks if the given dragged block was added to a statement body
     */
    private void pushProgramBlocksForBody(Block draggedBlock) {
        if (draggedBlock.getParentBlock() != null && !(draggedBlock instanceof ConditionBlock)) {

            Block currentBlock = draggedBlock;
            while (currentBlock.getParentBlock() != null) {
                currentBlock = currentBlock.getParentBlock();
            }
            if (getProgram().contains(currentBlock)) {
                // Now currentBlock is a block from the program
                int distance = draggedBlock.getHeight() + draggedBlock.getStep();
                if (draggedBlock instanceof BlockWithBody) distance += ((BlockWithBody) draggedBlock).getGapSize();
                PushBlocks.pushFrom(getProgram(), getProgram().indexOf(currentBlock) + 1, distance);
            }
        }
    }


    /**
     * Handle eventual connections of close blocks for the given draggedblock.
     */
    private void handleConnections(Block draggedBlock) {
        if (draggedBlock instanceof VerticallyConnectable) handleVerticalConnections(draggedBlock);
        if (draggedBlock instanceof HorizontallyConnectable) handleHorizontalConnections(draggedBlock);
    }

    /**
     * Connect the @param draggedBlock to the blocks in the Program Area in a vertical direction.
     */
    private void handleVerticalConnections(Block draggedBlock) {
        assert (draggedBlock instanceof VerticallyConnectable);
        Block closeBlock;
        // 1) plug
        closeBlock = getBlockWithPlugForBlockWithinRadius(draggedBlock, radius);
        if (closeBlock != null) {
            if ((program.isEmpty() || program.get(0) == null) && !(draggedBlock instanceof FunctionDefinitionBlock) ){
                program.clear();
                program.add(getMostUpperBlock());
                nextProgramBlock = getMostUpperBlock();
            }
            connectPlug(draggedBlock, closeBlock);
        } else {
            // 2) socket
            closeBlock = getBlockWithSocketForBlockWithinRadius(draggedBlock, radius);
            if (closeBlock != null) {
                if ((program.isEmpty() || program.get(0) == null) && !(draggedBlock instanceof FunctionDefinitionBlock) ){
                    program.clear();
                    program.add(getMostUpperBlock());
                    nextProgramBlock = getMostUpperBlock();
                }
                connectSocket(draggedBlock, closeBlock);
                draggedBlock.setPosition(getVerticalConnectionPoint(draggedBlock, closeBlock));
            } else {
                // 3) body
                closeBlock = getBlockWithBodyPlugWithinRadius(draggedBlock, radius);
                if (closeBlock != null) {
                    draggedBlock.setPosition(((BlockWithBody) closeBlock).getBodyPlugPosition());
                    ((BlockWithBody) closeBlock).insertBodyBlockAtIndex(draggedBlock, 0);
                }
            }
        }
    }

    /**
     * Connect the plugs of the two given blocks.
     * @param draggedBlock The Block that has just been added.
     * @param closeBlock The closest Block in the Program Area.
     */
    private void connectPlug(Block draggedBlock, Block closeBlock) {
        if (program.contains(closeBlock)) {
            addProgramBlockAfter(draggedBlock, closeBlock);
        } else if (closeBlock.getParentBlock() != null) {
            closeBlock.getParentBlock().addBodyBlockAfter(draggedBlock, closeBlock);
        }
        draggedBlock.setPosition(getVerticalConnectionPoint(draggedBlock, closeBlock));
    }

    /**
     * Connect the sockets of the two given blocks.
     * @param draggedBlock The Block that has just been added.
     * @param closeBlock The closest Block in the Program Area.
     */
    private void connectSocket(Block draggedBlock, Block closeBlock) {
        if (program.contains(closeBlock)) {
            addProgramBlockBefore(draggedBlock, closeBlock);
        } else if (closeBlock.getParentBlock() != null) {
            closeBlock.getParentBlock().addBodyBlockBefore(draggedBlock, closeBlock);
        }
    }

    /**
     * Connect the @param draggedBlock to the blocks in the Program Area in a horizontal direction.
     */
    private void handleHorizontalConnections(Block draggedBlock) {
        assert draggedBlock instanceof HorizontallyConnectable;
        if (draggedBlock instanceof ConditionBlock) {
            Block closeBlock;
            closeBlock = getStatementBlockConditionPlugWithinRadius(draggedBlock, radius);
            if (closeBlock != null) {
                draggedBlock.setPosition(((StatementBlock) closeBlock).getConditionPlugPosition());
                ((StatementBlock) closeBlock).addConditionBlock((ConditionBlock) draggedBlock);
            } else {
                closeBlock = getConditionBlockConditionPlugWithinRadius(draggedBlock, radius);
                if (closeBlock != null) {
                    draggedBlock.setPosition(((HorizontallyConnectable) closeBlock).getPlugPosition());
                    StatementBlock parent = (StatementBlock) closeBlock.getParentBlock();
                    parent.addConditionBlock((ConditionBlock) draggedBlock);
                }
            }
        }
    }

    /**
     * @return  Returns the next to be executed block.
     *          This is not necessarily the next to be executed block, rather it is the next in the list (can be a statement).
     */
    public Block getNextProgramBlock() {
        return nextProgramBlock;
    }

    /**
     * Sets the next block to the next block from the program list, otherwise to null.
     * This is not necessarily the next to be executed block, rather it is the next in the list (can be a statement).
     */
    public void setNextProgramBlock() {
        if (getProgram().isEmpty()) {
            setNextProgramBlock(null);
            return;
        }
        if (getNextProgramBlock() == null) {
            return;
        } else if (!getNextProgramBlock().isBusy()) {
            // 1) Find the correct next index (by skipping illegal statements and functioncallblocks)
            int i = program.indexOf(nextProgramBlock) + 1;
            Block next = i < program.size() ? program.get(i) : null;
            if (next instanceof StatementBlock && (!((StatementBlock) next).isConditionValid() || ((StatementBlock) next).getBodyBlocks().isEmpty()))
                i += 1;
            else if (next instanceof FunctionCallBlock && ((FunctionCallBlock) next).getDefinitionBlock().getBodyBlocks().isEmpty())
                i += 1;

            // 2) Set the next program block, and if it was already the last set to null
            if (i < program.size()) {
                setNextProgramBlock(program.get(i));
            } else {
                setNextProgramBlock(null);
            }
        }
    }

    /**
     * Sets the next block to the first block from the program list if the program list is not empty, otherwise to null.
     */
    public void resetNextProgramBlock() {
        if (getProgram().isEmpty()) {
            setNextProgramBlock(null);
            return;
        }
        setNextProgramBlock(getProgram().get(0));
    }

    private void setNextProgramBlock(Block block) {
        this.nextProgramBlock = block;
    }

    /**
     * @param x Given x coordinate.
     * @param y Given y coordinate.
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
    private Point getVerticalConnectionPoint(Block draggedBlock, Block closeBlock) {
        if (!(draggedBlock instanceof VerticallyConnectable && closeBlock instanceof VerticallyConnectable))
            throw new IllegalArgumentException("Not both blocks are vertically connectable");

        if (draggedBlock.isUnder(closeBlock)) return ((VerticallyConnectable) closeBlock).getPlugPosition();
        else if(draggedBlock instanceof StatementBlock){
            return new Point(((VerticallyConnectable) closeBlock).getSocketPosition().x,
                    ((VerticallyConnectable) closeBlock).getSocketPosition().y - draggedBlock.getHeight() - 10 - ((StatementBlock)draggedBlock).getGapSize());
        }
        else{
            return new Point(((VerticallyConnectable) closeBlock).getSocketPosition().x,
                    ((VerticallyConnectable) closeBlock).getSocketPosition().y - draggedBlock.getHeight() - 10 );
        }
    }

    /**
     * @param block  given block
     * @param radius given radius
     * @return Returns the block with plug that is within the given radius of the given block
     */
    private Block getBlockWithPlugForBlockWithinRadius(Block block, int radius) {
        for (Block b : getAllBlocks()) {
            if (b == block) continue;

            if (b instanceof HorizontallyConnectable && block instanceof HorizontallyConnectable) {
                if (getDistance(((HorizontallyConnectable) block).getSocketPosition(), ((HorizontallyConnectable) b).getPlugPosition()) <= radius) {
                    return b;
                }
            } else if (b instanceof VerticallyConnectable && block instanceof VerticallyConnectable) {
                if (getDistance(((VerticallyConnectable) block).getSocketPosition(), ((VerticallyConnectable) b).getPlugPosition()) <= radius) {
                    return b;
                }
            }
        }
        return null;
    }

    /**
     * @param block  given block
     * @param radius given radius
     * @return Returns the block with socket within the given radius of the given block
     */
    private Block getBlockWithSocketForBlockWithinRadius(Block block, int radius) {
        for (Block b : getAllBlocks()) {
            if (b == block) continue;

            if (b instanceof HorizontallyConnectable && block instanceof HorizontallyConnectable) {
                if (getDistance(((HorizontallyConnectable) block).getPlugPosition(), ((HorizontallyConnectable) b).getSocketPosition()) <= radius) {
                    return b;
                }
            } else if (b instanceof VerticallyConnectable && block instanceof VerticallyConnectable) {
                if (getDistance(((VerticallyConnectable) block).getPlugPosition(), ((VerticallyConnectable) b).getSocketPosition()) <= radius) {
                    return b;
                }
            }

        }
        return null;
    }

    /**
     * @param block  given block
     * @param radius given radius
     * @return Returns the statement block body plug within the given radius of the given block
     */
    private Block getBlockWithBodyPlugWithinRadius(Block block, int radius) {
        if (!(block instanceof VerticallyConnectable))
            throw new IllegalArgumentException("Block is not vertically connectable");
        for (Block b : getAllBlocks()) {
            if (b == block || !(b instanceof BlockWithBody) /*|| !((StatementBlock) b).getBodyBlocks().isEmpty()*/)
                continue;

            if (getDistance(((VerticallyConnectable) block).getSocketPosition(), ((BlockWithBody) b).getBodyPlugPosition()) <= radius) {
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
        if (!(block instanceof HorizontallyConnectable))
            throw new IllegalArgumentException("Block is not horizontally connectable");
        for (Block b : getAllBlocks()) {
            if (b == block || !(b instanceof StatementBlock) || !((StatementBlock) b).getConditions().isEmpty())
                continue;

            if (getDistance(((HorizontallyConnectable) block).getSocketPosition(), ((StatementBlock) b).getConditionPlugPosition()) <= radius) {
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
        if (!(block instanceof ConditionBlock)) throw new IllegalArgumentException();
        for (Block b : getAllBlocks()) {
            if (b == block || !(b instanceof ConditionBlock))
                continue;

            if (getDistance(((ConditionBlock) block).getSocketPosition(), ((ConditionBlock) b).getPlugPosition()) <= radius) {
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
            BlockWithBody parentBlock = clickedBlock.getParentBlock();
            if (parentBlock != null) {
                pushUpBodyAndProgramAfterClickOn(parentBlock, clickedBlock);
            }
            // fixes bug without problems
            if (getProgram().size() == 0 && getAllBlocks().size() > 0){
                setNextProgramBlock(getMostUpperBlock());
                if (getNextProgramBlock() != null) {
                    program.add(getNextProgramBlock());
                }
            }
        } else if (clickedBlock.getParentBlock() != null) {
            ((StatementBlock) clickedBlock.getParentBlock()).removeConditionBlock((ConditionBlock) clickedBlock);
        }
        allBlocks.remove(clickedBlock);
        if (getProgram().contains(clickedBlock)) {
            removeProgramBlock(clickedBlock);
        }
    }

    /**
     * Gets the block with the smallest Y value in the Program Area that is not a condition or a function definition.
     * @return A Block object.
     */
    private Block getMostUpperBlock() {
        Optional<Block> min = getAllBlocks().stream().filter(block -> !(block instanceof ConditionBlock) && !(block instanceof FunctionDefinitionBlock) && (block.getParentBlock() == null)).min(Comparator.comparingInt(block -> (int) block.getPosition().getY()));
        return min.orElse(null);
    }

    /**
     * Push up the body of the given parentBlock and the program due to a click on the given clicked block.
     *
     * @param clickedBlock Block clicked.
     * @param parentBlock Block parent.
     */
    private void pushUpBodyAndProgramAfterClickOn(BlockWithBody parentBlock, Block clickedBlock) {
        // 1) Remove the body and push all superior body-blocks up
        parentBlock.removeBodyBlock(clickedBlock);
        // 2) Push program up
        if (parentBlock instanceof FunctionDefinitionBlock) return;
        // 2.1) Find most superior program block
        while (parentBlock.getParentBlock() != null) {
            parentBlock = parentBlock.getParentBlock();
        }
        // 2.2) Push
        if (getProgram().contains(parentBlock)) {
            int distance = -clickedBlock.getHeight() - clickedBlock.getStep();
            if (clickedBlock instanceof StatementBlock)
                distance -= ((StatementBlock) clickedBlock).getGapSize();
            PushBlocks.pushFrom(program, program.indexOf(parentBlock) + 1, distance);
        }
    }

    /**
     * Resets the program area, first block will be current block.
     */
    public void resetProgramExecution() {
        for (Block block : getAllBlocks()) if (block instanceof BlockWithBody) ((BlockWithBody) block).resetExecution();
        if (!program.isEmpty()) resetNextProgramBlock();
        ExecutedBlocks.getInstance().clear();
    }

    /**
     * Set the Program Area Blocks, Program Blocks and next-to-be-executed Block.
     *
     * @param allBlocks List of Block elements to fill the Program Area Blocks.
     * @param program List of Block elements to fill the Program Blocks.
     * @param nextProgramBlock Block.
     */
    public void restore(List<Block> allBlocks, List<Block> program, Block nextProgramBlock){
        this.allBlocks.clear();
        this.allBlocks.addAll(allBlocks);
        this.program.clear();
        this.program.addAll(program);
        setNextProgramBlock(nextProgramBlock);

        try {
            // Reset next program block
            Block parent = ExecutedBlocks.getInstance().pop();
            while (parent.getParentBlock() != null) {
                parent.getParentBlock().setNextBodyBlock(parent);
                parent = parent.getParentBlock();
            }
            if (getProgram().contains(parent)) setNextProgramBlock(parent);
        } catch (EmptyStackException ignore) {

        }

    }
}
