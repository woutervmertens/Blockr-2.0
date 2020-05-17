package com.swop.handlers;

import com.swop.BlockrGame;

public class ExecuteProgramHandler {
    private final SharedData sharedData;

    public ExecuteProgramHandler(SharedData sharedData) {
        this.sharedData = sharedData;
    }

    public void executeNext() {
        // TODO: make a separate method for highlights and fix update bug !

        BlockrGame blockrGame = sharedData.getBlockrGame();
        if (blockrGame.getNumBlocksInPA() > blockrGame.getNumBlocksInProgram()) return;
        try {
            sharedData.getCorrespondingUiBlockFor(blockrGame.getCurrentActiveBlock()).setHighlightStateOn(false);
        } catch (NullPointerException ignored) {
        }
        blockrGame.executeNext();
        try {
            sharedData.getCorrespondingUiBlockFor(blockrGame.getCurrentActiveBlock()).setHighlightStateOn(true);
        } catch (NullPointerException e) {
            sharedData.getCorrespondingUiBlockFor(blockrGame.getAllBlocksInPA().get(0)).setHighlightStateOn(true);
        }

    }

}
