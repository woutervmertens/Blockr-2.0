package com.swop.blocks;

import com.swop.worldElements.Direction;
import com.swop.worldElements.GameWorld;

public class TurnBlock extends ActionBlock {
    private final Direction direction;

    public TurnBlock(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void execute(GameWorld world) {
        // TODO: try world.turn or catch and ignore exception. ANSWER: why would there be an exception?
        try {
            world.turn(direction);
        }catch (Exception e){}
    }
}
