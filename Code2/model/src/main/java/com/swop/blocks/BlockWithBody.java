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
     * 1) Add the given block after the given existing block
     * 2) And push all others inside the body
     * 3) And make all the parents' gap sizes bigger.
     * <p>
     * If existing block is null add the given block at the start of the body
     *
     * @param block The block to add.
     * @param existingBlock The already existing block.
     */
    public void addBodyBlockAfter(Block block, Block existingBlock) {
        if (existingBlock == null) throw new IllegalArgumentException();
        if (!bodyBlocks.contains(existingBlock)) throw new IllegalArgumentException();
        insertBodyBlockAtIndex(block, bodyBlocks.indexOf(existingBlock) + 1);

    }

    /**
     * Add the given block before the given existing block.
     *
     * @param block The block to add.
     * @param existingBlock The already existing block.
     */
    public void addBodyBlockBefore(Block block, Block existingBlock) {
        if (existingBlock == null) throw new IllegalArgumentException();
        if (!bodyBlocks.contains(existingBlock)) throw new IllegalArgumentException();
        insertBodyBlockAtIndex(block, bodyBlocks.indexOf(existingBlock));
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

    /**
     * The next body block will be executed. If there wasn't a block executed before the boolean 'busy' will be set true and the nextBodyBlock gets chosen.
     */
    @Override
    public void execute() {
        if (!isBusy()) {
            setBusy(true);
            if (getNextBodyBlock() == null) setNextBodyBlock();
        }
        executeNextBodyBlock();
    }

    /**
     * The execution state of a block with a body resets to the beginning state: the nextBodyBlock is null and busy is false.
     */
    public void resetExecution() {
        setNextBodyBlock(null);
        setBusy(false);
    }

    public Block getNextBodyBlock() {
        return nextBodyBlock;
    }

    public void setNextBodyBlock(Block nextBodyBlock) {
        this.nextBodyBlock = nextBodyBlock;
    }

    /**
     * if there isn't a (next) bodyBlock, nextBodyBlock will be null otherwise the next or the first bodyBlock.
     */
    public void setNextBodyBlock() {
        if (getNextBodyBlock() == null && !getBodyBlocks().isEmpty()) {
            setNextBodyBlock(getBodyBlocks().get(0));
        } else if (getNextBodyBlock() != null && !getNextBodyBlock().isBusy()) {
            try {
                setNextBodyBlock(getBodyBlocks().get(getBodyBlocks().indexOf(nextBodyBlock) + 1));
            } catch (Exception e) {
                setNextBodyBlock(null);
            }
        }
    }

    /**
     * The next body block will be executed or busy will be set to false.
     */
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

    /**
     * @return Returns the number of blocks: blockWithBody + # bodyBlocks
     */
    @Override
    public int getCount() {
        int count = 1;
        for (Block block : getBodyBlocks()) {
            count += block.getCount();
        }
        return count;
    }
}
