package com.swop.blocks;

import java.awt.*;

public class ConditionBlock extends Block implements HorizontallyConnectable{
    /**
     * @param isPredicate Variable registering whether the to make block is a WIF. If not then it is a NOT.
     */
    public ConditionBlock(Point position, boolean isPredicate, int width, int height) {
        super(position, width, height);
        this.isPredicate = isPredicate;
        executeType = ExecuteType.NonExecutable;
    }

    private final boolean isPredicate;

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
