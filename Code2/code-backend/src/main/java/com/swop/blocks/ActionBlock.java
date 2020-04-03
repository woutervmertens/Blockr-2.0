package com.swop.blocks;

import com.swop.worldElements.GameWorld;

import java.awt.*;

public abstract class ActionBlock extends Block implements Executable {
    public ActionBlock(Point position, GameWorld gameWorld, int width, int height) {
        super(position, gameWorld, width, height);
    }
}
