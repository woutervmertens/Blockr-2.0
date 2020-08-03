package com.swop.GameStates;

import com.swop.InputGameControllerFacade;
import com.swop.RepaintEventController;

public class StateHandler {
    private GameState gameState = new defaultState();
    private InputGameControllerFacade gameController;
    public StateHandler(InputGameControllerFacade gameController){
        this.gameController = gameController;
    }

    public void HandleMouseRelease(int x, int y){
        gameState = gameState.release(gameController, x, y);
        gameController.setDraggedBlockVM(null);
        RepaintEventController.getInstance().CallRepaint();
    }

    public String getFeedback() {
        return gameState.getFeedback(gameController.getNrBlocksAvailable());
    }

    public void HandleExecute() {
        gameState = gameState.execute(gameController);
        RepaintEventController.getInstance().CallRepaint();
    }

    public void setStateDrag(){
        gameState = new draggingBlockState();
    }

    public void setStateGoal() {
        gameState = new goalReachedState();
    }

    public void setStateDefault() { gameState = new defaultState();
    }
}
