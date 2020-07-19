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

    public void setBlockProgram(Queue<BlockModel> blockProgram) {
        BlockProgram = blockProgram;
    }

    private Queue<BlockModel> BlockProgram = new LinkedList<>();

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
        BlockProgram.add(block);
    }
    public void AddBlockGroupToProgramFront(Collection<BlockModel> blocks){
        BlockProgram.addAll(blocks);
    }

    public void removeBlock(BlockModel blockModel) {
        AllBlocks.remove(blockModel);
        BlockProgram.remove(blockModel);
    }

    public BlockModel getParent(BlockModel blockModel) {
        return AllBlocks.stream().filter(x -> x.hasConnectedBlock(blockModel)).findFirst().orElse(null);
    }
}
