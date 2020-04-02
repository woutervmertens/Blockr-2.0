package com.swop.blocks;


import com.swop.worldElements.GameWorld;

import java.awt.*;
import java.util.List;

public class IfBlock extends StatementBlock {
    public IfBlock(Point position, GameWorld gameWorld, Condition[] conditions, List<ActionBlock> bodyBlocks) {
        super(position, gameWorld, conditions, bodyBlocks);
    }
}
