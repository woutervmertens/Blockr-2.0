package com.swop.blocks;

import com.swop.worldElements.Direction;
import com.swop.worldElements.GameWorld;

import java.util.LinkedList;
import java.util.List;

public abstract class StatementBlock extends Block {
    // TODO: 30/03/2020 moet conditions een linked list zijn?
    protected Condition[] conditions;
    protected List<ActionBlock> bodyBlocks;

    public StatementBlock(Condition[] conditions, List<ActionBlock> bodyBlocks) {
        if (conditions.length == 0) throw new IllegalArgumentException("Cannot make statementBlock without conditions");
        this.conditions = conditions;
        this.bodyBlocks = bodyBlocks;
    }


    public boolean isConditionValid(GameWorld world) throws IllegalStateException {
        int length = conditions.length;
        if (length == 0 || (conditions[length - 1] != Condition.WIF)) {
            throw new IllegalStateException("coditions is empty or there is no WIF block");
        }
        // if length is even then there is an odd number of not blocks -> opposite of the result of wallInFront(world)
        if (length % 2 == 0){
            return ! wallInFront(world);
        }else{
            return wallInFront(world);
        }
    }

    @Override
    public void execute(GameWorld world) {
        if (isConditionValid(world)) executeBodyOnce();
    }

    private void executeBodyOnce() {
        // TODO:
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

    enum Condition {
        WIF, NOT;
    }
}