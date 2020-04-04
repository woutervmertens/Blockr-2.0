package com.swop.blocks;


import com.swop.worldElements.GameWorld;

import java.awt.*;

public abstract class Block {
    private final int width;
    private final int height;
    protected final int step;

    protected Block(Point position, GameWorld gameWorld, int width, int height) {
        if (gameWorld == null) throw new IllegalArgumentException();

        this.setPosition(position);
        this.gameWorld = gameWorld;
        this.width = width;
        this.height = height;
        step = height / 6;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    /**
     * Check whether the given position is on this block.
     */
    public boolean isPositionOn(int x, int y) {
        return (x > position.x && x < position.x + width) && (y > position.y && y < position.y + height);
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
