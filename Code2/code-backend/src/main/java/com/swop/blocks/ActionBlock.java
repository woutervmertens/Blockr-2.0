package com.swop.blocks;

import com.swop.Action;
import com.swop.BlockrGame;
import com.swop.Snapshot;
import com.swop.SuccessState;

import java.awt.*;

public class ActionBlock extends Block implements Executable, VerticallyConnectable {
    private final Action action;

    public ActionBlock(Point position, int width, int height, Action action, BlockrGame blockrGame) {
        super(position, width, height, blockrGame);
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
        Snapshot snap = getGameWorld().createSnapshot();
        SuccessState state = getGameWorld().doAction(action);
        if (state == SuccessState.FAILURE) {
            getGameWorld().restoreSnapshot(snap);
        }
    }
}
