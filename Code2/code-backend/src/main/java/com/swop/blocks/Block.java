package com.swop.blocks;

import com.swop.GameWorld;

import java.awt.*;

public abstract class Block {
    private final int width;
    private final int height;
    protected final int step;
    protected ExecuteType executeType;
    private StatementBlock parentStatement;

    protected Block(Point position, GameWorld gameWorld, int width, int height) {
        if (gameWorld == null) throw new IllegalArgumentException();

        this.setPosition(position);
        this.gameWorld = gameWorld;
        this.width = width;
        this.height = height;
        step = height / 6;
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

    public void setPosition(Point position) {
        this.position = position;
    }

    private Point position;

    public GameWorld getGameWorld() {
        return gameWorld;
    }

    private final GameWorld gameWorld;

    public void terminate() {
        this.position = null;
    }

    public abstract Point getSocketPosition();

    public abstract Point getPlugPosition();

    // TODO: should we add getParentStatement() ? Is is needed ?
}
