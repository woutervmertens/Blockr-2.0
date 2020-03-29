package com.swop.blocks;

import com.swop.worldElements.Direction;
import com.swop.worldElements.GameWorld;

public class TurnBlock extends ActionBlock {
    private final Direction direction;

    public TurnBlock(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void execute() {
        // TODO: try world.turn or catch and ignore exception
    }
}
