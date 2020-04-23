package com.swop.blocks;

import com.swop.BlockrGame;
import com.swop.GameWorld;

import java.awt.*;

public abstract class Block implements Cloneable {
    private final int width;
    private final int height;
    protected final int step;
    protected ExecuteType executeType;
    private StatementBlock parentStatement;

    private boolean done = true;

    protected Block(Point position, int width, int height) {
        this.setPosition(position);
        this.width = width;
        this.height = height;
        step = height / 6;
    }

    public Block clone(){
        try {
            return (Block)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
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

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public ExecuteType getExecuteType() {
        return executeType;
    }

    public StatementBlock getParentStatement() {
        return parentStatement;
    }

    public void setParentStatement(StatementBlock parentStatement) {
        this.parentStatement = parentStatement;
    }

    /**
     * Check whether the given position is on this block.
     */
    public boolean isPositionOn(int x, int y) {
        return (x > position.x && x < position.x + width) && (y > position.y && y < position.y + height);
    }

    /**
     * Check whether this block is under the given block
     */
    public boolean isUnder(Block block) {
        return this.getPosition().y > block.getPosition().y;
    }

    public Point getPosition() {
        return position;
    }

    public Point getPreviousDropPosition() {
        return previousDropPosition;
    }

    public void setPosition(Point position) {
        if (position == null) throw new IllegalArgumentException();
        this.position = position;
    }

    public void setPreviousDropPosition(Point previousDropPosition) {
        this.previousDropPosition = previousDropPosition;
    }

    private Point position;

    private Point previousDropPosition;

    public GameWorld getGameWorld() {
        return BlockrGame.getInstance().getGameWorld();
    }

    public abstract Point getSocketPosition();

    public abstract Point getPlugPosition();

    // TODO: should we add getParentStatement() ? Is is needed ?
}
