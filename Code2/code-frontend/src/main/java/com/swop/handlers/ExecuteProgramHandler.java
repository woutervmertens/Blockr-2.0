package com.swop.handlers;

import com.swop.BlockrGame;

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
    }

    public void reset() {
        blockrGame.resetProgramExecution();
    }
}
