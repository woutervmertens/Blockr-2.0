package com.swop.GameStates;

import com.swop.GameController;

public interface GameState {
    public GameState execute(GameController gameController);
    public GameState release(GameController gameController, int x, int y);
    public String getFeedback(int nrBlocksAvailable);
}
