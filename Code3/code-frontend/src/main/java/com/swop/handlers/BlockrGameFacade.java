package com.swop.handlers;

import com.swop.*;
import com.swop.blocks.Block;
import com.swop.uiElements.UIBlock;

import java.util.Collection;
import java.util.List;

public class BlockrGameFacade {
    private DisplaceBlockHandler displaceBlockHandler;
    private ExecuteProgramHandler executeProgramHandler;
    private SharedData sharedData;

    public BlockrGameFacade(SharedData sharedData){
        this.sharedData = sharedData;
        displaceBlockHandler = new DisplaceBlockHandler(sharedData);
        executeProgramHandler = new ExecuteProgramHandler(sharedData);
    }

    public Collection<Action> getSupportedActions(){
        return sharedData.getBlockrGame().getGameWorldType().getSupportedActions();
    }

    public Collection<Predicate> getSupportedPredicates(){
        return sharedData.getBlockrGame().getGameWorldType().getSupportedPredicates();
    }

    public Collection<UIBlock> getFunctionDefinitions(){
        return sharedData.getFunctionDefinitions();
    }

    public boolean isPaletteHidden(){
        return sharedData.getBlockrGame().isPaletteHidden();
    }

    public List<UIBlock> getAllUIBlocksInPA(){
        return displaceBlockHandler.getAllUIBlocksInPA();
    }

    public GameWorld getGameWorld(){
        return sharedData.getBlockrGame().getGameWorld();
    }

    public int getNumBlocksInPA() {
        return sharedData.getBlockrGame().getNumBlocksInPA();
    }

    public void handleProgramAreaForClickOn(UIBlock clickedBlock){
        displaceBlockHandler.handleClickOn(clickedBlock);
    }

    public void handleReleaseInPAAt(UIBlock draggedBlock, int x, int y){
        displaceBlockHandler.handleReleaseInPAAt(draggedBlock, x, y);
    }

    public void handleReleaseOutsidePA(UIBlock draggedBlock){
        displaceBlockHandler.handleReleaseOutsidePA(draggedBlock);
    }

    public void adjustAllBlockPositions(){
        sharedData.adjustAllBlockPositions();
    }

    public void adjustAllStatementBlockGaps(){
        sharedData.adjustAllBodyBlockGaps();
    }

    public UIBlock getCorrespondingUiBlockFor(Block block){
        return sharedData.getCorrespondingUiBlockFor(block);
    }

    public Block getCorrespondingBlockFor(UIBlock uiBlock) {
        return sharedData.getCorrespondingBlockFor(uiBlock);
    }

    public Block getBlockInPaAt(int x, int y){
        return sharedData.getBlockrGame().getBlockInPaAt(x,y);
    }

    public void executeNext(){
        executeProgramHandler.executeNext();
    }

    public void undo() {
        sharedData.getBlockrGame().undoCommand();
        sharedData.handleHighlight();
    }

    public void redo() {
        sharedData.getBlockrGame().redoCommand();
        sharedData.handleHighlight();
    }

    public void reset() {
        sharedData.getBlockrGame().resetIfNeeded();
        sharedData.handleHighlight();
    }
}
