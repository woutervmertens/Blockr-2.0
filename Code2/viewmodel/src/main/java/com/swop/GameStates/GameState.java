package com.swop.GameStates;

import com.swop.InputGameControllerFacade;

public interface GameState {
    public GameState execute(InputGameControllerFacade gameController);
    public GameState release(InputGameControllerFacade gameController, int x, int y);
    public String getFeedback(int nrBlocksAvailable);
}
