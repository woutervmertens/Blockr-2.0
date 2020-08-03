package com.swop.GameStates;

import com.swop.InputGameControllerFacade;

public class goalReachedState implements GameState{
    @Override
    public GameState execute(InputGameControllerFacade gameController) {
        //gameController.resetExecution();
        return new defaultState();
    }

    @Override
    public GameState release(InputGameControllerFacade gameController, int x, int y) {
        return new defaultState();
    }

    @Override
    public String getFeedback(int nrBlocksAvailable) {
        return "GOAL REACHED!";
    }
}
