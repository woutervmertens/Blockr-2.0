package com.swop.GameStates;

import com.swop.InputGameControllerFacade;

public class defaultState implements GameState{
    @Override
    public GameState execute(InputGameControllerFacade gameController) {
        //Execute
        gameController.executeNext();
        return this;
    }

    @Override
    public GameState release(InputGameControllerFacade gameController, int x, int y) {
        gameController.CallReleaseInVms(x,y);
        return this;
    }

    @Override
    public String getFeedback(int nrBlocksAvailable) {
        return "# blocks available: " + nrBlocksAvailable;
    }
}
