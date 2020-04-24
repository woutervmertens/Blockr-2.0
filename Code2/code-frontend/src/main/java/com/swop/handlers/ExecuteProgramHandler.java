package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.blocks.Block;
import com.swop.uiElements.UIBlock;

import java.util.Map;

public class ExecuteProgramHandler {
    private final BlockrGame blockrGame;
    /**
     * Map with as keys all the backend blocks present in the PA and their corresponding ui block as value.
     */
    private Map<Block, UIBlock> blockUIBlockMap;

    public ExecuteProgramHandler(BlockrGame blockrGame, Map<Block, UIBlock> blockUIBlockMap) {
        this.blockrGame = blockrGame;
        this.blockUIBlockMap = blockUIBlockMap;
    }

    public UIBlock getCorrespondingUiBlockFor(Block block) {
        try {
            return blockUIBlockMap.get(block);
        } catch (NullPointerException e) {
            return null;
        }
    }

    public int getNumBlocksInPA() {
        return blockrGame.getNumBlocksInPA();
    }

    public void executeNext() {
        if (blockrGame.getNumBlocksInPA() > blockrGame.getNumBlocksInProgram()) return;
        try {
            getCorrespondingUiBlockFor(blockrGame.getCurrentActiveBlock()).setHighlightStateOn(false);
        } catch (NullPointerException ignored) {
        }
        blockrGame.executeNext();
        try {
            getCorrespondingUiBlockFor(blockrGame.getCurrentActiveBlock()).setHighlightStateOn(true);
        } catch (NullPointerException e) {
            getCorrespondingUiBlockFor(blockrGame.getAllBlocksInPA().get(0)).setHighlightStateOn(true);
        }

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
