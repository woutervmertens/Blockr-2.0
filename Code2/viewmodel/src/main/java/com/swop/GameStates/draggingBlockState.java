package com.swop.GameStates;

import com.swop.InputGameControllerFacade;

public class draggingBlockState implements GameState{

    @Override
    public GameState execute(InputGameControllerFacade gameController) {
        //Remove draggingBlock
        gameController.resetExecution();
        gameController.executeNext();
        return new defaultState();
    }

    @Override
    public GameState release(InputGameControllerFacade gameController, int x, int y) {
        gameController.dropDraggedBlock();
        return new defaultState();
    }

    @Override
    public String getFeedback(int nrBlocksAvailable) {
        return "# blocks available: " + nrBlocksAvailable;
    }
}
