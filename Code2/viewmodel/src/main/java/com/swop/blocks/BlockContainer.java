package com.swop.blocks;

import com.swop.GameWorld;
import com.swop.SuccessState;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Queue;

public class BlockContainer {
    private List<BlockModel> AllBlocks;
    private Queue<BlockModel> BlockProgram;

    public void AddBlockToProgramFront(BlockModel block){
        BlockProgram.add(block);
    }
    public void AddBlockGroupToProgramFront(Collection<BlockModel> blocks){
        BlockProgram.addAll(blocks);
    }

    public BlockModel GetNextToExecute(){
        return BlockProgram.poll();
    }

    public void GenerateProgram(){
        BlockProgram.clear();
        //TODO
    }

    public List<Block> getAllBlockVMs() {
        List<Block> bs = new ArrayList<>();
        for (BlockModel bm : AllBlocks){
            bs.add(new Block(bm));
        }
        return bs;
    }
}
