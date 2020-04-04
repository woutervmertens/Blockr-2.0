package com.swop.blocks;

import com.swop.GameWorld;

import java.awt.*;

public class ConditionBlock extends Block implements HorizontallyConnectable{
    /**
     * @param isWallInFrontBlock Variable registering whether the to make block is a WIF. If not then it is a NOT.
     */
    public ConditionBlock(Point position, GameWorld gameWorld, boolean isWallInFrontBlock, int width, int height) {
        super(position, gameWorld, width, height);
        this.isWallInFrontBlock = isWallInFrontBlock;
    }

    private final boolean isWallInFrontBlock;

    public boolean isWallInFrontBlock() {
        return isWallInFrontBlock;
    }

    @Override
    public Point getPlugPosition() {
        return new Point(getPosition().x + getWidth() + step, getPosition().y/* + step * 3*/);
    }

    @Override
    public Point getSocketPosition() {
        return new Point(getPosition().x + step, getPosition().y/* + step * 3*/);
    }
}
