package com.swop;

import com.swop.GameStates.StateHandler;
import com.swop.blocks.BlockVM;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MainViewModel extends ViewModel{
    GameController gameController;
    StateHandler stateHandler;

    public MainViewModel(Point pos, int width, int height, GameController gameController) {
        super(pos, width, height);
        this.gameController = gameController;
        stateHandler = new StateHandler(gameController);
    }

    public String getFeedback(){
        return "# blocks available: " + gameController.getNrBlocksAvailable() + "\n" + stateHandler.getFeedback();
    }

    public BlockVM getDraggedBlock(){
        return gameController.getDraggedBlockVM();
    }

    public void handleKeyInput(int id, int keyCode, boolean isHoldingCtrl, boolean isHoldingShift){
        if (id == KeyEvent.KEY_PRESSED) {
            switch (keyCode) {
                case 116: //F5
                    executeNext();
                    break;
                case 27: //Escape
                    resetProgramExecution();
                    break;
                case 90: //Z
                    if(isHoldingCtrl){
                        if(isHoldingShift) redo();
                        else undo();
                    }
            }
        }
    }

    public void handleMouseInput(int id, int x,int y){
        switch (id) {
            case MouseEvent.MOUSE_PRESSED:
                HandleMousePress(x, y);
                break;
            case MouseEvent.MOUSE_CLICKED:
                break;
            case MouseEvent.MOUSE_DRAGGED:
                HandleMouseDrag(x, y);
                break;
            case MouseEvent.MOUSE_RELEASED:
                HandleMouseRelease(x, y);
                break;
            default:
                throw new IllegalStateException("Unexpected mouse event: " + id);
        }
    }

    public boolean shouldRepaint(){return RepaintEventController.getInstance().ShouldRepaint();}

    /**
     * Calls the respective handler to undo the last change.
     */
    private void undo() {
        gameController.undoCommand();
    }

    /**
     * Calls the respective handler to redo the last undo.
     */
    private void redo() {
        gameController.redoCommand();
    }

    /**
     * Calls the respective handler to execute the next block.
     */
    private void executeNext() {
        stateHandler.HandleExecute();
        if(gameController.getLastSuccessState() == SuccessState.GOAL_REACHED) stateHandler.setStateGoal();
    }

    /**
     * Calls the respective handler to reset the program.
     */
    private void resetProgramExecution() {
        gameController.resetExecution();
    }

    @Override
    public void HandleMousePress(int x, int y) {
        gameController.HandleMousePress(x,y);
        if(gameController.getDraggedBlockVM() != null)stateHandler.setStateDrag();
    }

    @Override
    public void HandleMouseRelease(int x, int y) {
        stateHandler.HandleMouseRelease(x, y);
    }

    @Override
    public void HandleMouseDrag(int x, int y) {
        gameController.HandleMouseDrag(x,y);
    }

    @Override
    public void HandleReset() {
        return;
    }

    @Override
    public Object getModel() {
        return null;
    }
}
