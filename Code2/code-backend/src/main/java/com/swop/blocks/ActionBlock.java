package com.swop.blocks;

import com.swop.Action;
import com.swop.BlockrGame;
import com.swop.Snapshot;
import com.swop.SuccessState;

import java.awt.*;

/**
 * A block to perform an action.
 */
public class ActionBlock extends Block implements Executable, VerticallyConnectable {
    private final Action action;

    /**
     * Creates an actionBlock with the given position, width, height and action.
     * @param position The position of the actionBlock.
     * @param width The width of the actionBlock.
     * @param height The height of the actionBlock.
     * @param action The action that the actionBlock can perform on the gameworld.
     */
    public ActionBlock(Point position, int width, int height, Action action) {
        super(position, width, height);
        this.action = action;
        executeType = ExecuteType.WorldChanging;
    }

    /**
     * @return returns the plug position of the actionBlock.
     */
    @Override
    public Point getPlugPosition() {
        return new Point(getPosition().x /*+ step * 3*/, getPosition().y + getHeight() + step);
    }

    /**
     * @return returns the socket position of the actionBlock.
     */
    @Override
    public Point getSocketPosition() {
        return new Point(getPosition().x /*+ step * 3*/, getPosition().y + step);
    }

    /**
     * Executes the action on the game world if possible, otherwise the state of the gameworld will restore like the action didn't happen.
     */
    @Override
    public void execute() {
        Snapshot snap = getGameWorld().createSnapshot();
        SuccessState state = getGameWorld().doAction(action);
        if (state == SuccessState.FAILURE) {
            getGameWorld().restoreSnapshot(snap);
        }
    }
}
