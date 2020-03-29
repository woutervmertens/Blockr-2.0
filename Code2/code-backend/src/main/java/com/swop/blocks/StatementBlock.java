package com.swop.blocks;

import com.swop.worldElements.Direction;
import com.swop.worldElements.GameWorld;

import java.util.LinkedList;

public abstract class StatementBlock extends Block {
    Condition[] conditions;

    public boolean isConditionValid() {
        // TODO check on the game world whether the list of conditions returns true or not
        return false;
    }

    @Override
    public void execute() {
        // TODO: check condition and execute body once !
    }

    private void executeBodyOnce() {
        // TODO:
    }
}

enum Condition {
    WIF, NOT;
}