package com.swop;

import com.swop.handlers.DisplaceBlockHandler;
import com.swop.handlers.ExecuteProgramHandler;
import com.swop.uiElements.BlockTypes;
import com.swop.uiElements.UIBlock;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MyCanvasWindow extends CanvasWindow {
    private boolean isPaletteHidden = false;
    // Variables
    private UIBlock draggedBlock;
    private Point pos = new Point(0, 0);
    private int maxBlocks = 10;
    // Handlers
    BlockrGame blockrGame = new BlockrGame(maxBlocks,new int[]{5, 5}, new int[]{3, 4});
    DisplaceBlockHandler displaceBlockHandler = new DisplaceBlockHandler(blockrGame);
    ExecuteProgramHandler executeProgramHandler = new ExecuteProgramHandler(blockrGame);

    /**
     * Initializes a CanvasWindow object.
     *
     * @param title Window title
     */
    protected MyCanvasWindow(String title) {
        super(title);

    }

    @Override
    protected void paint(Graphics g) {
        isPaletteHidden = blockrGame.isPaletteHidden();
        Windows.drawWindows(g, isPaletteHidden, displaceBlockHandler.getAllUIBlocksInPA(), executeProgramHandler.getGameWorld(),executeProgramHandler.getCharacter());

        // TODO: check the rest
        if (draggedBlock != null) {
            g.setColor(draggedBlock.getColor());
            g.fillPolygon(draggedBlock.getPolygon());
            g.setColor(Color.BLACK);
            g.drawString(draggedBlock.getText(), draggedBlock.getTextPosition().x, draggedBlock.getTextPosition().y);
        }
        g.setColor(Color.BLACK);
        g.drawString("# blocks available: " + (maxBlocks - executeProgramHandler.getNumBlocksInPA()), width - 140, height - 10);
    }

    /**
     * Calls the respective com.com.swop.handlers for each supported mouse input.
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
                executeProgramHandler.reset();
                draggedBlock = getUIBlock(x, y);
                break;
            case MouseEvent.MOUSE_CLICKED:
                break;
            case MouseEvent.MOUSE_DRAGGED:
                if (isBlockDragged()) {
                    pos.x = x;
                    pos.y = y;
                    draggedBlock.setPosition(pos);
                    repaint();
                }
                break;
            case MouseEvent.MOUSE_RELEASED:
                if (isBlockDragged()) {
                    if (Windows.PROGRAM_AREA.isWithin(x, y)) { // TODO remove uiPA and make isWithinPA method
                        displaceBlockHandler.handleReleaseInPA(x, y, draggedBlock);
                    } else {
                        displaceBlockHandler.handleReleaseOutsidePA(x, y, draggedBlock);
                    }
                    draggedBlock = null;
                    repaint();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected mouse event: " + id);
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
        if (Windows.PALETTE.isWithin(x, y) && !isPaletteHidden) {
            BlockTypes type = Windows.getTypeOfClick(x, y);
            return type.getNewUIBlock(x, y);
        } else if (Windows.PROGRAM_AREA.isWithin(x, y)) {
            // TODO: check
            return displaceBlockHandler.getCorrespondingUiBlockFor(blockrGame.getBlockInPaAt(x, y));
        }
        return null;
    }

    /**
     * Calls the respective com.com.swop.handlers for each supported key input.
     *
     * @param id      The KeyEvent id (Pressed or typed).
     * @param keyCode The numerical value of the key.
     * @param keyChar The char value of the key.
     */
    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar) {
        super.handleKeyEvent(id, keyCode, keyChar);
        if (id == KeyEvent.KEY_PRESSED) {
            switch (keyCode) {
                case 116: //F5
                    executeNext();
                    repaint();
                    break;
                case 27: //Escape
                    resetProgramExecution();
                    repaint();
                    break;
            }
        }
    }

    private void executeNext() {
        executeProgramHandler.executeNext();
    }

    private void resetProgramExecution() {
        executeProgramHandler.reset();
    }

    /**
     * Checks whether at this moment a block is being dragged.
     */
    private boolean isBlockDragged() {
        return draggedBlock != null;
    }
}
