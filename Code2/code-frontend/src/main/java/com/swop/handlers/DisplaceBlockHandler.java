package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.blocks.Block;
import com.swop.blocks.ConditionBlock;
import com.swop.blocks.StatementBlock;
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
     * @pre draggedBlock.getPosition() is inside the PA
     */
    public void handleReleaseInPA(UIBlock draggedBlock) {
        // 1) Handle map
        if (draggedBlock.getCorrespondingBlock() == null) {
            draggedBlock.makeNewCorrespondingBlock();
            draggedBlock.getCorrespondingBlock().setBlockrGame(sharedData.getBlockrGame());
            sharedData.putInBlockUIBlockMap(draggedBlock.getCorrespondingBlock(), draggedBlock);
        }

        // 2) Handle drop position
        Block backendBlock = draggedBlock.getCorrespondingBlock();
        BlockrGame blockrGame = sharedData.getBlockrGame();
        blockrGame.dropBlockInPA(backendBlock);
        draggedBlock.setPosition(blockrGame.getBlockPosition(backendBlock));

        // 3) Handle highlight
        sharedData.setHighlightedBlock(sharedData.getCorrespondingUiBlockFor(blockrGame.getNextToBeExecutedBlock()));
    }

    public void handleReleaseOutsidePA(UIBlock draggedBlock) {
        BlockrGame blockrGame = sharedData.getBlockrGame();
        Block backendBlock = draggedBlock.getCorrespondingBlock();
        if (backendBlock != null) {
            // TODO: don't remove all bodies and conditions here, let the statementblock do it himself
            // Remove all bodies and conditions as well from program area
            if (backendBlock instanceof StatementBlock) {
                List<Block> newBodyBlocks = new ArrayList<>(((StatementBlock) backendBlock).getBodyBlocks());
                Collections.reverse(newBodyBlocks);  // Reversing is needed for correct undo
                for (Block bodyBlock : newBodyBlocks) {
                    blockrGame.removeBlockFromPA(bodyBlock, true);
                }
                List<ConditionBlock> newConditions = new ArrayList<>(((StatementBlock) backendBlock).getConditions());
                Collections.reverse(newConditions);  // Reversing is needed for correct undo
                for (Block condition : newConditions) {
                    blockrGame.removeBlockFromPA(condition, true);
                }
            }
            //remove the block from program area
            blockrGame.removeBlockFromPA(backendBlock, true);
        }

        sharedData.setHighlightedBlock(sharedData.getCorrespondingUiBlockFor(blockrGame.getNextToBeExecutedBlock()));
    }

    public List<UIBlock> getAllUIBlocksInPA() {
        List<Block> backBlocks = sharedData.getBlockrGame().getAllBlocksInPA();
        List<UIBlock> returnUIBlocks = new ArrayList<>();
        for (Block block : backBlocks) {
            returnUIBlocks.add(sharedData.getCorrespondingUiBlockFor(block));
        }
        return returnUIBlocks;
    }

    public void handleClickOn(UIBlock clickedBlock) {
        if (clickedBlock == null) throw new IllegalArgumentException();
        Block backendBlock = clickedBlock.getCorrespondingBlock();
        backendBlock.setPreviousDropPosition(backendBlock.getPosition());
        sharedData.getBlockrGame().removeBlockFromPA(backendBlock, false);
        sharedData.adjustAllBlockPositions();
        sharedData.adjustAllStatementBlockGaps();

        // TODO: restore highlighted block
    }
}
