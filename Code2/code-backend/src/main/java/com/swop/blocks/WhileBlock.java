package com.swop.blocks;


import java.util.List;

public class WhileBlock extends StatementBlock {

    public WhileBlock(Condition[] conditions, List<ActionBlock> bodyBlocks) {
        super(conditions, bodyBlocks);
    }

    @Override
    public void execute() {
        // TODO if (isValidCondition()) execute() // --> method from superclass
        // TODO: execute body using superclass method until condition is not satisfied anymore.
    }
}
