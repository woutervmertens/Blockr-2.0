package com.swop;

import com.swop.blocks.Block;
import com.swop.blocks.BlockContainer;

import java.awt.*;
import java.util.List;

public class ProgramAreaViewModel extends WindowSection{
    private BlockContainer blocks = new BlockContainer();
    public ProgramAreaViewModel(Point pos, int width, int height) {
        super(pos, width, height);
    }
    public List<Block> getAllBlocks(){
        return blocks.getAllBlockVMs();
    }
}
