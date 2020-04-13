package com.swop.command;

import com.swop.blocks.Block;

public class AddBlockCommand extends ProgramAreaCommand {
    private Block block;

    public AddBlockCommand(Block block){
        this.block = block;
    }

    @Override
    public void execute() {
        programArea.dropBlock(block);
    }

    @Override
    public void undo() {
        programArea.removeBlockFromPA(block);
    }
}
