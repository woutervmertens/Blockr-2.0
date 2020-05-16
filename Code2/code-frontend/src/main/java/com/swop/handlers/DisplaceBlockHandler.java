package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.blocks.Block;
import com.swop.blocks.ConditionBlock;
import com.swop.blocks.StatementBlock;
import com.swop.uiElements.UIBlock;
import com.swop.uiElements.UIStatementBlock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DisplaceBlockHandler {
    private final SharedData sharedData;

    public DisplaceBlockHandler(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    /**
     * @pre draggedBlock.getPosition() is inside the PA
     */
    public void handleReleaseInPA(UIBlock draggedBlock) {
        if (draggedBlock.getCorrespondingBlock() == null) {
            draggedBlock.makeNewCorrespondingBlock();
            draggedBlock.getCorrespondingBlock().setBlockrGame(sharedData.getBlockrGame());
        }

        Block backendBlock = draggedBlock.getCorrespondingBlock();
        sharedData.putInBlockUIBlockMap(backendBlock, draggedBlock);

        BlockrGame blockrGame = sharedData.getBlockrGame();
        blockrGame.dropBlockInPA(backendBlock);
        draggedBlock.setPosition(blockrGame.getBlockPosition(backendBlock));
        try {
            sharedData.getCorrespondingUiBlockFor(blockrGame.getCurrentActiveBlock()).setHighlightStateOn(false);
        } catch (Exception ignore) {  // TODO: fix this exception issue due to getCurrentActiveBlock()
        }
    }

    public void adjustAllStatementBlockGaps() {
        for (Block block : sharedData.getBlockrGame().getAllBlocksInPA()) {
            if (block instanceof StatementBlock) {
                UIStatementBlock uiStatement = (UIStatementBlock) sharedData.getCorrespondingUiBlockFor(block);
                uiStatement.setGapSize(((StatementBlock) block).getGapSize());
            }
        }
    }

    public void adjustAllBlockPositions() {
        for (Block block : sharedData.getBlockrGame().getAllBlocksInPA()) {
            UIBlock uiBlock = sharedData.getCorrespondingUiBlockFor(block);
            uiBlock.setPosition(block.getPosition());
        }
    }

    public void handleReleaseOutsidePA(UIBlock draggedBlock) {
        BlockrGame blockrGame = sharedData.getBlockrGame();
        Block backendBlock = draggedBlock.getCorrespondingBlock();
        if (backendBlock != null) {

            //blockUIBlockMap.remove(backendBlock);
            // Remove all bodies and conditions as well from program area
            if (backendBlock instanceof StatementBlock) {
                List<Block> newBodyBlocks = new ArrayList<>(((StatementBlock) backendBlock).getBodyBlocks());
                Collections.reverse(newBodyBlocks);
                for (Block bodyBlock : newBodyBlocks) {
                    blockrGame.removeBlockFromPA(bodyBlock, true);
                }
                List<ConditionBlock> newConditions = new ArrayList<>(((StatementBlock) backendBlock).getConditions());
                Collections.reverse(newConditions);
                for (Block condition : newConditions) {
                    blockrGame.removeBlockFromPA(condition, true);
                }
            }
            //remove the block from program area
            blockrGame.removeBlockFromPA(backendBlock, true);
        }
        try {
            sharedData.getCorrespondingUiBlockFor(blockrGame.getCurrentActiveBlock()).setHighlightStateOn(false);
        } catch (Exception ignore) {  // TODO: fix this exception issue due to getCurrentActiveBlock()
        }
    }

    public List<UIBlock> getAllUIBlocksInPA() {
        List<Block> backBlocks = sharedData.getBlockrGame().getAllBlocksInPA();
        List<UIBlock> returnUIBlocks = new ArrayList<>();
        for (Block block : backBlocks) {
            returnUIBlocks.add(sharedData.getCorrespondingUiBlockFor(block));
        }
        return returnUIBlocks;
    }

    public void handleProgramAreaForClickOn(UIBlock clickedBlock) {
        if (clickedBlock == null) throw new IllegalArgumentException();
        Block backendBlock = clickedBlock.getCorrespondingBlock();
        backendBlock.setPreviousDropPosition(backendBlock.getPosition());
        if (backendBlock instanceof StatementBlock) {
            for (Block bodyBlock : ((StatementBlock) backendBlock).getBodyBlocks()) {
                bodyBlock.setPreviousDropPosition(bodyBlock.getPosition());
            }
            for (Block condition : ((StatementBlock) backendBlock).getConditions()) {
                condition.setPreviousDropPosition(condition.getPosition());
            }
        }
        sharedData.getBlockrGame().removeBlockFromPA(backendBlock, false);
        adjustAllBlockPositions();
        adjustAllStatementBlockGaps();
    }
}
