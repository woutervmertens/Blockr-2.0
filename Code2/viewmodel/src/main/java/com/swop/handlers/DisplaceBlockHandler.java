package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.blocks.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisplaceBlockHandler {
    //private final SharedData sharedData;
//
    //public DisplaceBlockHandler(SharedData sharedData) {
    //    this.sharedData = sharedData;
    //}
//
    ///**
    // * Handles logic for when a block is dropped in the Program Area.
    // * It handles the BiMap addition, the drop position and the highlight.
    // *
    // * @custom.pre draggedBlock.getPosition() is inside the PA.
    // * @param draggedBlock The dropped block.
    // * @param x The x coordinate of the drop.
    // * @param y The y coordinate of the drop.
    // */
    //public void handleReleaseInPAAt(UIBlock draggedBlock, int x, int y) {
    //    // 1) Handle map
    //    if (sharedData.getCorrespondingBlockFor(draggedBlock) == null) {
    //        sharedData.makeNewCorrespondingBlock(draggedBlock);
    //        sharedData.getCorrespondingBlockFor(draggedBlock).setBlockrGame(sharedData.getBlockrGame());
    //    }
//
    //    // 2) Handle drop position
    //    BlockModel backendBlockModel = sharedData.getCorrespondingBlockFor(draggedBlock);
    //    BlockrGame blockrGame = sharedData.getBlockrGame();
    //    blockrGame.dropBlockInPAAt(backendBlockModel, x, y);
    //    draggedBlock.setPosition(blockrGame.getBlockPosition(backendBlockModel));
//
    //    // 3) Handle highlight
    //    sharedData.setHighlightedBlock(sharedData.getCorrespondingUiBlockFor(blockrGame.getToHighlightBlock()));
    //}
//
    ///**
    // * Handles logic for when a block is dropped outside the Program Area.
    // * It removes all bodies and conditions connected to this Block from the Program Area and then removes the Block.
    // * Then it sets the highlight.
    // *
    // * @param draggedBlock The dropped block.
    // */
    //public void handleReleaseOutsidePA(UIBlock draggedBlock) {
    //    BlockrGame blockrGame = sharedData.getBlockrGame();
    //    BlockModel backendBlockModel = sharedData.getCorrespondingBlockFor(draggedBlock);
    //    if (backendBlockModel != null) {
    //        // Remove all bodies and conditions as well from program area (eventually all call blocks)
    //        if (backendBlockModel instanceof BlockModelWithBody) {
    //            List<BlockModel> newBodyBlockModels = new ArrayList<>(((BlockModelWithBody) backendBlockModel).getBodyBlockModels());
    //            Collections.reverse(newBodyBlockModels);  // Reversing is needed for correct undo
    //            for (BlockModel bodyBlockModel : newBodyBlockModels) {
    //                handleReleaseOutsidePA(sharedData.getCorrespondingUiBlockFor(bodyBlockModel));  // recursion
    //            }
    //            if (backendBlockModel instanceof StatementBlockModel) {
    //                List<ConditionBlockModel> newConditions = new ArrayList<>(((StatementBlockModel) backendBlockModel).getConditions());
    //                Collections.reverse(newConditions);  // Reversing is needed for correct undo
    //                for (BlockModel condition : newConditions) {
    //                    blockrGame.removeBlockFromPA(condition, true);
    //                }
    //            } else if (backendBlockModel instanceof FunctionDefinitionBlockModel) {
    //                for (FunctionCallBlockModel call: new ArrayList<>(((FunctionDefinitionBlockModel) backendBlockModel).getCalls())) {
    //                    blockrGame.removeBlockFromPA(call, true);
    //                    ((FunctionDefinitionBlockModel) backendBlockModel).removeCall(call);
    //                }
    //            }
    //        }  else if (backendBlockModel instanceof FunctionCallBlockModel) {
    //            ((FunctionCallBlockModel) backendBlockModel).getDefinitionBlock().removeCall((FunctionCallBlockModel) backendBlockModel);
    //        }
//
    //        //remove the block from program area
    //        blockrGame.removeBlockFromPA(backendBlockModel, true);
    //    }
//
    //    sharedData.setHighlightedBlock(sharedData.getCorrespondingUiBlockFor(blockrGame.getToHighlightBlock()));
    //}
//
    ///**
    // * Gets all the UIBlocks in the Program Area.
    // *
    // * @return A list of UIBlocks.
    // */
    //public List<UIBlock> getAllUIBlocksInPA() {
    //    List<BlockModel> backBlockModels = sharedData.getBlockrGame().getAllBlocksInPA();
    //    List<UIBlock> returnUIBlocks = new ArrayList<>();
    //    for (BlockModel blockModel : backBlockModels) {
    //        returnUIBlocks.add(sharedData.getCorrespondingUiBlockFor(blockModel));
    //    }
    //    return returnUIBlocks;
    //}
//
    ///**
    // * Handles the logic when a block is clicked.
    // * It sets the previous drop position for an undo, removes the Block from the Program Area, adjusts the Program Area and sets the highlight.
    // *
    // * @param clickedBlock The clicked block.
    // */
    //public void handleClickOn(UIBlock clickedBlock) {
    //    if (clickedBlock == null) throw new IllegalArgumentException();
    //    BlockModel backendBlockModel = sharedData.getCorrespondingBlockFor(clickedBlock);
    //    backendBlockModel.setPreviousDropPosition(backendBlockModel.getPosition());
    //    sharedData.getBlockrGame().removeBlockFromPA(backendBlockModel, false);
    //    sharedData.adjustAllBlockPositions();
    //    sharedData.adjustAllBodyBlockGaps();
    //    sharedData.setHighlightedBlock(sharedData.getCorrespondingUiBlockFor(sharedData.getBlockrGame().getToHighlightBlock()));
    //}
}//
//