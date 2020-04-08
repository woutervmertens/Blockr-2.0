package com.swop;

import com.swop.blocks.*;

import java.awt.*;
import java.util.List;
import java.util.*;

/**
 * A program area that handles drops of blocks in it for constructing program(s).
 * It has no notion of position or width or height.
 */
public class ProgramArea {
    private final int radius = 15;  // Radius for connections
    private List<Block> program = new LinkedList<>();
    /**
     * Array recording all blocks currently present in program area
     */
    private List<Block> allBlocks = new ArrayList<>();
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
        if (!allBlocks.contains(draggedBlock)) allBlocks.add(draggedBlock);

        // TODO: add eventually to program and handle eventual connections

        Point dropPos = draggedBlock.getPosition();
        Block closeBlock = null;
        Point connectionPoint = null;

        int type;

        if (!(draggedBlock instanceof ConditionBlock)) {
            // 1) plug
            closeBlock = getBlockWithPlugForBlockWithinRadius(draggedBlock, radius);
            type = 1;
            if (closeBlock == null) {
                // 2) socket
                closeBlock = getBlockWithSocketForBlockWithinRadius(draggedBlock, radius);
                type = 2;
                if (closeBlock == null) {
                    // 3) statement body
                    closeBlock = getStatementBlockBodyPlugWithinRadius(draggedBlock, radius);
                    type = 3;
                }
            }
        } else {
            // 4) statement condition
            closeBlock = getStatementBlockConditionPlugWithinRadius(draggedBlock, radius);
            type = 4;
        }

        if (closeBlock != null) {
            switch (type) {
                case 1: //Plug
                    // TODO: draggedBlock.setParentStatement(closeBlock.getParentStatement());
                    // TODO: is it needed to record the parent statement ?
                    // TODO: should I break now ?
                case 2: //Socket
                    connectionPoint = getConnectionPoint(draggedBlock, closeBlock);
                    // TODO: executeProgramHandler.addBlockToProgramArea(draggedBlock, closeBlock);
                    break;
                case 3: //Statement body
                    connectionPoint = ((StatementBlock) closeBlock).getBodyPlugPosition();
                    ((StatementBlock) closeBlock).addBodyBlock((ActionBlock) draggedBlock);
                    break;
                case 4: //Statement condition
                    connectionPoint = ((StatementBlock) closeBlock).getConditionPlugPosition();
                    ((StatementBlock) closeBlock).addConditionBlock((ConditionBlock) draggedBlock);
                    break;
            }
            System.out.println("Close block: " + closeBlock);
            draggedBlock.setPosition(connectionPoint);
            // TODO:
//            if (draggedBlock.getParentStatement() != null) {
//                ((UIStatementBlock) draggedBlock.getParentStatement()).increaseGapSize(draggedBlock.getHeight() + 5);
//            }
        } else {
            // TODO: should this else block still exist ??

//            //TODO: create new program in backend
//            draggedBlock.setPosition(dropPos);
//            //Backend add block to current blockgroup
//            executeProgramHandler.addBlockToProgramArea(draggedBlock, null);
        }
//        uiProgramArea.addBlock(draggedBlock);

        // TODO: Reset the execution of the program bcs a new block was added

    }

    public Block getCurrentBlock() {
        return currentBlock;
    }

    private void setCurrentBlock(Block first) {
        this.currentBlock = first;
    }

    public Block getBlockAt(int x, int y) {
        Optional<Block> found = getAllBlocks().stream().filter(block1 -> block1.isPositionOn(x, y)).findAny();
        System.out.println(found);
        return found.orElse(null);
    }

    /**
     * Get the distance between two given points.
     *
     * @param b Point1
     * @param p Point2
     */
    private static int getDistance(Point b, Point p) {
        return (int) Math.sqrt((p.getX() - b.getX()) * (p.getX() - b.getX()) + (p.getY() - b.getY()) * (p.getY() - b.getY()));
    }

    /**
     * @pre Both blocks are close enough to each other for connection !
     */
    public Point getConnectionPoint(Block draggedBlock, Block closeBlock) {
        if (draggedBlock.isUnder(closeBlock)) return closeBlock.getPlugPosition();
        else
            return new Point(closeBlock.getSocketPosition().x, closeBlock.getSocketPosition().y - draggedBlock.getHeight() - 10);
    }

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
    private Block getStatementBlockBodyPlugWithinRadius(Block block, int radius) {
        for (Block b : getAllBlocks()) {
            if (b == block || !(b instanceof StatementBlock) || !((StatementBlock) b).getBodyBlocks().isEmpty())
                continue;

            if (getDistance(block.getSocketPosition(), ((StatementBlock) b).getBodyPlugPosition()) <= radius) {
                return b;
            }
        }
        return null;
    }

    // TODO: connect to last condition of the conditions of statement (add getConditionPlugWithinRadius() )
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
