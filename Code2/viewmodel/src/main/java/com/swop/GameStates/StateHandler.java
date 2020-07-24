package com.swop.GameStates;

import com.swop.GameController;
import com.swop.RepaintEventController;

public class StateHandler {
    private GameState gameState = new defaultState();
    private GameController gameController;
    public StateHandler(GameController gameController){
        this.gameController = gameController;
    }

    public void HandleMouseRelease(int x, int y){
        gameState = gameState.release(gameController, x, y);
        gameController.setDraggedBlock(null);
        RepaintEventController.getInstance().CallRepaint();
    }

    public String getFeedback() {
        return gameState.getFeedback();
    }

    public void HandleExecute() {
        gameState = gameState.execute(gameController);
        RepaintEventController.getInstance().CallRepaint();
    }
}
