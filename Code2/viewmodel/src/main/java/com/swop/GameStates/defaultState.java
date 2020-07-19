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
    public GameState release(GameController gameController) {
        //Do nothing
        return this;
    }

    @Override
    public String getFeedback() {
        return "";
    }
}
