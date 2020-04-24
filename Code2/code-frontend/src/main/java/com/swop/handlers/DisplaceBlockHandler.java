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
import java.util.Map;

public class DisplaceBlockHandler {
    /**
     * Connection with backend
     */
    private final BlockrGame blockrGame;
    /**
     * Map with as keys all the backend blocks present in the PA and their corresponding ui block as value.
     */
    private Map<Block, UIBlock> blockUIBlockMap;

    public DisplaceBlockHandler(BlockrGame blockrGame, Map<Block, UIBlock> blockUIBlockMap) {
        this.blockrGame = blockrGame;
        this.blockUIBlockMap = blockUIBlockMap;
    }

    private Map<Block, UIBlock> getBlockUIBlockMap() {
        return blockUIBlockMap;
    }

    public void putInBlockUIBlockMap(Block key, UIBlock value) {
        blockUIBlockMap.put(key, value);
    }

    public UIBlock getCorrespondingUiBlockFor(Block block) {
        try {
            return blockUIBlockMap.get(block);
        } catch (NullPointerException e) {
            return null;
        }
    }

    /**
     * @pre draggedBlock.getPosition() is inside the PA
     */
    public void handleReleaseInPA(UIBlock draggedBlock) {
        if (draggedBlock.getCorrespondingBlock() == null)
            draggedBlock.makeNewCorrespondingBlock();

        Block backendBlock = draggedBlock.getCorrespondingBlock();
        if (!getBlockUIBlockMap().containsKey(backendBlock)) {
            putInBlockUIBlockMap(backendBlock, draggedBlock);
        }
        blockrGame.dropBlockInPA(backendBlock);
        draggedBlock.setPosition(blockrGame.getBlockPosition(backendBlock));
        getCorrespondingUiBlockFor(blockrGame.getCurrentActiveBlock()).setHighlightStateOn(false);
    }

    public void adjustAllStatementBlockGaps() {
        for (Block block : blockrGame.getAllBlocksInPA()) {
            if (block instanceof StatementBlock) {
                UIStatementBlock uiStatement = (UIStatementBlock) getCorrespondingUiBlockFor(block);
                uiStatement.setGapSize(((StatementBlock) block).getGapSize());
            }
        }
    }

    public void adjustAllBlockPositions() {
        for (Block block : blockrGame.getAllBlocksInPA()) {
            UIBlock uiBlock = getCorrespondingUiBlockFor(block);
            uiBlock.setPosition(block.getPosition());
        }
    }

    public void handleReleaseOutsidePA(UIBlock draggedBlock) {
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
        getCorrespondingUiBlockFor(blockrGame.getCurrentActiveBlock()).setHighlightStateOn(false);
    }

    public List<UIBlock> getAllUIBlocksInPA() {
        List<Block> backBlocks = blockrGame.getAllBlocksInPA();
        List<UIBlock> returnUIBlocks = new ArrayList<>();
        for (Block block : backBlocks) {
            returnUIBlocks.add(getCorrespondingUiBlockFor(block));
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
        blockrGame.removeBlockFromPA(backendBlock, false);
        adjustAllBlockPositions();
        adjustAllStatementBlockGaps();
    }
}
