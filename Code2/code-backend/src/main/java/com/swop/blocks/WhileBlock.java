package com.swop.blocks;

import java.awt.*;
import java.util.List;
import com.swop.worldElements.GameWorld;


public class WhileBlock extends StatementBlock {

    public WhileBlock(Point position, GameWorld gameWorld, Condition[] conditions, List<ActionBlock> bodyBlocks) {
        super(position, gameWorld, conditions, bodyBlocks);
    }

    @Override
    public void executeNextBodyBlock() {
        // TODO
    }
}
