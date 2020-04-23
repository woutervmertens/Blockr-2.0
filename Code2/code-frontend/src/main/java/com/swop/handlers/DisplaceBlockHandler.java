package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.blocks.Block;
import com.swop.blocks.StatementBlock;
import com.swop.uiElements.UIBlock;
import com.swop.uiElements.UIStatementBlock;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (draggedBlock.getCorrespondingBlock() == null)
            draggedBlock.makeNewCorrespondingBlock();

        Block backendBlock = draggedBlock.getCorrespondingBlock();
        if (!getBlockUIBlockMap().containsKey(backendBlock)) {
            putInBlockUIBlockMap(backendBlock, draggedBlock);
        }
        blockrGame.dropBlockInPA(backendBlock);
        draggedBlock.setPosition(blockrGame.getBlockPosition(backendBlock));
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
            if (backendBlock instanceof StatementBlock) {

                for (Block bodyBlock : new ArrayList<>(((StatementBlock) backendBlock).getBodyBlocks())) {
                    blockUIBlockMap.remove(bodyBlock);
                    blockrGame.removeBlockFromPA(bodyBlock);
                }
            }
            //remove from the map in DisplaceBlockHandler
            blockUIBlockMap.remove(backendBlock);
            //remove the block from program area
            blockrGame.removeBlockFromPA(backendBlock);
            // Remove all bodies and conditions as well from program area
            if (backendBlock instanceof StatementBlock) {
                for (Block bodyBlock : ((StatementBlock) backendBlock).getBodyBlocks()) {
                    blockrGame.removeBlockFromPA(bodyBlock);
                }
                for (Block bodyBlock : ((StatementBlock) backendBlock).getConditions()) {
                    blockrGame.removeBlockFromPA(bodyBlock);
                }
            }
        }
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

        blockrGame.removeBlockFromPA(clickedBlock.getCorrespondingBlock());

        // TODO: remove
        //adjustAllBlockPositions();
        //adjustAllStatementBlockGaps();
    }

    public void displaceAllBodyBlocksAndConditionsOfBlockWithDistance(UIStatementBlock draggedBlock, int dx, int dy) {
        // Body blocks
        List<Block> bodyAndConditionBlocks = ((StatementBlock) draggedBlock.getCorrespondingBlock()).getBodyBlocks();
        // Adding conditions
        bodyAndConditionBlocks.addAll(((StatementBlock) draggedBlock.getCorrespondingBlock()).getConditions());

        for (Block block : bodyAndConditionBlocks) {
            block.setPosition(new Point(block.getPosition().x + dx, block.getPosition().y + dy));
            if (block instanceof StatementBlock) {
                for (Block bodyBlock2 : ((StatementBlock) block).getBodyBlocks()) {
                    bodyBlock2.setPosition(new Point(bodyBlock2.getPosition().x + dx, bodyBlock2.getPosition().y + dy));
                }
            }
        }
        adjustAllBlockPositions();
    }
}
