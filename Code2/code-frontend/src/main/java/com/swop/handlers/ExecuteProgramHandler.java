package com.swop.handlers;

import com.swop.BlockrGame;
import com.swop.uiElements.UIBlock;

public class ExecuteProgramHandler {
    private final SharedData sharedData;

    public ExecuteProgramHandler(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void executeNext() {
        BlockrGame blockrGame = sharedData.getBlockrGame();
        if (blockrGame.getNumBlocksInPA() > blockrGame.getNumBlocksInProgram()) return;
        blockrGame.executeNext();
        sharedData.setHighlightedBlock(sharedData.getCorrespondingUiBlockFor(blockrGame.getNextToBeExecutedBlock()));
    }

}
