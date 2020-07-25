package com.swop;

import com.swop.blocks.BlockVM;
import com.swop.blocks.BlockFactory;
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
                gameController.setDraggedBlockVM(bm);
                RemoveBlock(bm);
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
        return model;
    }

    public void setModel(ProgramAreaModel model) {
        this.model = model;
    }

    public List<BlockVM> getAllBlocks(){
        return getAllBlockVMs();
    }

    /**
     * Checks all blocks for a connector link and adds the @param blockModel in the correct position, then reorders the blocks
     */
    public void DropBlock(BlockModel blockModel){
        assert isWithin(blockModel.getPosition().x,blockModel.getPosition().y);
        //Check AllBlocks for connector link and add block
        BlockVM b;
        for(BlockModel bm : model.getAllBlocks()){
            b = BlockFactory.getInstance().createBlockVM(bm);
            Connector c = b.getConnectorOrNull(blockModel.getPosition());
            if(c != null) {
                BlockModel bNext = b.getNext();
                b.setNext(blockModel);
                blockModel.setPosition(c.getPosition());
                BlockVM newBlockVM = BlockFactory.getInstance().createBlockVM(blockModel);
                newBlockVM.setNext(bNext);
                blockModel.setIsFirstFlag(false);
                break;
            }
        }
        model.getAllBlocks().add(blockModel);
        //Reorder block positions to fit actual blocks
        if(model.getParent(blockModel) == null) return;
        BlockVM parentBlockVM = BlockFactory.getInstance().createBlockVM(model.getParent(blockModel));
        fixBlockPositions(parentBlockVM);
    }

    /**
     * Removes @param blockModel from the program area and corrects the positions of the other blocks.
     */
    public void RemoveBlock(BlockModel blockModel){
        //Get parent
        BlockModel parent = model.getParent(blockModel);
        BlockVM parentBlockVM = null;
        if(parent != null) {
            parentBlockVM = BlockFactory.getInstance().createBlockVM(parent);
        }
        //Call remove on block
        BlockVM oldBlockVM = BlockFactory.getInstance().createBlockVM(blockModel);
        oldBlockVM.Remove(model);
        //Reorder block positions to fit actual blocks
        fixBlockPositions(parentBlockVM);
    }

    /***
     * Start correcting the model positions to the position of the parents connector position, starting after last correct Block @param startBlock
     */
    private void fixBlockPositions(BlockVM startBlockVM){
        if(startBlockVM == null) return;
        BlockModel nextModel = startBlockVM.getNext();
        BlockVM b;
        while(nextModel != null){
            b = BlockFactory.getInstance().createBlockVM(nextModel);
            b.updatePosition(startBlockVM.getNextPosition());
            startBlockVM = b;
            nextModel = startBlockVM.getNext();
        }
    }

    public SuccessState ExecuteNext(GameWorld gw){
        BlockVM b = GetNextToExecute();
        return (b == null)? SuccessState.FAILURE : b.Execute(gw,model);
    }

    /**
     * Generates a new program if none exists
     * Pops the first element blockModel and changes the highlight
     * Checks and flags if the blockModel is the last element, if not it sets the highlight for the next block
     * @return a Block made with blockModel
     */
    public BlockVM GetNextToExecute(){
        if(model.getBlockProgram().isEmpty()) GenerateProgram();

        if(model.getBlockProgram().isEmpty())return null;
        BlockModel blockModel = model.getBlockProgram().poll();
        blockModel.setHighlightState(false);

        BlockModel nextBlock = model.getBlockProgram().peek();
        if (nextBlock != null) {
            nextBlock.setHighlightState(true);
        }

        return BlockFactory.getInstance().createBlockVM(blockModel);
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
        model.AddBlockGroupToProgramFront(tempProgram);
        model.getBlockProgram().peek().setHighlightState(true);
    }

    /**
     * @return All of the ViewModels of all the blocks in the program area
     */
    public List<BlockVM> getAllBlockVMs() {
        List<BlockVM> bs = new ArrayList<>();
        for (BlockModel bm : model.getAllBlocks()){
            bs.add(BlockFactory.getInstance().createBlockVM(bm));
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
