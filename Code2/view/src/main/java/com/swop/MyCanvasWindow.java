package com.swop;

import com.swop.handlers.BlockrGameFacade;
import com.swop.handlers.SharedData;
import com.swop.uiElements.UIBlock;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;


public class MyCanvasWindow extends CanvasWindow {
    // Facade
    BlockrGameFacade blockrGameFacade;
    //KeyHolds
    boolean isHoldingCtrl = false;
    boolean isHoldingShift = false;
    private boolean isPaletteHidden = false;
    // Variables
    private UIBlock draggedBlock;
    private Point pos = new Point(0, 0);
    private final int maxBlocks = 20;
    //Windows
    private PaletteView paletteView;
    private ProgramAreaView programAreaView;
    private GameWorldView gameWorldView;

    /**
     * Initializes a CanvasWindow object.
     *
     * @param title Window title
     * @param gameWorldType GameWorldType of the API
     */
    protected MyCanvasWindow(String title, GameWorldType gameWorldType) {
        super(title);
        //Facade
        blockrGameFacade = new BlockrGameFacade(new SharedData(maxBlocks, gameWorldType));
        //Sections
        paletteView = new PaletteView(new Point(0,0),this.width/4,this.height, blockrGameFacade);
        programAreaView = new ProgramAreaView(new Point(paletteView.getWidth(),0), paletteView.getWidth() * 2, paletteView.getHeight());
        gameWorldView = new GameWorldView(new Point(paletteView.getWidth() + programAreaView.getWidth(),0), paletteView.getWidth(), paletteView.getHeight());
    }

    /**
     * Paints the CanvasWindow.
     *
     * @param g This object offers the methods that allow you to paint on the canvas.
     */
    @Override
    protected void paint(Graphics g) {
        adaptSectionSizes();

        paintSections(g);

        if (draggedBlock != null) {
            paintDraggedBlock(g);
        }

        paintNumberOfBlocksAvailable(g);
    }

    /**
     * Sets the sizes and positions of all the sections relative to the current window size.
     */
    private void adaptSectionSizes(){
        paletteView.setHeight(getHeight());
        programAreaView.setHeight(getHeight());
        gameWorldView.setHeight(getHeight());

        paletteView.setWidth(getWidth()/4);
        programAreaView.setWidth(getWidth()/2);
        gameWorldView.setWidth(getWidth()/4);

        paletteView.setPosition(new Point(0,0));
        programAreaView.setPosition(new Point(paletteView.getWidth(),0));
        gameWorldView.setPosition(new Point(paletteView.getWidth() + programAreaView.getWidth(),0));
    }

    /**
     * Calls draw on all the sections.
     *
     * @param g Graphics Object
     */
    private void paintSections(Graphics g){
        paletteView.draw(g);
        programAreaView.draw(g);
        gameWorldView.draw(g);
    }

    /**
     * Paints a Block that is being dragged.
     *
     * @param g Graphics Object
     */
    private void paintDraggedBlock(Graphics g){
        draggedBlock.draw(g);
    }

    /**
     * Paints the text informing the player how many blocks are still available to be placed.
     *
     * @param g Graphics Object
     */
    private void paintNumberOfBlocksAvailable(Graphics g){
        g.setColor(Color.BLACK);
        g.drawString("# blocks available: " + (maxBlocks - blockrGameFacade.getNumBlocksInPA()), getWidth() - 140, getHeight() - 10);
    }

    /**
     * Calls the respective handlers for each supported mouse input.
     *
     * @param id         The MouseEvent id.
     * @param x          The x position of the mouse action.
     * @param y          The y position of the mouse action.
     * @param clickCount The number of clicks associated with this event.
     */
    @Override
    protected void handleMouseEvent(int id, int x, int y, int clickCount) {
        super.handleMouseEvent(id, x, y, clickCount);
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
        adjustAllBlocks();
    }

    /**
     * Calls the respective handlers for when the mouse is pressed.
     *
     * @param x The x position of the mouse.
     * @param y The y position of the mouse.
     */
    private void pressMouse(int x, int y) {
        draggedBlock = getUIBlock(x, y);
        if (programAreaView.isWithin(x, y) && draggedBlock != null) {
            blockrGameFacade.handleProgramAreaForClickOn(draggedBlock);
        }
    }

    /**
     * Calls the respective handlers for when the mouse is dragged.
     *
     * @param x The x position of the mouse.
     * @param y The y position of the mouse.
     */
    private void dragMouse(int x, int y) {
        if (isBlockDragged()) {
            pos.x = x;
            pos.y = y;
            if (blockrGameFacade.getCorrespondingBlockFor(draggedBlock) != null) {
                draggedBlock.setPosition((Point) pos.clone());
                blockrGameFacade.getCorrespondingBlockFor(draggedBlock).setPosition((Point) pos.clone());
            }
            else {
                draggedBlock.setPosition((Point) pos.clone());
            }
            repaint();
        }
    }

    /**
     * Calls the respective handlers for when the mouse is released.
     *
     * @param x The x position of the mouse.
     * @param y The y position of the mouse.
     */
    private void releaseMouse(int x, int y) {
        if (isBlockDragged()) {
            if (programAreaView.isWithin(x, y)) {
                blockrGameFacade.handleReleaseInPAAt(draggedBlock, x, y);
            } else {
                blockrGameFacade.handleReleaseOutsidePA(draggedBlock);
            }
            draggedBlock = null;
            repaint();
        }
    }


    /**
     * Returns the UIBlock at the given click coordinates
     *
     * @param x Given x of click
     * @param y Given y of click
     * @return The UIBlock at the given coordinates or null
     */
    private UIBlock getUIBlock(int x, int y) {
        if (paletteView.isWithin(x, y) && !isPaletteHidden) {
            BlockTypes type = paletteView.getTypeOfClick(x, y);
            return type.getNewUIBlock(x, y);
        } else if (programAreaView.isWithin(x, y)) {
            return blockrGameFacade.getCorrespondingUiBlockFor(blockrGameFacade.getBlockInPaAt(x, y));
        }
        return null;
    }

    /**
     * Calls the respective handlers for each supported key input.
     *
     * @param id      The KeyEvent id (Pressed or typed).
     * @param keyCode The numerical value of the key.
     * @param keyChar The char value of the key.
     */
    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar) {
        super.handleKeyEvent(id, keyCode, keyChar);
        boolean bRepaint = false;
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
                    bRepaint = ctrlZ();
            }
            if (keyCode == 17) isHoldingCtrl = true;
            if (keyCode == 16) isHoldingShift = true;

            if (bRepaint) repaint();
        }
        adjustAllBlocks();
    }

    /**
     * Checks if the combination 'CTRL-Z' is pressed when 'Z' is pressed and calls the respective handlers if needed.
     *
     * @return Boolean which indicates if the window needs to be repainted.
     */
    private boolean ctrlZ() {
        if (isHoldingCtrl) {
            if (isHoldingShift) redo();
            else undo();
            isHoldingShift = false;
            isHoldingCtrl = false;
            return true;
        }
        return false;
    }

    /**
     * Calls the respective handlers to adjust all block positions and statement gaps.
     */
    private void adjustAllBlocks(){
        blockrGameFacade.adjustAllBlockPositions();
        blockrGameFacade.adjustAllStatementBlockGaps();
    }

    /**
     * Calls the respective handler to execute the next block.
     */
    private void executeNext() {
        blockrGameFacade.executeNext();
    }

    /**
     * Calls the respective handler to undo the last change.
     */
    private void undo() {
        blockrGameFacade.undo();
    }

    /**
     * Calls the respective handler to redo the last undo.
     */
    private void redo() {
        blockrGameFacade.redo();
    }

    /**
     * Calls the respective handler to reset the program.
     */
    private void resetProgramExecution() {
        blockrGameFacade.reset();
    }

    /**
     * Checks if, at this moment, a block is being dragged.
     */
    private boolean isBlockDragged() {
        return draggedBlock != null;
    }
}
