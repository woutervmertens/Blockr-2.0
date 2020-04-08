package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.blocks.Block;
import com.swop.blocks.StatementBlock;
import com.swop.uiElements.UIBlock;
import com.swop.uiElements.UIStatementBlock;

import java.util.*;

public class DisplaceBlockHandler {
    /**
     * Map with as keys all the backend blocks present in the PA and their corresponding ui block as value.
     */
    private Map<Block, UIBlock> blockUIBlockMap = new HashMap<>();

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

    public DisplaceBlockHandler(BlockrGame blockrGame) {
        this.blockrGame = blockrGame;
    }

    /**
     * Connection with backend
     */
    private BlockrGame blockrGame;

    /**
     * @pre draggedBlock.getPosition() is inside the PA
     */
    public void handleReleaseInPA(UIBlock draggedBlock) {
        if (draggedBlock.getCorrespondingBlock() == null) draggedBlock.makeNewCorrespondingBlockIn(blockrGame.getGameWorld());

        Block backendBlock = draggedBlock.getCorrespondingBlock();
        if (!getBlockUIBlockMap().containsKey(backendBlock)) {
            putInBlockUIBlockMap(backendBlock, draggedBlock);
        }
        blockrGame.dropBlockInPA(backendBlock);
        draggedBlock.setPosition(blockrGame.getBlockPosition(backendBlock));

        adjustAllStatementBlockGaps();
    }

    private void adjustAllStatementBlockGaps() {
        for (Block block: blockrGame.getAllBlocksInPA()) {
            if (block instanceof StatementBlock) {
                UIStatementBlock uiStatement = (UIStatementBlock)getCorrespondingUiBlockFor(block);
                uiStatement.setGapSize(((StatementBlock) block).getGapSize());
            }
        }
    }

    public void handleReleaseOutsidePA(UIBlock draggedBlock) {
        if (draggedBlock.getCorrespondingBlock() != null) {
            //remove from the map in DisplaceBlockHandler
            blockUIBlockMap.remove(draggedBlock.getCorrespondingBlock());
            //remove the block from program area
            blockrGame.removeBlockFromPA(draggedBlock.getCorrespondingBlock());
        }
        draggedBlock.terminate();
    }

    public List<UIBlock> getAllUIBlocksInPA() {
        List<Block> backBlocks = blockrGame.getAllBlocksInPA();
        List<UIBlock> returnUIBlocks = new ArrayList<>();
        for (Block block : backBlocks) {
            returnUIBlocks.add(getCorrespondingUiBlockFor(block));
        }
        return returnUIBlocks;
    }
}
