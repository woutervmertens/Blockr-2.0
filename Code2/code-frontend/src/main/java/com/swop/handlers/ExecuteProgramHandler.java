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
        // TODO getCharPos()
    }

    public void undo(){
        blockrGame.undoCommand();
    }
    public void redo(){
        blockrGame.redoCommand();
    }

    public void reset() {
        blockrGame.resetProgramExecution();
    }
}
