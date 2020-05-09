package com.swop;

import com.swop.blocks.Block;
import com.swop.handlers.DisplaceBlockHandler;
import com.swop.handlers.ExecuteProgramHandler;
import com.swop.uiElements.BlockTypes;
import com.swop.uiElements.UIBlock;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;

public class MyCanvasWindow extends CanvasWindow {
    // Handlers
    BlockrGame blockrGame;
    DisplaceBlockHandler displaceBlockHandler;
    ExecuteProgramHandler executeProgramHandler;
    //KeyHolds
    boolean isHoldingCtrl = false;
    boolean isHoldingShift = false;
    private boolean isPaletteHidden = false;
    // Variables
    private UIBlock draggedBlock;
    private Point pos = new Point(0, 0);
    private final int maxBlocks = 10;
    //Windows
    private PaletteSection paletteSection;
    private ProgramAreaSection programAreaSection;
    private GameWorldSection gameWorldSection;

    /**
     * Initializes a CanvasWindow object.
     *
     * @param title Window title
     */
    protected MyCanvasWindow(String title, GameWorldType gameWorldType) {
        super(title);
        blockrGame = new BlockrGame(maxBlocks, gameWorldType);
        // Make blockUIMap and share the REFERENCE to all handlers who need it
        Map<Block, UIBlock> blockUIBlockMap = new HashMap<>();
        displaceBlockHandler = new DisplaceBlockHandler(blockrGame, blockUIBlockMap);
        executeProgramHandler = new ExecuteProgramHandler(blockrGame, blockUIBlockMap);
        paletteSection = new PaletteSection(new Point(0,0),600/4,600, blockrGame);
        programAreaSection = new ProgramAreaSection(new Point(paletteSection.getWidth(),0),paletteSection.getWidth() * 2,paletteSection.getHeight());
        gameWorldSection = new GameWorldSection(new Point(paletteSection.getWidth() + programAreaSection.getWidth(),0),paletteSection.getWidth(),paletteSection.getHeight());
    }

    @Override
    protected void paint(Graphics g) {
        isPaletteHidden = blockrGame.isPaletteHidden();
        paletteSection.draw(g,isPaletteHidden);
        programAreaSection.draw(g,displaceBlockHandler.getAllUIBlocksInPA());
        gameWorldSection.draw(g,blockrGame.getGameWorld());

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
                draggedBlock = getUIBlock(x, y);
                if (programAreaSection.isWithin(x, y) && draggedBlock != null) {
                    displaceBlockHandler.handleProgramAreaForClickOn(draggedBlock);
                }
                break;
            case MouseEvent.MOUSE_CLICKED:
                break;
            case MouseEvent.MOUSE_DRAGGED:
                if (isBlockDragged()) {
                    pos.x = x;
                    pos.y = y;
                    draggedBlock.setPosition((Point) pos.clone());
                    repaint();
                }
                break;
            case MouseEvent.MOUSE_RELEASED:
                if (isBlockDragged()) {
                    if (programAreaSection.isWithin(x, y)) {
                        displaceBlockHandler.handleReleaseInPA(draggedBlock);
                    } else {
                        displaceBlockHandler.handleReleaseOutsidePA(draggedBlock);
                    }
                    draggedBlock = null;
                    repaint();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected mouse event: " + id);
        }
        displaceBlockHandler.adjustAllBlockPositions();
        displaceBlockHandler.adjustAllStatementBlockGaps();
        executeProgramHandler.reset();
        // TODO: executeProgramHandler.getCorrespondingUiBlockFor(blockrGame.getCurrentActiveBlock()).setHighlightStateOn(true);
    }


    /**
     * Returns the UIBlock at the given click coordinates
     *
     * @param x Given x of click
     * @param y Given y of click
     * @return The UIBlock at the given coordinates or null
     */
    private UIBlock getUIBlock(int x, int y) {
        if (paletteSection.isWithin(x, y) && !isPaletteHidden) {
            BlockTypes type = paletteSection.getTypeOfClick(x, y);
            return type.getNewUIBlock(x, y);
        } else if (programAreaSection.isWithin(x, y)) {
            return displaceBlockHandler.getCorrespondingUiBlockFor(blockrGame.getBlockInPaAt(x, y));
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
                    if (isHoldingCtrl) {
                        if (isHoldingShift) redo();
                        else undo();
                        isHoldingShift = false;
                        isHoldingCtrl = false;
                        bRepaint = true;
                    }
            }

            if (keyCode == 17) isHoldingCtrl = true;
            if (keyCode == 16) isHoldingShift = true;

            System.out.println("Ctrl " + isHoldingCtrl);
            System.out.println("Shift " + isHoldingShift);

            if (bRepaint) repaint();
        }

        displaceBlockHandler.adjustAllBlockPositions();
        displaceBlockHandler.adjustAllStatementBlockGaps();
        // TODO: executeProgramHandler.getCorrespondingUiBlockFor(blockrGame.getCurrentActiveBlock()).setHighlightStateOn(true);
    }

    private void executeNext() {
        executeProgramHandler.executeNext();
    }

    private void undo() {
        executeProgramHandler.undo();
    }

    private void redo() {
        executeProgramHandler.redo();
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
