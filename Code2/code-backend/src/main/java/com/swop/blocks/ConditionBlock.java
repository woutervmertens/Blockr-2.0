package com.swop.blocks;

import com.swop.BlockrGame;

import java.awt.*;

public class ConditionBlock extends Block implements HorizontallyConnectable {
    private final boolean isPredicate;

    /**
     * @param isPredicate Variable registering whether the to make block is a WIF. If not then it is a NOT.
     */
    public ConditionBlock(Point position, boolean isPredicate, int width, int height, BlockrGame blockrGame) {
        super(position, width, height, blockrGame);
        this.isPredicate = isPredicate;
        executeType = ExecuteType.NonExecutable;
    }

    public boolean isPredicate() {
        return isPredicate;
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
