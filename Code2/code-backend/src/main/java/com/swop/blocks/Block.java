package com.swop.blocks;

import com.swop.BlockrGame;
import com.swop.GameWorld;

import java.awt.*;

public abstract class Block implements Cloneable {
    protected final int step;
    private final int width;
    private final int height;
    protected ExecuteType executeType;
    private BlockWithBody parentBlock;
    private BlockrGame blockrGame;

    private boolean busy = false;
    private Point position;
    private Point previousDropPosition;

    /**
     * Creates a block with the given position, width and height.
     * @param position Position of the block.
     * @param width Width of the block.
     * @param height Height of the block.
     */
    protected Block(Point position, int width, int height) {
        this.setPosition(position);
        this.width = width;
        this.height = height;
        step = height / 6;
    }

    /**
     * @return Returns a clone of the given block.
     */
    public Block clone() {
        try {
            return (Block) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setBlockrGame(BlockrGame blockrGame){
        this.blockrGame = blockrGame;
    }

    public int getStep() {
        return step;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isBusy() {
        return busy;
    }

    public void setBusy(boolean busy) {
        this.busy = busy;
    }

    public ExecuteType getExecuteType() {
        return executeType;
    }

    public BlockWithBody getParentBlock() {
        return parentBlock;
    }

    public void setParentBlock(BlockWithBody parentBlock) {
        this.parentBlock = parentBlock;
    }


    /**
     * @param x x-position.
     * @param y y-position.
     * @return Returns whether the given position is on this block.
     */
    public boolean isPositionOn(int x, int y) {
        return (x > position.x && x < position.x + width) && (y > position.y && y < position.y + height);
    }

    /**
     * @param block the given block
     * @return Return whether this block is under the given block.
     */
    public boolean isUnder(Block block) {
        return this.getPosition().y > block.getPosition().y;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        if (position == null) throw new IllegalArgumentException();
        this.position = position;
    }

    public Point getPreviousDropPosition() {
        return previousDropPosition;
    }

    public void setPreviousDropPosition(Point previousDropPosition) {
        this.previousDropPosition = previousDropPosition;
    }

    /**
     * @pre blockerGame isn't null
     * @return Returns the blockrGame where the block belongs.
     */
    public GameWorld getGameWorld() {
        if(blockrGame == null) throw new NullPointerException("BlockrGame not set in Block.");
        return blockrGame.getGameWorld();
    }

    public int getCount(){return 1;}
}
