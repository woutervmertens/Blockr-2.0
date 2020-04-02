package com.swop.blocks;

import com.swop.worldElements.GameWorld;

import java.awt.*;

public abstract class ActionBlock extends Block {
    public ActionBlock(Point position, GameWorld gameWorld) {
        super(position, gameWorld);
    }
}
