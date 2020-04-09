package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.Predicate;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

// TODO BIG !! Should the program in program area contain body-blocks as well
// TODO BIG !! ... or should the statementblock execute and let know when it finished ??
public abstract class StatementBlock extends Block implements Executable, VerticallyConnectable {
    protected List<ConditionBlock> conditions = new ArrayList<>();
    protected List<ActionBlock> bodyBlocks = new ArrayList<>();
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

    public List<ActionBlock> getBodyBlocks() {
        return bodyBlocks;
    }

    public List<ConditionBlock> getConditions() {
        return conditions;
    }

    /**
     * Add the given block to the end of the body of this statement block.
     */
    public void addBodyBlock(ActionBlock block) {
        bodyBlocks.add(block);
        if (bodyBlocks.size() == 1) increaseGapSize(step);
        increaseGapSize(block.getHeight());
    }

    /**
     * @pre bodyBlocks.contains(block)
     */
    public void removeBodyBlock(ActionBlock block) {
        assert bodyBlocks.contains(block);

        int index = bodyBlocks.indexOf(block);
        bodyBlocks.remove(block);
        for (int i = index; i < bodyBlocks.size(); i++) {
            ActionBlock currentBlock = bodyBlocks.get(i);
            currentBlock.setPosition(new Point(currentBlock.getPosition().x,
                    currentBlock.getPosition().y - block.getHeight()));
        }
        decreaseGapSize(block.getHeight());
        if (bodyBlocks.isEmpty()) setGapSize(0);
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