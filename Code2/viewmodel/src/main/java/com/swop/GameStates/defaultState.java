package com.swop.GameStates;

import com.swop.InputGameControllerFacade;

/**
 * The default state logic.
 */
public class defaultState implements GameState{
    /**
     * Calls the GameController to execute the next block
     * @param gameController a facade to talk to the GameController
     * @return the default state
     */
    @Override
    public GameState execute(InputGameControllerFacade gameController) {
        //Execute
        gameController.executeNext();
        return this;
    }

    /**
     * Calls the GameController to handle the release
     * @param gameController a facade to talk to the GameController
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     * @return the default state
     */
    @Override
    public GameState release(InputGameControllerFacade gameController, int x, int y) {
        gameController.CallReleaseInVms(x,y);
        return this;
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
