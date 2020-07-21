package com.swop.GameStates;

import com.swop.GameController;

public class draggingBlockState implements GameState{

    @Override
    public GameState execute(GameController gameController) {
        //Remove draggingBlock
        gameController.resetExecution();
        gameController.executeNext();
        return new defaultState();
    }

    @Override
    public GameState release(GameController gameController, int x, int y) {
        gameController.dropDraggedBlock();
        return new defaultState();
    }

    @Override
    public String getFeedback() {
        return "";
    }
}
