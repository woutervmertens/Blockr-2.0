package com.swop.GameStates;

import com.swop.InputGameControllerFacade;

public class draggingBlockState implements GameState{

    /**
     * Calls the GameController to reset the execution and execute the next block
     * @param gameController a facade to talk to the GameController
     * @return the default state
     */
    @Override
    public GameState execute(InputGameControllerFacade gameController) {
        //Remove draggingBlock
        gameController.resetExecution();
        gameController.executeNext();
        return new defaultState();
    }

    /**
     * Calls the GameController to drop the dragged block
     * @param gameController a facade to talk to the GameController
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     * @return the default state
     */
    @Override
    public GameState release(InputGameControllerFacade gameController, int x, int y) {
        gameController.dropDraggedBlock();
        return new defaultState();
    }

    /**
     * Gives feedback to the user.
     * @param nrBlocksAvailable the amount of blocks still available to use.
     * @return a feedback string
     */
    @Override
    public String getFeedback(int nrBlocksAvailable) {
        return "# blocks available: " + nrBlocksAvailable;
    }
}
