package com.swop.blocks;

import com.swop.worldElements.GameWorld;

public class MoveBlock extends ActionBlock {
    @Override
    public void doAction(GameWorld world) {
        world.moveForward();
    }
}
