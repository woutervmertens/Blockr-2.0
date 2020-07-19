package com.swop.GameStates;

import com.swop.GameController;

public class goalReachedState implements GameState{
    @Override
    public GameState execute(GameController gameController) {
        gameController.resetExecution();
        return new defaultState();
    }

    @Override
    public GameState release(GameController gameController) {
        return new defaultState();
    }

    @Override
    public String getFeedback() {
        return "GOAL REACHED!";
    }
}
