package com.swop.blocks;

import com.swop.worldElements.Direction;
import com.swop.worldElements.GameWorld;

import java.awt.*;
import java.util.List;

// TODO BIG !! Should the program in program area contain body-blocks as well
// TODO BIG !! ... or should the statementblock execute and let know when it finished ??
public abstract class StatementBlock extends Block {
    protected Condition[] conditions;  // TODO: possible to construct from outside ? If not make enum separate
    protected List<ActionBlock> bodyBlocks;
    protected Block currentBodyBlock = null;

    public StatementBlock(Point position, GameWorld gameWorld, Condition[] conditions, List<ActionBlock> bodyBlocks) {
        super(position, gameWorld);
        if (conditions.length == 0) throw new IllegalArgumentException("Cannot make statementBlock without conditions");
        this.conditions = conditions;
        this.bodyBlocks = bodyBlocks;
    }


    public boolean isConditionValid(GameWorld world) throws IllegalStateException {
        if (conditions.length == 0) throw new IllegalStateException("No condition for the statement");

        // WIF should only be at the last (and has to)
        for (int i = 0; i < conditions.length; i++) {
            if (conditions[i] == Condition.WIF && i < conditions.length - 1) {
                throw new IllegalStateException("Invalid condition for statement block");
            }
        }

        // if length is even then there is an odd number of not blocks -> opposite of the result of wallInFront(world)
        if (conditions.length % 2 == 0) return getGameWorld().isPassableInFrontOfCharacter();
        else return ! getGameWorld().isPassableInFrontOfCharacter();

    }

    @Override
    public void execute(GameWorld world) {
        if (isConditionValid(world)) executeNextBodyBlock();
    }

    protected void executeNextBodyBlock() {
        // TODO: (after solving TODO BIG at class header !)
    }

    enum Condition {
        WIF, NOT;
    }
}