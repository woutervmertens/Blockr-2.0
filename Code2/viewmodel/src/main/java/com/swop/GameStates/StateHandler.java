package com.swop.GameStates;

import com.swop.InputGameControllerFacade;
import com.swop.RepaintEventController;

public class StateHandler {
    private GameState gameState = new defaultState();
    private InputGameControllerFacade gameController;
    public StateHandler(InputGameControllerFacade gameController){
        this.gameController = gameController;
    }

    /**
     * Calls the release for the current GameState, tells the GameController to remove the dragged block and flags a repaint.
     * @param x the x position of the mouse.
     * @param y the y position of the mouse.
     */
    public void HandleMouseRelease(int x, int y){
        gameState = gameState.release(gameController, x, y);
        gameController.setDraggedBlockVM(null);
        RepaintEventController.getInstance().CallRepaint();
    }

    /**
     * Calls the feedback for the current GameState.
     * @return feedback for the user.
     */
    public String getFeedback() {
        return gameState.getFeedback(gameController.getNrBlocksAvailable());
    }

    /**
     * Calls the execute for the current GameState, flags a repaint.
     * @return feedback for the user.
     */
    public void HandleExecute() {
        gameState = gameState.execute(gameController);
        RepaintEventController.getInstance().CallRepaint();
    }

    /**
     * Change to GameState to the drag state
     */
    public void setStateDrag(){
        gameState = new draggingBlockState();
    }

    /**
     * Change to GameState to the goal reached state
     */
    public void setStateGoal() {
        gameState = new goalReachedState();
    }

    /**
     * Change to GameState to the default state
     */
    public void setStateDefault() {
        gameState = new defaultState();
    }
}
