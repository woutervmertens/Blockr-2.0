package com.swop.blocks;

import com.swop.worldElements.GameWorld;

import java.awt.*;
import java.util.List;

// TODO BIG !! Should the program in program area contain body-blocks as well
// TODO BIG !! ... or should the statementblock execute and let know when it finished ??
public abstract class StatementBlock extends Block implements Executable{
    protected ConditionBlock[] conditions;
    protected List<ActionBlock> bodyBlocks;
    protected Block currentBodyBlock = null;

    public StatementBlock(Point position, GameWorld gameWorld, int width, int height) {
        super(position, gameWorld, width, height);
    }


    public boolean isConditionValid(GameWorld world) throws IllegalStateException {
        if (conditions.length == 0) throw new IllegalStateException("No condition for the statement");

        // WIF should only be at the last (and has to)
        for (int i = 0; i < conditions.length; i++) {
            if (conditions[i].isWallInFrontBlock() && i < conditions.length - 1) {
                throw new IllegalStateException("Invalid condition for statement block");
            }
        }

        // if length is even then there is an odd number of not blocks -> opposite of the result of wallInFront(world)
        if (conditions.length % 2 == 0) return getGameWorld().isPassableInFrontOfCharacter();
        else return ! getGameWorld().isPassableInFrontOfCharacter();

    }

    @Override
    public void execute() {
        if (isConditionValid(getGameWorld())) executeNextBodyBlock();
    }

    protected void executeNextBodyBlock() {
        // TODO: (after solving TODO BIG at class header !)
    }

    // TODO: methods for handling adding and removing body or condition blocks based on plugs etc.
}