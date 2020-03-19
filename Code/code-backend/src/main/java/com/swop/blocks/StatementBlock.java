package com.swop.blocks;

import com.swop.worldElements.Direction;
import com.swop.worldElements.GameWorld;

import java.util.LinkedList;

public abstract class StatementBlock extends Block {
    LinkedList<ConditionBlock> conditions;  // Currently either WIF or Not WIF
    BlockGroup body;
    private Block previous;
    private Block next;

    public StatementBlock() {
        this.conditions = new LinkedList<>();
        this.body = new BlockGroup();
    }

    public LinkedList<ConditionBlock> getConditions() {
        return conditions;
    }

    public void setConditions(LinkedList<ConditionBlock> conditions) {
        this.conditions = conditions;
    }

    public boolean isConditionValid(GameWorld world) {
        if (conditions.get(0) instanceof WallInFrontBlock) {
            return noWallInFront(world);

        } else if (conditions.get(0) instanceof NotBlock && conditions.getLast() instanceof WallInFrontBlock) {
            if (conditions.size() % 2 == 0) {
                return !noWallInFront(world);
            } else {
                return noWallInFront(world);
            }
        } else {
            throw new IllegalStateException("Illegal Condition of StatementBlock !");
        }
    }

    private boolean noWallInFront(GameWorld world) {
        int cPosY = world.getCharacter().getPosition()[0];
        int cPosX = world.getCharacter().getPosition()[1];
        Direction cDir = world.getCharacter().getDirection();
        if (cDir == Direction.LEFT && !world.getGrid()[cPosY][cPosX - 1].isPassable()) {
            return false;
        } else if (cDir == Direction.RIGHT && !world.getGrid()[cPosY][cPosX + 1].isPassable()) {
            return false;
        } else if (cDir == Direction.UP && !world.getGrid()[cPosY - 1][cPosX].isPassable()) {
            return false;
        } else return cDir != Direction.DOWN || world.getGrid()[cPosY + 1][cPosX].isPassable();
    }

    public BlockGroup getBody() {
        return body;
    }

    public void setBody(BlockGroup children) {
        this.body = children;
    }

    public void addCondition(ConditionBlock b) {
        conditions.add(b);
    }

    public Block getPrevious() {
        return previous;
    }

    public void setPrevious(Block previous) {
        this.previous = previous;
    }

    public Block getNext() {
        return next;
    }

    public void setNext(Block next) {
        this.next = next;
    }
}
