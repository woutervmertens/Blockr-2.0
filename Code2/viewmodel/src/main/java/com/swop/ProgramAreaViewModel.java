package com.swop;

import com.swop.blocks.Block;
import com.swop.blocks.BlockModel;
import com.swop.blocks.Connector;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramAreaViewModel extends ScrollableViewModel {
    private ProgramAreaModel model = new ProgramAreaModel();
    private GameController gameController;
    public ProgramAreaViewModel(Point pos, int width, int height, GameController gameController) {
        super(pos, width, height);
        this.gameController = gameController;
    }

    @Override
    public void HandleMousePress(int x, int y) {
        if(!isWithin(x,y)) return;
        for (BlockModel bm : model.getAllBlocks())
        {
            if(bm.isWithin(x,y)) {
                gameController.setDraggedBlock(bm);
                return;
            }
        }
        scrollBarViewModel.HandleMousePress(x,y);
    }

    @Override
    public void HandleMouseRelease(int x, int y) {
        scrollBarViewModel.HandleMouseRelease(x,y);
    }

    @Override
    public void HandleMouseDrag(int x, int y) {
        scrollBarViewModel.HandleMouseDrag(x,y);
    }

    @Override
    public void HandleReset() {
        GenerateProgram();
    }

    @Override
    public ProgramAreaModel getModel() {
        return getModel();
    }

    public void setModel(ProgramAreaModel model) {
        this.model = model;
    }

    public List<Block> getAllBlocks(){
        return getAllBlockVMs();
    }

    /**
     * Checks all blocks for a connector link and adds the @param blockModel in the correct position, then reorders the blocks
     */
    public void DropBlock(BlockModel blockModel){
        assert isWithin(blockModel.getPosition().x,blockModel.getPosition().y);
        //Check AllBlocks for connector link and add block
        Block b;
        for(BlockModel bm : model.getAllBlocks()){
            b = new Block(bm);
            Connector c = b.getConnectorOrNull(bm.getPosition());
            if(c != null) {
                BlockModel bNext = b.getNext();
                b.setNext(blockModel);
                blockModel.setPosition(c.getPosition());
                Block newBlock = new Block(blockModel);
                newBlock.setNext(bNext);
                break;
            }
        }
        model.getAllBlocks().add(blockModel);
        //Reorder block positions to fit actual blocks
        Block parentBlock = new Block(model.getParent(blockModel));
        fixBlockPositions(parentBlock);
    }

    /**
     * Removes @param blockModel from the program area and corrects the positions of the other blocks.
     */
    public void RemoveBlock(BlockModel blockModel){
        //Get parent
        Block parentBlock = new Block(model.getParent(blockModel));
        //Call remove on block
        Block oldBlock = new Block(blockModel);
        oldBlock.Remove(model);
        //Reorder block positions to fit actual blocks
        fixBlockPositions(parentBlock);
    }

    /***
     * Start correcting the model positions to the position of the parents connector position, starting after last correct Block @param startBlock
     */
    private void fixBlockPositions(Block startBlock){
        if(startBlock == null) return;
        BlockModel nextModel = startBlock.getNext();
        Block b;
        while(nextModel != null){
            b = new Block(nextModel);
            b.updatePosition(startBlock.getNextPosition());
            startBlock = b;
            nextModel = startBlock.getNext();
        }
    }

    public SuccessState ExecuteNext(GameWorld gw){
        return GetNextToExecute().Execute(gw,model);
    }

    /**
     * Generates a new program if none exists
     * Pops the first element blockModel and changes the highlight
     * Checks and flags if the blockModel is the last element, if not it sets the highlight for the next block
     * @return a Block made with blockModel
     */
    public Block GetNextToExecute(){
        if(model.getBlockProgram().isEmpty()) GenerateProgram();

        BlockModel blockModel = model.getBlockProgram().poll();
        blockModel.setHighlightState(false);

        BlockModel nextBlock = model.getBlockProgram().peek();
        if (nextBlock != null) {
            nextBlock.setHighlightState(true);
        } else {
            blockModel.flagLastBlock();
        }

        return new Block(blockModel);
    }

    private List<BlockModel> findFirstBlock(){
        List<BlockModel> firsts = new ArrayList<>();
        for(BlockModel bm : model.getAllBlocks()){
            if(bm.isFirst()) firsts.add(bm);
        }
        return firsts;
    }

    /**
     * Clears the current program
     * Generates a new one if all conditions are met:
     *  - Only one group of blocks (except function definitions)
     *  -> Iterate through the blocks and add them to the program (don't handle bodies)
     */
    public void GenerateProgram(){
        model.getBlockProgram().clear();

        List<BlockModel> firsts = findFirstBlock();
        if(firsts.size() != 1) return;

        BlockModel block = firsts.get(0);
        ArrayList<BlockModel> tempProgram = new ArrayList<>();

        while(!block.checkLastBlockFlag()){
            tempProgram.add(block);
            block = block.getNext();
        }
        tempProgram.add(block);

        model.getBlockProgram().peek().setHighlightState(true);
    }

    /**
     * @return All of the ViewModels of all the blocks in the program area
     */
    public List<Block> getAllBlockVMs() {
        List<Block> bs = new ArrayList<>();
        for (BlockModel bm : model.getAllBlocks()){
            bs.add(new Block(bm));
        }
        return bs;
    }

    /**
     * @return number of blocks in Program Area
     */
    public int getNumBlocksUsed() {
        return model.getAllBlocks().size();
    }
}
