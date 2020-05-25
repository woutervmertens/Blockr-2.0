package com.swop.blocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A block that is a statement.
 */
public abstract class StatementBlock extends BlockWithBody implements Executable, VerticallyConnectable {
    protected List<ConditionBlock> conditions = new ArrayList<>();
    private final int conditionWidth;

    /**
     * Creates a block that is a statement with the given position, width and height.
     * @param position the given position of the statementBlock.
     * @param width the given width of the statementBlock.
     * @param height the given height of the statementBlock.
     */
    public StatementBlock(Point position, int width, int height) {
        super(position, width, height);
        conditionWidth = width / 2;
        executeType = ExecuteType.NonWorldChanging;
    }

    /**
     * Sets the previous position of the statementBlock and sets all the bodyBlocks of the statementBlock to their previous position.
     * @param previousDropPosition The given previous drop position
     */
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

    /**
     * Sets the position of the statementBlock to the given position and the conditionBlock(s) to their adjusted position.
     * @param position the given position.
     */
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

    /**
     * @return Returns true or false according to the combination of conditions are true or false.
     */
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

    /**
     * Executes the nextBodyBlock if there is one otherwise busy will be set to false.
     */
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

    /**
     * Adds the given conditionBlock to the statement and sets the parentBlock of the given block to this statementBlock.
     * @param block The given conditionBlock.
     */
    public void addConditionBlock(ConditionBlock block) {
        conditions.add(block);
        block.setParentBlock(this);
    }

    /**
     * Removes all the ConditionBlocks behind the given conditionBlock and the given conditionBlock from the statementBlock.
     * @param block The given conditionBlock.
     */
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

    /**
     * @return Returns the plug position of the statementBlock.
     */
    @Override
    public Point getPlugPosition() {
        return new Point(getPosition().x /*+ step * 3*/, getPosition().y + getHeight() + /*pillarWidth*/ +getGapSize() + step);
    }

    /**
     * @return Returns the socket position of the statementBlock.
     */
    @Override
    public Point getSocketPosition() {
        return new Point(getPosition().x /*+ step * 3*/, getPosition().y + step);
    }

    public Point getConditionPlugPosition() {
        return new Point(getPosition().x + conditionWidth + step, getPosition().y);
    }

    /**
     * Is the given position on this statement block.
     * This method is overridden bcs statementBlocks should only be clicked on their upper part (conditionWidth).
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