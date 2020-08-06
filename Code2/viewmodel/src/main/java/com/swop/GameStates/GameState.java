package com.swop.GameStates;

import com.swop.InputGameControllerFacade;

/**
 * The state interface.
 */
public interface GameState {
    public GameState execute(InputGameControllerFacade gameController);
    public GameState release(InputGameControllerFacade gameController, int x, int y);
    public String getFeedback(int nrBlocksAvailable);
}
