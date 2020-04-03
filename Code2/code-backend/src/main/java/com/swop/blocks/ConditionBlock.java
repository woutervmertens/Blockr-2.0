package com.swop.blocks;

import com.swop.worldElements.GameWorld;

import java.awt.*;

public class ConditionBlock extends Block{
    /**
     * @param isWallInFrontBlock Variable registering whether the to make block is a WIF. If not then it is a NOT.
     */
    public ConditionBlock(Point position, GameWorld gameWorld, boolean isWallInFrontBlock) {
        super(position, gameWorld);
        this.isWallInFrontBlock = isWallInFrontBlock;
    }

    private final boolean isWallInFrontBlock;

    public boolean isWallInFrontBlock() {
        return isWallInFrontBlock;
    }
}
