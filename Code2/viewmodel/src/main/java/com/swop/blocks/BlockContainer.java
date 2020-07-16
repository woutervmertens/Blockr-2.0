package com.swop.blocks;

import java.util.*;

//TODO: Implements cloneable?
public class BlockContainer {
    private List<BlockModel> AllBlocks = new ArrayList<>();
    private Queue<BlockModel> BlockProgram = new LinkedList<>();

    public void AddBlockToProgramFront(BlockModel block){
        BlockProgram.add(block);
    }
    public void AddBlockGroupToProgramFront(Collection<BlockModel> blocks){
        BlockProgram.addAll(blocks);
    }

    /**
     * Generates a new program if none exists
     * Pops the first element blockModel and changes the highlight
     * Checks and flags if the blockModel is the last element, if not it sets the highlight for the next block
     * @return a Block made with blockModel
     */
    public Block GetNextToExecute(){
        if(BlockProgram.isEmpty()) GenerateProgram();

        BlockModel blockModel = BlockProgram.poll();
        blockModel.setHighlightState(false);

        BlockModel nextBlock = BlockProgram.peek();
        if (nextBlock != null) {
            nextBlock.setHighlightState(true);
        } else {
            blockModel.flagLastBlock();
        }

        return new Block(blockModel);
    }

    public void GenerateProgram(){
        BlockProgram.clear();
        //TODO
        BlockProgram.peek().setHighlightState(true);
    }

    public List<Block> getAllBlockVMs() {
        List<Block> bs = new ArrayList<>();
        for (BlockModel bm : AllBlocks){
            bs.add(new Block(bm));
        }
        return bs;
    }
}
