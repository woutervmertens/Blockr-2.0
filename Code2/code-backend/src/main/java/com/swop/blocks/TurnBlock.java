package com.swop.blocks;

import com.swop.worldElements.Direction;
import com.swop.worldElements.GameWorld;

public class TurnBlock extends ActionBlock {
    private Direction direction;

    public TurnBlock(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void doAction(GameWorld world) {
        world.turn(direction);
    }

}
