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
            return wallInFront(world);

        } else if (conditions.get(0) instanceof NotBlock && conditions.getLast() instanceof WallInFrontBlock) {
            if (conditions.size() % 2 == 0) {
                return !wallInFront(world);
            } else {
                return wallInFront(world);
            }
        } else {
            throw new IllegalStateException("Illegal Condition of StatementBlock !");
        }
    }

    private boolean wallInFront(GameWorld world) {
        int cPosX = world.getCharacter().getPosition()[0];
        int cPosY = world.getCharacter().getPosition()[1];
        Direction cDir = world.getCharacter().getDirection();
        if (cDir == Direction.LEFT && !world.getGrid()[cPosX - 1][cPosY].isPassable()) {
            return true;
        } else if (cDir == Direction.RIGHT && !world.getGrid()[cPosX + 1][cPosY].isPassable()) {
            return true;
        } else if (cDir == Direction.UP && !world.getGrid()[cPosX][cPosY - 1].isPassable()) {
            return true;
        } else return cDir != Direction.DOWN || world.getGrid()[cPosX][cPosY + 1].isPassable();
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
