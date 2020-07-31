package com.swop;

import com.swop.blocks.BlockModel;

import java.util.*;

public class ProgramAreaModel implements Cloneable{
    public List<BlockModel> getAllBlocks() {
        return AllBlocks;
    }

    public void setAllBlocks(List<BlockModel> allBlocks) {
        AllBlocks = allBlocks;
    }

    private List<BlockModel> AllBlocks = new ArrayList<>();

    public Queue<BlockModel> getBlockProgram() {
        return BlockProgram;
    }

    public void setBlockProgram(Deque<BlockModel> blockProgram) {
        BlockProgram = blockProgram;
    }

    private Deque<BlockModel> BlockProgram = new LinkedList<>();

    /**
     * @return Returns a clone of the given block.
     */
    public ProgramAreaModel clone() {
        try {
            return (ProgramAreaModel) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void AddBlockToProgramFront(BlockModel block){
        BlockProgram.addFirst(block);
    }
    public void AddBlockGroupToProgramFront(List<BlockModel> blocks){
        for (int i = blocks.size() - 1; i >= 0; i--) {
            BlockProgram.addFirst(blocks.get(i));
        }
    }

    public void removeBlock(BlockModel blockModel) {
        AllBlocks.remove(blockModel);
        BlockProgram.remove(blockModel);
    }

    public BlockModel getParent(BlockModel blockModel) {
        return AllBlocks.stream().filter(x -> x.hasConnectedBlock(blockModel)).findFirst().orElse(null);
    }
}
