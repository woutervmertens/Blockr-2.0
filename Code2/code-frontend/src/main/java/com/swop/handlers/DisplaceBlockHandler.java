package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.blocks.*;
import com.swop.uiElements.UIBlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisplaceBlockHandler {
    private final SharedData sharedData;

    public DisplaceBlockHandler(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    /**
     * Handles logic for when a block is dropped in the Program Area.
     * It handles the BiMap addition, the drop position and the highlight.
     *
     * @pre draggedBlock.getPosition() is inside the PA.
     * @param draggedBlock The dropped block.
     * @param x The x coordinate of the drop.
     * @param y The y coordinate of the drop.
     */
    public void handleReleaseInPAAt(UIBlock draggedBlock, int x, int y) {
        // 1) Handle map
        if (sharedData.getCorrespondingBlockFor(draggedBlock) == null) {
            sharedData.makeNewCorrespondingBlock(draggedBlock);
            sharedData.getCorrespondingBlockFor(draggedBlock).setBlockrGame(sharedData.getBlockrGame());
        }

        // 2) Handle drop position
        Block backendBlock = sharedData.getCorrespondingBlockFor(draggedBlock);
        BlockrGame blockrGame = sharedData.getBlockrGame();
        blockrGame.dropBlockInPAAt(backendBlock, x, y);
        draggedBlock.setPosition(blockrGame.getBlockPosition(backendBlock));

        // 3) Handle highlight
        sharedData.setHighlightedBlock(sharedData.getCorrespondingUiBlockFor(blockrGame.getToHighlightBlock()));
    }

    /**
     * Handles logic for when a block is dropped outside the Program Area.
     * It removes all bodies and conditions connected to this Block from the Program Area and then removes the Block.
     * Then it sets the highlight.
     *
     * @param draggedBlock The dropped block.
     */
    public void handleReleaseOutsidePA(UIBlock draggedBlock) {
        BlockrGame blockrGame = sharedData.getBlockrGame();
        Block backendBlock = sharedData.getCorrespondingBlockFor(draggedBlock);
        if (backendBlock != null) {
            // TODO: recursively remove all bodies
            // Remove all bodies and conditions as well from program area
            if (backendBlock instanceof BlockWithBody) {
                List<Block> newBodyBlocks = new ArrayList<>(((BlockWithBody) backendBlock).getBodyBlocks());
                Collections.reverse(newBodyBlocks);  // Reversing is needed for correct undo
                for (Block bodyBlock : newBodyBlocks) {
                    blockrGame.removeBlockFromPA(bodyBlock, true);
                }
                if (backendBlock instanceof StatementBlock) {
                    List<ConditionBlock> newConditions = new ArrayList<>(((StatementBlock) backendBlock).getConditions());
                    Collections.reverse(newConditions);  // Reversing is needed for correct undo
                    for (Block condition : newConditions) {
                        blockrGame.removeBlockFromPA(condition, true);
                    }
                } else if (backendBlock instanceof FunctionDefinitionBlock) {
                    for (FunctionCallBlock call: new ArrayList<>(((FunctionDefinitionBlock) backendBlock).getCalls())) {
                        blockrGame.removeBlockFromPA(call, true);
                        ((FunctionDefinitionBlock) backendBlock).removeCall(call);
                    }
                }
            }  else if (backendBlock instanceof FunctionCallBlock) {
                ((FunctionCallBlock) backendBlock).getDefinitionBlock().removeCall((FunctionCallBlock) backendBlock);
            }

            //remove the block from program area
            blockrGame.removeBlockFromPA(backendBlock, true);
        }

        sharedData.setHighlightedBlock(sharedData.getCorrespondingUiBlockFor(blockrGame.getToHighlightBlock()));
    }

    /**
     * Gets all the UIBlocks in the Program Area.
     *
     * @return A list of UIBlocks.
     */
    public List<UIBlock> getAllUIBlocksInPA() {
        List<Block> backBlocks = sharedData.getBlockrGame().getAllBlocksInPA();
        List<UIBlock> returnUIBlocks = new ArrayList<>();
        for (Block block : backBlocks) {
            returnUIBlocks.add(sharedData.getCorrespondingUiBlockFor(block));
        }
        return returnUIBlocks;
    }

    /**
     * Handles the logic when a block is clicked.
     * It sets the previous drop position for an undo, removes the Block from the Program Area, adjusts the Program Area and sets the highlight.
     *
     * @param clickedBlock The clicked block.
     */
    public void handleClickOn(UIBlock clickedBlock) {
        if (clickedBlock == null) throw new IllegalArgumentException();
        Block backendBlock = sharedData.getCorrespondingBlockFor(clickedBlock);
        backendBlock.setPreviousDropPosition(backendBlock.getPosition());
        sharedData.getBlockrGame().removeBlockFromPA(backendBlock, false);
        sharedData.adjustAllBlockPositions();
        sharedData.adjustAllBodyBlockGaps();
        sharedData.setHighlightedBlock(sharedData.getCorrespondingUiBlockFor(sharedData.getBlockrGame().getToHighlightBlock()));
    }
}
