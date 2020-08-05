package com.swop.GameStates;

import com.swop.InputGameControllerFacade;

public class goalReachedState implements GameState{
    /**
     * Does nothing.
     * @param gameController a facade to talk to the GameController
     * @return the default state
     */
    @Override
    public GameState execute(InputGameControllerFacade gameController) {
        return new defaultState();
    }

    /**
     * Does nothing.
     * @param gameController a facade to talk to the GameController
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     * @return the default state
     */
    @Override
    public GameState release(InputGameControllerFacade gameController, int x, int y) {
        return new defaultState();
    }

    /**
     * Gives feedback to the user.
     * @param nrBlocksAvailable the amount of blocks still available to use.
     * @return the string "GOAL REACHED!"
     */
    @Override
    public String getFeedback(int nrBlocksAvailable) {
        return "GOAL REACHED!";
    }
}
