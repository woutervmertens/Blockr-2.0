package com.swop.blocks;

import java.util.List;
import com.swop.worldElements.GameWorld;


public class WhileBlock extends StatementBlock {

    public WhileBlock(Condition[] conditions, List<ActionBlock> bodyBlocks) {
        super(conditions, bodyBlocks);
    }

    @Override
    public void execute(GameWorld world) {
        // TODO if (isValidCondition()) execute() // --> method from superclass
        // TODO: execute body using superclass method until condition is not satisfied anymore.
    }
}
