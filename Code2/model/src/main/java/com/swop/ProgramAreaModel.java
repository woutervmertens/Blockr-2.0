package com.swop;

import com.swop.blocks.BlockModel;

import java.util.*;

/**
 * The data for the Program Area Window
 */
public class ProgramAreaModel implements Cloneable{
    public List<BlockModel> getAllBlocks() {
        return AllBlocks;
    }

    public void setAllBlocks(List<BlockModel> allBlocks) {
        AllBlocks = allBlocks;
    }

    private List<BlockModel> AllBlocks = new ArrayList<>();

    public Deque<BlockModel> getBlockProgram() {
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
        ProgramAreaModel cm = new ProgramAreaModel();
        List<BlockModel> allclone = new ArrayList<>();
        for(BlockModel bm : getAllBlocks()) allclone.add(bm.clone());
        cm.setAllBlocks(allclone);
        ArrayDeque<BlockModel> progclone = new ArrayDeque<>();
        for(BlockModel bm : getBlockProgram()) progclone.add(bm.clone());
        cm.setBlockProgram(progclone);
        return cm;
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
