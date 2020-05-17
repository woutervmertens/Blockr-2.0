package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.GameWorldType;
import com.swop.blocks.Block;
import com.swop.blocks.StatementBlock;
import com.swop.uiElements.UIBlock;
import com.swop.uiElements.UIStatementBlock;

import java.util.HashMap;
import java.util.Map;

/**
 * Class holding information about all the shared objects between the existing handlers.
 * This class represents the "database" between frontend and backend.
 */
public class SharedData {
    private static SharedData firstInstance = null;
    private final BlockrGame blockrGame;
    private final Map<Block, UIBlock> blockUIBlockMap = new HashMap<>();
    private UIBlock highlightedBlock;

    private SharedData(int maxBlocks, GameWorldType type) {
        blockrGame = new BlockrGame(maxBlocks, type);
    }

    public static SharedData getInstance(int maxBlocks, GameWorldType type) {
        if (firstInstance == null) {
            firstInstance = new SharedData(maxBlocks, type);
        }
        return firstInstance;
    }

    public BlockrGame getBlockrGame() {
        return blockrGame;
    }

    public void putInBlockUIBlockMap(Block key, UIBlock value) {
        if (!blockUIBlockMap.containsKey(key)) {
            blockUIBlockMap.put(key, value);
        }
    }

    public UIBlock getCorrespondingUiBlockFor(Block block) {
        try {
            return blockUIBlockMap.get(block);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public Block getCorrespondingBlockFor(UIBlock uiBlock) {
        // TODO: decide whether another map is needed for the opposite direction or not (for efficiency)
        // TODO: remove the block attribute from uiblocks and fill this method
        return null;
    }

    public void adjustAllStatementBlockGaps() {
        for (Block block : getBlockrGame().getAllBlocksInPA()) {
            if (block instanceof StatementBlock) {
                UIStatementBlock uiStatement = (UIStatementBlock) getCorrespondingUiBlockFor(block);
                uiStatement.setGapSize(((StatementBlock) block).getGapSize());
            }
        }
    }

    public void adjustAllBlockPositions() {
        for (Block block : getBlockrGame().getAllBlocksInPA()) {
            UIBlock uiBlock = getCorrespondingUiBlockFor(block);
            uiBlock.setPosition(block.getPosition());
        }
    }

    public void setHighlightedBlock(UIBlock block) {
        if (block == null) throw new IllegalArgumentException();

        if (highlightedBlock != null) highlightedBlock.setHighlightStateOn(false);
        highlightedBlock = block;
        highlightedBlock.setHighlightStateOn(true);
    }

    // TODO: find a way to "adjust" highlighted block
}
