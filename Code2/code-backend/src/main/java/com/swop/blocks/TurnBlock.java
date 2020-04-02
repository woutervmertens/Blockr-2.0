package com.swop.blocks;

import com.swop.worldElements.GameWorld;

import java.awt.*;

public class TurnBlock extends ActionBlock {
    private final boolean isClockwise;

    public TurnBlock(Point position, GameWorld gameWorld, boolean isClockwise) {
        super(position, gameWorld);
        this.isClockwise = isClockwise;
    }

    @Override
    public void execute() {
        getGameWorld().turn(isClockwise());
    }

    public boolean isClockwise() {
        return this.isClockwise;
    }
}
