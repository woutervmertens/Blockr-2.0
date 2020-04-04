package com.swop.blocks;

import java.awt.*;

public abstract class ActionBlock extends Block implements Executable, VerticallyConnectable {
    public ActionBlock(Point position, GameWorld gameWorld, int width, int height) {
        super(position, gameWorld, width, height);
    }

    @Override
    public Point getPlugPosition() {
        return new Point(getPosition().x /*+ step * 3*/, getPosition().y + getHeight() + step);
    }

    @Override
    public Point getSocketPosition() {
        return new Point(getPosition().x /*+ step * 3*/, getPosition().y + step);
    }
}
