package com.swop.blocks;

import com.swop.Action;
import com.swop.GameWorld;

import java.awt.*;

public class ActionBlock extends Block implements Executable, VerticallyConnectable {
    private Action action;

    public ActionBlock(Point position, GameWorld gameWorld, int width, int height, Action action) {
        super(position, gameWorld, width, height);
        this.action = action;
        executeType = ExecuteType.WorldChanging;
    }

    @Override
    public Point getPlugPosition() {
        return new Point(getPosition().x /*+ step * 3*/, getPosition().y + getHeight() + step);
    }

    @Override
    public Point getSocketPosition() {
        return new Point(getPosition().x /*+ step * 3*/, getPosition().y + step);
    }

    @Override
    public void execute() {
        getGameWorld().doAction(action);
    }
}
