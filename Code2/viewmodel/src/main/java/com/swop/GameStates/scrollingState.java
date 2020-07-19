package com.swop.GameStates;

import com.swop.GameController;

public class scrollingState implements GameState{
    @Override
    public GameState execute(GameController gameController) {
        //set y pos
        gameController.executeNext();
        return new defaultState();
    }

    @Override
    public GameState release(GameController gameController) {
        //set y pos
        return new defaultState();
    }

    @Override
    public String getFeedback() {
        return "";
    }
}
