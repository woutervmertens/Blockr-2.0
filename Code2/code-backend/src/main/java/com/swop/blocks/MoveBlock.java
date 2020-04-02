package com.swop.blocks;

import com.swop.worldElements.GameWorld;

import java.awt.*;

// TODO: find a way to remove this class (maybe action block enum ?)
public class MoveBlock extends ActionBlock {
    public MoveBlock(Point position, GameWorld gameWorld) {
        super(position, gameWorld);
    }

    @Override
    public void execute() {
        getGameWorld().moveForward();
    }
}
