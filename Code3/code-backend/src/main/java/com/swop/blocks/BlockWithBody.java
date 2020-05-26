package com.swop.blocks;

import com.swop.PushBlocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BlockWithBody extends Block implements Executable {
    protected List<Block> bodyBlocks = new ArrayList<>();
    private Block nextBodyBlock = null;
    private int gapSize;

    /**
     * Creates a block that can have a body.
     * @param position The position of the block.
     * @param width The width of the block.
     * @param height The height of the block.
     */
    protected BlockWithBody(Point position, int width, int height) {
        super(position, width, height);
    }

    @Override
    public void setPosition(Point position) {
        try {
            int dx = position.x - getPosition().x;
            int dy = position.y - getPosition().y;
            super.setPosition(position);
            for (Block bodyBlock : getBodyBlocks()) {
                bodyBlock.setPosition(new Point(bodyBlock.getPosition().x + dx, bodyBlock.getPosition().y + dy));
            }
        } catch (NullPointerException e) {
            super.setPosition(position);
        }
    }


    /**
     * Add the given block at a certain index in the body.
     *
     * @param block The block to add.
     * @param index The index.
     */
    public void insertBodyBlockAtIndex(Block block, int index) {
        // 1) Add to the body blocks of this block
        bodyBlocks.add(index, block);
        block.setParentBlock(this);

        // 2) Push all next body blocks down
        int distance = block.getHeight() + step;
        if (block instanceof StatementBlock) distance += ((StatementBlock) block).getGapSize();
        PushBlocks.pushFrom(bodyBlocks, index + 1, distance);

        // 3) If this is a statement, increase the gap and all eventual superior parent statements
        BlockWithBody currentParent = block.getParentBlock();
        while (currentParent != null) {
            currentParent.increaseGapSize(distance);
            currentParent = currentParent.getParentBlock();
        }
    }

    /**
     * Remove a block from the body.
     * @custom.pre bodyBlocks.contains(block)
     * @param block The block to remove
     */
    public void removeBodyBlock(Block block) {
        assert bodyBlocks.contains(block);

        int index = bodyBlocks.indexOf(block);
        bodyBlocks.remove(block);
        int distance = -block.getHeight() - step;
        if (block instanceof StatementBlock) distance -= ((StatementBlock) block).getGapSize();
        PushBlocks.pushFrom(bodyBlocks, index, distance);

        block.setParentBlock(null);

        BlockWithBody currentParent = this;
        while (currentParent != null) {
            currentParent.increaseGapSize(distance);
            currentParent = currentParent.getParentBlock();
        }
    }

    public List<Block> getBodyBlocks() {
        return bodyBlocks;
    }

    /**
     * @return Returns the plug position of the body
     */
    public Point getBodyPlugPosition() {
        int pillarWidth = 10;
        return new Point(getPosition().x + pillarWidth, getPosition().y + getHeight() - step);
    }

    public int getGapSize() {
        return gapSize;
    }

    public void setGapSize(int gapSize) {
        this.gapSize = gapSize;
    }

    /**
     * Increases the gap size with the given size.
     * @param increase the given size
     */
    public void increaseGapSize(int increase) {
        this.setGapSize(getGapSize() + increase);
    }
}
