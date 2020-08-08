package com.swop;

import com.swop.blocks.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The logic for the ProgramArea View.
 */
public class ProgramAreaViewModel extends ScrollableViewModel {
    private ProgramAreaModel model = new ProgramAreaModel();
    private WindowGameControllerFacade gameController;
    public ProgramAreaViewModel(Point pos, int width, int height, WindowGameControllerFacade gameController) {
        super(pos, width, height);
        this.gameController = gameController;
    }

    /**
     * React to a MousePress on (x,y):
     *  checks all the blocks to find the one being pressed
     *  set the dragged block in the GameController
     *  remove the block from the program area data
     *  if no block was pressed, pass the call along to the scrollbar
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
    @Override
    public void HandleMousePress(int x, int y) {
        int oldy = y;
        if(!isWithin(x,y)) return;
        y = offsetScrollPosition(y);
        for (BlockModel bm : model.getAllBlocks())
        {
            if(bm.isWithin(x,y)) {
                gameController.setDraggedBlockVM(bm);
                RemoveBlock(bm);
                return;
            }
        }
        scrollBarViewModel.HandleMousePress(x,oldy);
    }

    /**
     * React to a MouseRelease on (x,y):
     *  Passes the call along to the scrollbar
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
    @Override
    public void HandleMouseRelease(int x, int y) {
        scrollBarViewModel.HandleMouseRelease(x,y);
    }

    /**
     * React to a MouseDrag on (x,y):
     *  Passes the call along to the scrollbar
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
    @Override
    public void HandleMouseDrag(int x, int y) {
        scrollBarViewModel.HandleMouseDrag(x,y);
    }

    /**
     * React to a Reset call:
     *  Generates a new program.
     */
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
     * Checks all blocks for a connector link and adds the blockModel in the correct position, then reorders the blocks and adapts the scrollbar.
     * @param blockModel the BlockModel to drop
     */
    public void DropBlock(BlockModel blockModel){
        blockModel.setPosition(new Point(blockModel.getPosition().x,offsetScrollPosition(blockModel.getPosition().y)));
        //Check AllBlocks for connector link and add block
        BlockVM parentVM;
        for(BlockModel bm : model.getAllBlocks()){
            parentVM = BlockFactory.getInstance().createBlockVM(bm);
            Connector c = parentVM.getConnectorOrNull(blockModel.getPosition(), blockModel.getBlockModelType());
            if(c != null) {
                //Handle the parent next and retrieve new blocks' next
                BlockModel bNext = null;
                switch (c.getType()){
                    case NEXT:
                        bNext = parentVM.getNext();
                        parentVM.setNext(blockModel);
                        break;
                    case BODY:
                        bNext = ((BlockVMWithBody)parentVM).getFirstBodyBlock();
                        ((BlockVMWithBody)parentVM).setFirstBodyBlock(blockModel);
                        break;
                    case CONDITION:
                        bNext = ((StatementBlockVM)parentVM).getFirstCondition();
                        ((StatementBlockVM)parentVM).setFirstCondition(blockModel);
                        break;
                }
                //Snap new block into place
                blockModel.setPosition(c.getPosition());
                //Set next of new block
                blockModel.setIsFirstFlag(false);
                BlockVM newBlockVM = BlockFactory.getInstance().createBlockVM(blockModel);
                newBlockVM.setNext(bNext);
                break;
            }
        }
        model.getAllBlocks().add(blockModel);
        //Reorder block positions to fit actual blocks
        fixAllBlockPositions();
        adaptScrollbar();
    }

    /**
     * Removes @param blockModel from the program area and corrects the positions of the other blocks.
     *
     * @param blockModel the BlockModel to remove
     */
    public void RemoveBlock(BlockModel blockModel){
        //Get parent
        BlockModel parent = model.getParent(blockModel);
        //Call remove on block
        BlockVM oldBlockVM = BlockFactory.getInstance().createBlockVM(blockModel);
        oldBlockVM.Remove(parent);
        model.removeBlock(blockModel);
        //Reorder block positions to fit actual blocks
        fixAllBlockPositions();
    }

    /**
     * Call fixBlockPosition for every BlockModel
     */
    private void fixAllBlockPositions(){
        List<BlockModel> models = model.getAllBlocks();
        for (BlockModel model : models)
        {
            fixBlockPositions(BlockFactory.getInstance().createBlockVM(model));
        }
    }

    /**
     * Start correcting the model positions to the position of the parents connector position, starting after last correct Block @param startBlock
     */
    private void fixBlockPositions(BlockVM startBlockVM){
        if(startBlockVM == null) return;
        startBlockVM.updatePosition(startBlockVM.getPosition());
        BlockModel nextModel = startBlockVM.getNext();
        BlockVM b;
        while(nextModel != null){
            b = BlockFactory.getInstance().createBlockVM(nextModel);
            b.updatePosition(startBlockVM.getNextPosition());
            startBlockVM = b;
            nextModel = startBlockVM.getNext();
        }
    }

    /**
     * Gets the next BlockModel to execute and calls execute on it
     * @param gw the GameWorld
     * @return the SuccessState of the execution.
     */
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
    private BlockVM GetNextToExecute(){
        if(model.getBlockProgram().isEmpty())return null;
        BlockModel blockModel = model.getBlockProgram().poll();
        blockModel.setHighlightState(false);

        return BlockFactory.getInstance().createBlockVM(blockModel);
    }

    /**
     * Marks the highlight flag in the next BlockModel
     */
    public void setHighlight(){
        BlockModel nextBlock = model.getBlockProgram().peek();
        if (nextBlock != null) {
            nextBlock.setHighlightState(true);
        }
    }

    /**
     * Finds the all the blockModels marked as "First"
     * @return a List of BlockModels
     */
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
     *  - Iterate through the blocks and add them to the program (don't handle bodies)
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
     * If any BlockModel is in the ScrollBuffer; activates the scrollbar and increases the size of the scrollable area.
     */
    private void adaptScrollbar(){
        Optional o = model.getAllBlocks().stream().filter(x -> isInScrollBuffer(x.getPosition())).findAny();
        if(o.isPresent()) {
            scrollBarViewModel.setActive(true);
            increaseSize();
        }
    }

    /**
     * @return number of blocks in Program Area
     */
    public int getNumBlocksUsed() {
        return model.getAllBlocks().size();
    }
}
