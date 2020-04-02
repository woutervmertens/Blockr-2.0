package com.swop.blocks;


import com.swop.worldElements.GameWorld;

import java.awt.*;

public abstract class Block {
    protected Block(Point position, GameWorld gameWorld) {
        if (gameWorld == null) throw new IllegalArgumentException();

        this.setPosition(position);
        this.gameWorld = gameWorld;
    }

    public abstract void execute();

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
}
