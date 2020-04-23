package com.swop.command;

import com.swop.blocks.Block;
import com.swop.blocks.ConditionBlock;
import com.swop.blocks.StatementBlock;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class DeleteBlockCommand extends ProgramAreaCommand {
    private final Block block;
    //private List<Block> bodyBlocks;
    //private List<ConditionBlock> conditions;
    private final Point previousPosition;

    public DeleteBlockCommand(Block block) {
        this.block = block;
        if (block instanceof StatementBlock) {
            //bodyBlocks = new ArrayList<>(((StatementBlock) block).getBodyBlocks());
            //conditions = new ArrayList<>(((StatementBlock) block).getConditions());
        }
        previousPosition = block.getPreviousDropPosition();
    }

    @Override
    public void execute() {
        programArea.removeBlockFromPA(block);
    }

    @Override
    public void undo() {
//        if (block instanceof StatementBlock) {
//            ((StatementBlock) block).setBodyBlocks(bodyBlocks);
//            ((StatementBlock) block).setConditions(conditions);
//        }
        if (previousPosition != null) block.setPosition(previousPosition);
        programArea.dropBlock(block);
    }
}
