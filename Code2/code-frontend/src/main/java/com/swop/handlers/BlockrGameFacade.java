package com.swop.handlers;

import com.swop.*;
import com.swop.blocks.Block;
import com.swop.uiElements.UIBlock;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlockrGameFacade {
    DisplaceBlockHandler displaceBlockHandler;
    ExecuteProgramHandler executeProgramHandler;
    BlockrGame blockrGame;
    // Make blockUIMap and share the REFERENCE to all handlers who need it
    Map<Block, UIBlock> blockUIBlockMap = new HashMap<>();

    public BlockrGameFacade(int maxBlocks, GameWorldType type){
        blockrGame = new BlockrGame(maxBlocks,type);
        displaceBlockHandler = new DisplaceBlockHandler(blockrGame, blockUIBlockMap);
        executeProgramHandler = new ExecuteProgramHandler(blockrGame, blockUIBlockMap);
    }

    public Collection<Action> getSupportedActions(){
        return blockrGame.getGameWorldType().getSupportedActions();
    }

    public Collection<Predicate> getSupportedPredicates(){
        return blockrGame.getGameWorldType().getSupportedPredicates();
    }

    public boolean isPaletteHidden(){
        return blockrGame.isPaletteHidden();
    }

    public List<UIBlock> getAllUIBlocksInPA(){
        return displaceBlockHandler.getAllUIBlocksInPA();
    }

    public GameWorld getGameWorld(){
        return blockrGame.getGameWorld();
    }

    public int getNumBlocksInPA() {
        return blockrGame.getNumBlocksInPA();
    }

    public void handleProgramAreaForClickOn(UIBlock clickedBlock){
        displaceBlockHandler.handleProgramAreaForClickOn(clickedBlock);
    }

    public void handleReleaseInPA(UIBlock draggedBlock){
        displaceBlockHandler.handleReleaseInPA(draggedBlock);
    }

    public void handleReleaseOutsidePA(UIBlock draggedBlock){
        displaceBlockHandler.handleReleaseOutsidePA(draggedBlock);
    }

    public void adjustAllBlockPositions(){
        displaceBlockHandler.adjustAllBlockPositions();
    }

    public void adjustAllStatementBlockGaps(){
        displaceBlockHandler.adjustAllStatementBlockGaps();
    }

    public UIBlock getCorrespondingUiBlockFor(Block block){
        return displaceBlockHandler.getCorrespondingUiBlockFor(block);
    }

    public Block getBlockInPaAt(int x, int y){
        return blockrGame.getBlockInPaAt(x,y);
    }

    public void executeNext(){
        executeProgramHandler.executeNext();
    }

    public void undo() {
        blockrGame.undoCommand();
    }

    public void redo() {
        blockrGame.redoCommand();
    }

    public void reset() {
        blockrGame.resetEverything();
    }
}
