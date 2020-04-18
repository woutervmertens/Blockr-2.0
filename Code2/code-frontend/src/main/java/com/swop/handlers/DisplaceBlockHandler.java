package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.PushBlocks;
import com.swop.blocks.Block;
import com.swop.blocks.ConditionBlock;
import com.swop.blocks.StatementBlock;
import com.swop.uiElements.UIBlock;
import com.swop.uiElements.UIConditionBlock;
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
            draggedBlock.makeNewCorrespondingBlockIn();

        Block backendBlock = draggedBlock.getCorrespondingBlock();
        if (!getBlockUIBlockMap().containsKey(backendBlock)) {
            putInBlockUIBlockMap(backendBlock, draggedBlock);
        }
        blockrGame.dropBlockInPA(backendBlock);
        draggedBlock.setPosition(blockrGame.getBlockPosition(backendBlock));

        adjustAllStatementBlockGaps();
        adjustAllBlockPositions();
    }

    private void adjustAllStatementBlockGaps() {
        for (Block block : blockrGame.getAllBlocksInPA()) {
            if (block instanceof StatementBlock) {
                UIStatementBlock uiStatement = (UIStatementBlock) getCorrespondingUiBlockFor(block);
                uiStatement.setGapSize(((StatementBlock) block).getGapSize());
            }
        }
    }

    private void adjustAllBlockPositions() {
        for (Block block : blockrGame.getAllBlocksInPA()) {
            UIBlock uiBlock = getCorrespondingUiBlockFor(block);
            uiBlock.setPosition(block.getPosition());
        }
    }

    public void handleReleaseOutsidePA(UIBlock draggedBlock) {
        if (draggedBlock.getCorrespondingBlock() != null) {
            if (draggedBlock.getCorrespondingBlock() instanceof StatementBlock) {
                for (Block bodyBlock : ((StatementBlock) draggedBlock.getCorrespondingBlock()).getBodyBlocks()) {
                    blockUIBlockMap.remove(bodyBlock);
                    blockrGame.removeBlockFromPA(bodyBlock);
                }
            }
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

    public void handleProgramAreaForClickOn(UIBlock clickedBlock) {
        if (clickedBlock == null) throw new IllegalArgumentException();

        if (!(clickedBlock instanceof UIConditionBlock)) {
            StatementBlock parentStatement = clickedBlock.getCorrespondingBlock().getParentStatement();
            if (parentStatement != null) {
                parentStatement.removeBodyBlock(clickedBlock.getCorrespondingBlock());
                PushBlocks.pushBodyBlocksOfSuperiorParents(clickedBlock.getCorrespondingBlock().getParentStatement().getBodyBlocks(),
                        -clickedBlock.getHeight() - clickedBlock.getStep() - ((StatementBlock)clickedBlock.getCorrespondingBlock()).getGapSize());
            }
            // TODO: still needed ?
//            if (clickedBlock instanceof UIStatementBlock && !((StatementBlock) (clickedBlock.getCorrespondingBlock())).getBodyBlocks().isEmpty()) {
//
//            }
        } else {
            // TODO:
        }
        // TODO: check if the program contains that block ? Is it needed ?
        blockrGame.removeProgramBlock(clickedBlock.getCorrespondingBlock());

        adjustAllBlockPositions();
        adjustAllStatementBlockGaps();
    }

    public void displaceAllBodyBlocksAndConditionsOfBlockWithDistance(UIStatementBlock draggedBlock, int dx, int dy) {
        // Body blocks
        List<Block> bodyAndConditionBlocks = ((StatementBlock) draggedBlock.getCorrespondingBlock()).getBodyBlocks();
        // Adding conditions
        bodyAndConditionBlocks.addAll(((StatementBlock) draggedBlock.getCorrespondingBlock()).getConditions());

        for (Block block :bodyAndConditionBlocks) {
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
