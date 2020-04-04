package com.swop.blocks;

import java.awt.*;

public class TurnBlock extends ActionBlock {
    private final boolean isClockwise;

    public TurnBlock(Point position, GameWorld gameWorld, boolean isClockwise, int width, int height) {
        super(position, gameWorld, width, height);
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
