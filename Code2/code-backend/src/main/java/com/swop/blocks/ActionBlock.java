package com.swop.blocks;

import com.swop.worldElements.GameWorld;

public abstract class ActionBlock extends Block {
    private Block previous;
    private Block next;

    public void execute(GameWorld world) {
        doAction(world);
    }

    public void doAction(GameWorld world) {
        world.moveForward();
    }
}
