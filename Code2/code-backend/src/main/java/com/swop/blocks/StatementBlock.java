package com.swop.blocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class StatementBlock extends BlockWithBody implements Executable, VerticallyConnectable {
    protected List<ConditionBlock> conditions = new ArrayList<>();
    private final int conditionWidth;

    public StatementBlock(Point position, int width, int height) {
        super(position, width, height);
        conditionWidth = width / 2;
        executeType = ExecuteType.NonWorldChanging;
    }

    @Override
    public void setPreviousDropPosition(Point previousDropPosition) {
        super.setPreviousDropPosition(previousDropPosition);
        for (Block bodyBlock : getBodyBlocks()) {
            bodyBlock.setPreviousDropPosition(bodyBlock.getPosition());
        }
        for (Block condition : getConditions()) {
            condition.setPreviousDropPosition(condition.getPosition());
        }
    }

    @Override
    public void setPosition(Point position) {
        try {
            int dx = position.x - getPosition().x;
            int dy = position.y - getPosition().y;
            super.setPosition(position);
            for (Block conditionBlock : getConditions()) {
                conditionBlock.setPosition(new Point(conditionBlock.getPosition().x + dx, conditionBlock.getPosition().y + dy));
            }
        } catch (NullPointerException e) {
            super.setPosition(position);
        }
    }

    public boolean isConditionValid() {
        // TODO: decide whether to return false or throw exceptions on illegal conditions

        if (conditions.isEmpty()) return false;

        int size = conditions.size();
        ConditionBlock last = conditions.get(size - 1);
        // Predicate(that isn't NOT) should only be at the last (and has to)
        for (int i = 0; i < size; i++) {
            if (conditions.get(i).isPredicate() && i < size - 1) {
                throw new IllegalStateException("Invalid condition for statement block");
            }
        }

        // if length is even then there is an odd number of not blocks -> opposite of the result of wallInFront(world)
        if (conditions.size() % 2 == 0) return !getGameWorld().evaluate(last.getPredicate());
        else if (last.isPredicate()) return getGameWorld().evaluate(last.getPredicate());
        else return false;

    }

    @Override
    public void execute() {
        if (isConditionValid() || isBusy()) {
            if (!isBusy()) {
                setBusy(true);
                setNextBodyBlock();
            }
            executeNextBodyBlock();
        } else {
            setBusy(false);
        }
    }

    public List<ConditionBlock> getConditions() {
        return conditions;
    }

    public void addConditionBlock(ConditionBlock block) {
        conditions.add(block);
        block.setParentBlock(this);
    }

    public void removeConditionBlock(ConditionBlock block) {
        assert getConditions().contains(block);

        ConditionBlock currentCondition;
        int n = getConditions().size();
        int j = getConditions().indexOf(block);
        for (int i = n - 1; i >= j; i--) {
            currentCondition = getConditions().get(i);
            currentCondition.setParentBlock(null);
            conditions.remove(currentCondition);
        }
    }

    @Override
    public Point getPlugPosition() {
        return new Point(getPosition().x /*+ step * 3*/, getPosition().y + getHeight() + /*pillarWidth*/ +getGapSize() + step);
    }

    @Override
    public Point getSocketPosition() {
        return new Point(getPosition().x /*+ step * 3*/, getPosition().y + step);
    }

    public Point getConditionPlugPosition() {
        return new Point(getPosition().x + conditionWidth + step, getPosition().y);
    }

    /**
     * Is the given position on this statement block.
     * This method is overridden bcs statementblocks should only be clicked on their upper part (conditionWidth).
     */
    @Override
    public boolean isPositionOn(int x, int y) {
        return (x > getPosition().x && x < getPosition().x + conditionWidth) && (y > getPosition().y && y < getPosition().y + getHeight());
    }

    @Override
    public int getCount() {
        return super.getCount() + getConditions().size();
    }
}