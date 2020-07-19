package com.swop;

import com.swop.blocks.Block;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MainViewModel extends ViewModel{
    GameController gameController;
    private boolean bRepaint = false;

    public MainViewModel(Point pos, int width, int height, GameController gameController) {
        super(pos, width, height);
        this.gameController = gameController;
    }

    public String getFeedback(){
        return "# blocks available: " + gameController.getNrBlocksAvailable() + "\n" + gameController.getFeedback();
    }

    public Block getDraggedBlock(){
        return gameController.getDraggedBlock();
    }

    public void handleKeyInput(int id, int keyCode, boolean isHoldingCtrl, boolean isHoldingShift){
        bRepaint = false;
        if (id == KeyEvent.KEY_PRESSED) {
            switch (keyCode) {
                case 116: //F5
                    executeNext();
                    bRepaint = true;
                    break;
                case 27: //Escape
                    resetProgramExecution();
                    bRepaint = true;
                    break;
                case 90: //Z
                    if(isHoldingCtrl){
                        if(isHoldingShift) redo();
                        else undo();
                        bRepaint = true;
                    }
            }
        }
    }

    public void handleMouseInput(int id, int x,int y){
        switch (id) {
            case MouseEvent.MOUSE_PRESSED:
                pressMouse(x, y);
                break;
            case MouseEvent.MOUSE_CLICKED:
                break;
            case MouseEvent.MOUSE_DRAGGED:
                dragMouse(x, y);
                break;
            case MouseEvent.MOUSE_RELEASED:
                releaseMouse(x, y);
                break;
            default:
                throw new IllegalStateException("Unexpected mouse event: " + id);
        }
    }

    /**
     * Calls the respective handlers for when the mouse is pressed.
     *
     * @param x The x position of the mouse.
     * @param y The y position of the mouse.
     */
    private void pressMouse(int x, int y) {
        HandleMousePress(x,y);
    }

    /**
     * Calls the respective handlers for when the mouse is dragged.
     *
     * @param x The x position of the mouse.
     * @param y The y position of the mouse.
     */
    private void dragMouse(int x, int y) {
        if (getDraggedBlock() != null) {
            getDraggedBlock().setPosition(new Point(x,y));
            bRepaint = true;
        }
        HandleMouseDrag(x,y);
    }

    /**
     * Calls the respective handlers for when the mouse is released.
     *
     * @param x The x position of the mouse.
     * @param y The y position of the mouse.
     */
    private void releaseMouse(int x, int y) {
        if (getDraggedBlock() != null) {
            HandleMouseRelease(x,y);
            bRepaint = true;
        }
    }

    public boolean shouldRepaint(){return bRepaint;}

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
        gameController.executeNext();
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
    }

    @Override
    public void HandleMouseRelease(int x, int y) {
        gameController.HandleMouseRelease(x, y);
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
