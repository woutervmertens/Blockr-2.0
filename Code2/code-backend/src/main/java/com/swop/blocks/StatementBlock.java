package com.swop.blocks;

import com.swop.PushBlocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public abstract class StatementBlock extends BlockWithBody implements Executable, VerticallyConnectable {
    private final int pillarWidth = 10;
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

    public void resetExecution() {
        setNextBodyBlock(null);
        setBusy(false);
    }

    @Override
    public void setPosition(Point position) {
        try {
            int dx = position.x - getPosition().x;
            int dy = position.y - getPosition().y;
            super.setPosition(position);
            for (Block bodyBlock : getBodyBlocks()) {
                bodyBlock.setPosition(new Point(bodyBlock.getPosition().x + dx, bodyBlock.getPosition().y + dy));
            }
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

    /**
     * 1) Add the given block after the given existing block
     * 2) And push all others inside the body
     * 3) And make all the parents' gap sizes bigger.
     * <p>
     * If existing block is null add the given block at the start of the body
     */
    public void addBodyBlockAfter(Block block, Block existingBlock) {
        if (existingBlock == null) throw new IllegalArgumentException();
        if (!bodyBlocks.contains(existingBlock)) throw new IllegalArgumentException();
        insertBodyBlockAtIndex(block, bodyBlocks.indexOf(existingBlock) + 1);

    }

    /**
     * Add the given block before the given existing block.
     */
    public void addBodyBlockBefore(Block block, Block existingBlock) {
        if (existingBlock == null) throw new IllegalArgumentException();
        if (!bodyBlocks.contains(existingBlock)) throw new IllegalArgumentException();
        insertBodyBlockAtIndex(block, bodyBlocks.indexOf(existingBlock));
    }

    public void insertBodyBlockAtIndex(Block block, int index) {
        // 1) Add to the body blocks of this statement
        bodyBlocks.add(index, block);
        block.setParentBlock(this);

        // 2) Push all next body blocks down
        int distance = block.getHeight() + step;
        if (block instanceof StatementBlock) distance += ((StatementBlock) block).getGapSize();
        PushBlocks.pushFrom(bodyBlocks, index + 1, distance);

        // 3) Increase the gap of this statement and all eventual superior parent statements
        StatementBlock currentParent = block.getParentBlock();
        while (currentParent != null) {
            currentParent.increaseGapSize(distance);
            currentParent = currentParent.getParentBlock();
        }
    }

    /**
     * @pre bodyBlocks.contains(block)
     */
    public void removeBodyBlock(Block block) {
        assert bodyBlocks.contains(block);

        int index = bodyBlocks.indexOf(block);
        bodyBlocks.remove(block);
        int distance = -block.getHeight() - step;
        if (block instanceof StatementBlock) distance -= ((StatementBlock) block).getGapSize();
        PushBlocks.pushFrom(bodyBlocks, index, distance);

        block.setParentBlock(null);

        StatementBlock currentParent = this;
        while (currentParent != null) {
            currentParent.increaseGapSize(distance);
            currentParent = currentParent.getParentBlock();
        }
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

    public Point getBodyPlugPosition() {
        return new Point(getPosition().x + pillarWidth, getPosition().y + getHeight() - step);
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
}