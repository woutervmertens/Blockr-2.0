package com.swop.command;

import com.swop.blocks.Block;

public class DeleteBlockCommand extends ProgramAreaCommand {
    private Block block;

    public DeleteBlockCommand(Block block){
        this.block = block;
    }

    @Override
    public void execute() {
        programArea.removeBlockFromPA(block);
    }

    @Override
    public void undo() {
        programArea.dropBlock(block);
    }
}
