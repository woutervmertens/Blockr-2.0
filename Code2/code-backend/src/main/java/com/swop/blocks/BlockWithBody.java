package com.swop.blocks;

import com.swop.PushBlocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class BlockWithBody extends Block{
    protected List<Block> bodyBlocks = new ArrayList<>();
    private Block nextBodyBlock = null;
    private int gapSize;

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
     * 1) Add the given block after the given existing block
     * 2) And push all others inside the body
     * 3) And make all the parents' gap sizes bigger.
     * <p>
     * If existing block is null add the given block at the start of the body
     */
    public void addBodyBlockAfter(Block block, Block existingBlock) {
        if (existingBlock == null) throw new IllegalArgumentException();
        if (!bodyBlocks.contains(existingBlock)) throw new IllegalArgumentException();
        insertBodyBlockAtIndex(block, bodyBlocks.indexOf(existingBlock) + 1);

    }

    /**
     * Add the given block before the given existing block.
     */
    public void addBodyBlockBefore(Block block, Block existingBlock) {
        if (existingBlock == null) throw new IllegalArgumentException();
        if (!bodyBlocks.contains(existingBlock)) throw new IllegalArgumentException();
        insertBodyBlockAtIndex(block, bodyBlocks.indexOf(existingBlock));
    }

    public void insertBodyBlockAtIndex(Block block, int index) {
        // TODO: make sure program is not pushed for FunctionDefinitionBlock

        // 1) Add to the body blocks of this statement
        bodyBlocks.add(index, block);
        block.setParentBlock(this);

        // 2) Push all next body blocks down
        int distance = block.getHeight() + step;
        if (block instanceof StatementBlock) distance += ((StatementBlock) block).getGapSize();
        PushBlocks.pushFrom(bodyBlocks, index + 1, distance);

        // 3) Increase the gap of this statement and all eventual superior parent statements
        BlockWithBody currentParent = block.getParentBlock();
        while (currentParent != null) {
            currentParent.increaseGapSize(distance);
            currentParent = currentParent.getParentBlock();
        }
    }

    /**
     * @pre bodyBlocks.contains(block)
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

    public void resetExecution() {
        setNextBodyBlock(null);
        setBusy(false);
    }

    public Block getNextBodyBlock() {
        return nextBodyBlock;
    }

    protected void setNextBodyBlock(Block nextBodyBlock) {
        this.nextBodyBlock = nextBodyBlock;
    }

    protected void setNextBodyBlock() {
        if (getNextBodyBlock() == null && !getBodyBlocks().isEmpty()) {
            setNextBodyBlock(getBodyBlocks().get(0));
        } else {
            try {
                setNextBodyBlock(getBodyBlocks().get(getBodyBlocks().indexOf(nextBodyBlock) + 1));
            } catch (Exception e) {
                setNextBodyBlock(null);
            }
        }
    }

    protected void executeNextBodyBlock() {
        if (getNextBodyBlock() == null) {
            setBusy(false);
        } else {
            Executable exBlock = (Executable) getNextBodyBlock();
            exBlock.execute();
            setNextBodyBlock();
            if (getNextBodyBlock() == null) {
                handleEndOfBody();
            }
        }
    }

    protected abstract void handleEndOfBody();

    public List<Block> getBodyBlocks() {
        return bodyBlocks;
    }

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

    public void increaseGapSize(int increase) {
        this.setGapSize(getGapSize() + increase);
    }

    @Override
    public int getCount() {
        int count = 1;
        for (Block block : getBodyBlocks()){
            count +=  block.getCount();
        }
        return count;
    }
}
