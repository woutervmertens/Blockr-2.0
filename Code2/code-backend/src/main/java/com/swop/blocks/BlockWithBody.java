package com.swop.blocks;

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

    public int getGapSize() {
        return gapSize;
    }

    public void setGapSize(int gapSize) {
        this.gapSize = gapSize;
    }

    public void increaseGapSize(int increase) {
        this.setGapSize(getGapSize() + increase);
    }
}
