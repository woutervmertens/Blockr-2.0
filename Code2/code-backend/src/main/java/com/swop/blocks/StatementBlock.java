package com.swop.blocks;

import com.swop.worldElements.Direction;
import com.swop.worldElements.GameWorld;

import java.util.LinkedList;
import java.util.List;

public abstract class StatementBlock extends Block {

    public StatementBlock(Condition[] conditions, List<ActionBlock> bodyBlocks) {
        if (conditions.length == 0) throw new IllegalArgumentException("Cannot make statementBlock without conditions");
        this.conditions = conditions;
        this.bodyBlocks = bodyBlocks;
    }

    protected Condition[] conditions;
    protected List<ActionBlock> bodyBlocks;

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