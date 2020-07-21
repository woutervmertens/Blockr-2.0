package com.swop.GameStates;

import com.swop.GameController;

public class defaultState implements GameState{
    @Override
    public GameState execute(GameController gameController) {
        //Execute
        gameController.executeNext();
        return this;
    }

    @Override
    public GameState release(GameController gameController, int x, int y) {
        gameController.CallReleaseInVms(x,y);
        return this;
    }

    @Override
    public String getFeedback() {
        return "";
    }
}
