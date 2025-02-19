package com.swop.blocks;

import com.swop.BlockrGame;

import com.swop.BlockrGame;
import com.swop.Predicate;

import java.awt.*;

/**
 * A block that is a condition.
 */
public class ConditionBlock extends Block implements HorizontallyConnectable {
    private final boolean isPredicate;
    private final Predicate predicate;

    /**
     * create a condition BLock
     * @param position position of the block
     * @param isPredicate Variable registering whether the to make block is a predicate (true) otherwise NOT (false).
     * @param width width of the block
     * @param height height of the block
     * @param predicate predicate of the block
     */
    public ConditionBlock(Point position, boolean isPredicate, int width, int height, Predicate predicate) {
        super(position, width, height);
        this.isPredicate = isPredicate;
        this.predicate = predicate;
        executeType = ExecuteType.NonExecutable;
    }

    public boolean isPredicate() {
        return isPredicate;
    }
    public Predicate getPredicate() { return predicate;}

    /**
     * @return returns the plug position of the conditionBlock.
     */
    @Override
    public Point getPlugPosition() {
        return new Point(getPosition().x + getWidth() + step, getPosition().y/* + step * 3*/);
    }

    /**
     * @return  returns the plug position of the conditionBlock.
     */
    @Override
    public Point getSocketPosition() {
        return new Point(getPosition().x + step, getPosition().y/* + step * 3*/);
    }
}
