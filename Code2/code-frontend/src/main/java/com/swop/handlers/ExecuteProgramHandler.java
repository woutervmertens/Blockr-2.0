package com.swop.handlers;

import com.swop.BlockrGame;

public class ExecuteProgramHandler {
    private final SharedData sharedData;

    public ExecuteProgramHandler(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    /**
     * Executes the next block in the program.
     */
    public void executeNext() {
        BlockrGame blockrGame = sharedData.getBlockrGame();
        // TODO: subtract from getNumBlocks the blocks in a functiondef
        // TODO: if (blockrGame.getNumBlocksInPA() > blockrGame.getNumBlocksInProgram()) return;
        blockrGame.executeNext();
        updateHighlight();
    }

    /**
     * Updates the blocks that need to be highlighted.
     */
    public void updateHighlight() {
        BlockrGame blockrGame = sharedData.getBlockrGame();
        sharedData.setHighlightedBlock(sharedData.getCorrespondingUiBlockFor(blockrGame.getNextToBeExecutedBlock()));
    }

}
