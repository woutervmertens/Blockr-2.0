package com.swop.blocks;


import java.util.List;

public class IfBlock extends StatementBlock {
    public IfBlock(Condition[] conditions, List<ActionBlock> bodyBlocks) {
        super(conditions, bodyBlocks);
    }
}
