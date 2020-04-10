package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.Predicate;
import com.swop.PushBlocks;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// TODO BIG !! Should the program in program area contain body-blocks as well
// TODO BIG !! ... or should the statementblock execute and let know when it finished ??
public abstract class StatementBlock extends Block implements Executable, VerticallyConnectable {
    protected List<ConditionBlock> conditions = new ArrayList<>();
    protected List<Block> bodyBlocks = new ArrayList<>();
    protected Block currentBodyBlock = null;
    private final int pillarWidth = 10;
    private int gapSize;
    private int conditionWidth;


    public StatementBlock(Point position, GameWorld gameWorld, int width, int height) {
        super(position, gameWorld, width, height);
        conditionWidth = width / 2;
    }

    public boolean isConditionValid(GameWorld world) throws IllegalStateException {
        if (conditions.isEmpty()) throw new IllegalStateException("No condition for the statement");

        // WIF should only be at the last (and has to)
        for (int i = 0; i < conditions.size(); i++) {
            if (conditions.get(i).isWallInFrontBlock() && i < conditions.size() - 1) {
                throw new IllegalStateException("Invalid condition for statement block");
            }
        }

        // if length is even then there is an odd number of not blocks -> opposite of the result of wallInFront(world)
        if (conditions.size() % 2 == 0) return getGameWorld().evaluate(Predicate.WALL_IN_FRONT);
        else return !getGameWorld().evaluate(Predicate.WALL_IN_FRONT);

    }

    @Override
    public void execute() {
        if (isConditionValid(getGameWorld())) executeNextBodyBlock();
    }

    protected void executeNextBodyBlock() {
        // TODO: (after solving TODO BIG at class header !)
    }

    public List<Block> getBodyBlocks() {
        return bodyBlocks;
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
        bodyBlocks.add(bodyBlocks.indexOf(existingBlock) + 1, block);

        PushBlocks.pushBlocksInListFromIndexWithDistance(bodyBlocks, bodyBlocks.indexOf(existingBlock) + 2,
                block.getHeight() + step);

        block.setParentStatement(this);
        StatementBlock currentParent = block.getParentStatement();
        while (currentParent != null) {
            currentParent.increaseGapSize(block.getHeight() + step);
            currentParent = currentParent.getParentStatement();
        }
    }

    /**
     * Add the given block before the given existing block.
     */
    public void addBodyBlockBefore(Block block, Block existingBlock) {
        if (existingBlock == null) throw new IllegalArgumentException();
        if (!bodyBlocks.contains(existingBlock)) throw new IllegalArgumentException();

        PushBlocks.pushBlocksInListFromIndexWithDistance(bodyBlocks, bodyBlocks.indexOf(existingBlock),
                block.getHeight() + step);

        block.setParentStatement(this);
        StatementBlock currentParent = block.getParentStatement();
        while (currentParent != null) {
            currentParent.increaseGapSize(block.getHeight() + step);
            currentParent = currentParent.getParentStatement();
        }
    }

    /**
     * @pre bodyBlocks.contains(block)
     */
    public void removeBodyBlock(Block block) {
        assert bodyBlocks.contains(block);

        int index = bodyBlocks.indexOf(block);
        bodyBlocks.remove(block);
        PushBlocks.pushBlocksInListFromIndexWithDistance(bodyBlocks, index, -block.getHeight() - step);

        block.setParentStatement(null);
        StatementBlock currentParent = block.getParentStatement();
        while (currentParent != null) {
            currentParent.decreaseGapSize(block.getHeight() + step);
            currentParent = currentParent.getParentStatement();
        }
    }

    public void removeAllBodyBlocks() {
        assert !getBodyBlocks().isEmpty();

        int n = bodyBlocks.size();
        if (getParentStatement() != null) {
            PushBlocks.pushBlocksInListFromIndexWithDistance(getParentStatement().getBodyBlocks(),
                    getParentStatement().getBodyBlocks().indexOf(this), n * (-getBodyBlocks().get(0).getHeight() - step));
        }
        for (Block bodyBlock : getBodyBlocks()) {
            bodyBlock.setParentStatement(null);
        }

        StatementBlock currentParent = this;
        while (currentParent != null) {
            currentParent.decreaseGapSize(n * (getBodyBlocks().get(0).getHeight() + step));
            currentParent = currentParent.getParentStatement();
        }
        bodyBlocks.clear();
    }

    public void addConditionBlock(ConditionBlock block) {
        conditions.add(block);
    }

    // TODO: methods for handling removing body or condition blocks (removing one removes all the followings)

    @Override
    public Point getPlugPosition() {
        return new Point(getPosition().x /*+ step * 3*/, getPosition().y + getHeight() + /*pillarWidth*/ +gapSize + step);
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

    public int getGapSize() {
        return gapSize;
    }

    private void setGapSize(int gapSize) {
        this.gapSize = gapSize;
    }

    public void increaseGapSize(int increase) {
        assert increase > 0;
        this.setGapSize(getGapSize() + increase);
    }

    public void decreaseGapSize(int increase) {
        assert increase > 0;
        this.setGapSize(getGapSize() - increase);
    }

    @Override
    public boolean isPositionOn(int x, int y) {
        return (x > getPosition().x && x < getPosition().x + conditionWidth) && (y > getPosition().y && y < getPosition().y + getHeight());
    }
}