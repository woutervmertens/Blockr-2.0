package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.blocks.Block;
import com.swop.uiElements.UIBlock;

import java.awt.*;

public class ExecuteProgramHandler {
    private BlockrGame blockrGame;

    public ExecuteProgramHandler(BlockrGame blockrGame) {
        this.blockrGame = blockrGame;
    }

    public int getNumBlocksInPA() {
        return blockrGame.getNumbBlocksInPA();
    }

    public void executeNext() {
        blockrGame.executeNext();
        // TODO: find a way to get the UIBlock given a backend block without giving backend info about frontend
        //  highlightBlock(blockrGame.getCurrentBlock());
    }

    public void reset() {
        if (currentlyHighlighted != null) currentlyHighlighted.setHighlightStateOn(false);
        currentlyHighlighted = null;
        blockrGame.resetProgramExecution();
    }

    public Point getCharacterPos() {
        return blockrGame.getCharacterPos();
    }

    /**
     * Unhighlight currently highlighted block and highlight the given one.
     */
    private void highlightBlock(UIBlock block) {
        if (block == null) throw new IllegalArgumentException();

        if (currentlyHighlighted != null) currentlyHighlighted.setHighlightStateOn(false);
        currentlyHighlighted = block;
        block.setHighlightStateOn(true);
    }

    /**
     * Index of the currently highlighted block
     */
    private UIBlock currentlyHighlighted = null;
}
