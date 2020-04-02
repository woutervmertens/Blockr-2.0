package com.swop.blocks;

import com.swop.worldElements.GameWorld;

public class MoveBlock extends ActionBlock {
    @Override
    public void execute(GameWorld world) {
        world.moveForward();
    }
}
