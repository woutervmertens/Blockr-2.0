package com.swop;

import com.swop.GameStates.StateHandler;
import com.swop.blocks.BlockVM;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MainViewModel extends ViewModel{
    InputGameControllerFacade gameController;
    StateHandler stateHandler;

    public MainViewModel(Point pos, int width, int height, InputGameControllerFacade gameController) {
        super(pos, width, height);
        this.gameController = gameController;
        stateHandler = new StateHandler(gameController);
    }

    public String getFeedback(){
        return stateHandler.getFeedback();
    }

    public BlockVM getDraggedBlock(){
        return gameController.getDraggedBlockVM();
    }

    /**
     * Calls the appropriate response for supported Key Inputs
     *
     * @param id the Key ID of the pressed key
     * @param keyCode the KeyCode of the pressed key
     * @param isHoldingCtrl is the user also holding down CTRL?
     * @param isHoldingShift is the user also holding down SHIFT?
     */
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

    /**
     * Calls the appropriate response for supported Mouse Inputs
     *
     * @param id the Input ID
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
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
        gameController.callResetCommand();
        HandleReset();
    }

    /**
     * Calls the appropriate handler for MousePress
     */
    @Override
    public void HandleMousePress(int x, int y) {
        gameController.HandleMousePress(x,y);
        if(gameController.getDraggedBlockVM() != null)
            stateHandler.setStateDrag();
    }

    /**
     * Calls the appropriate handler for MouseRelease
     */
    @Override
    public void HandleMouseRelease(int x, int y) {
        stateHandler.HandleMouseRelease(x, y);
    }

    /**
     * Calls the appropriate handler for MouseDrag
     */
    @Override
    public void HandleMouseDrag(int x, int y) {

        gameController.HandleMouseDrag(x,y);
    }

    /**
     * Calls the appropriate handler for Reset
     */
    @Override
    public void HandleReset() {
        stateHandler.setStateDefault();
    }

    @Override
    public Object getModel() {
        return null;
    }
}
