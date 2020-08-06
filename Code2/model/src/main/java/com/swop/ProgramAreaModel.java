package com.swop;

import com.swop.blocks.BlockModel;

import java.util.*;

/**
 * The data for the Program Area Window.
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

    /**
     * Adds a BlockModel to the front of the program DeQue.
     * @param block the BlockModel to add
     */
    public void AddBlockToProgramFront(BlockModel block){
        BlockProgram.addFirst(block);
    }

    /**
     * Adds a list of BlockModels to the front of the program DeQue.
     * @param blocks the list of BlockModels to add
     */
    public void AddBlockGroupToProgramFront(List<BlockModel> blocks){
        for (int i = blocks.size() - 1; i >= 0; i--) {
            BlockProgram.addFirst(blocks.get(i));
        }
    }

    /**
     * Removes a BlockModel from the program DeQue and the list of all blocks.
     * @param blockModel the BlockModel to remove
     */
    public void removeBlock(BlockModel blockModel) {
        AllBlocks.remove(blockModel);
        BlockProgram.remove(blockModel);
    }

    /**
     * Finds the parent of the given BlockModel.
     * @param blockModel the lost child
     * @return the parent BlockModel or null
     */
    public BlockModel getParent(BlockModel blockModel) {
        return AllBlocks.stream().filter(x -> x.hasConnectedBlock(blockModel)).findFirst().orElse(null);
    }
}
