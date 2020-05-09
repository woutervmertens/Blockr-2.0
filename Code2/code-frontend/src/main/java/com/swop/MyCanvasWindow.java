package com.swop;

import com.swop.handlers.BlockrGameFacade;
import com.swop.uiElements.BlockTypes;
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
        //Facade
        blockrGameFacade = new BlockrGameFacade(maxBlocks,gameWorldType);
        //Sections
        paletteSection = new PaletteSection(new Point(0,0),600/4,600, blockrGameFacade);
        programAreaSection = new ProgramAreaSection(new Point(paletteSection.getWidth(),0),paletteSection.getWidth() * 2,paletteSection.getHeight());
        gameWorldSection = new GameWorldSection(new Point(paletteSection.getWidth() + programAreaSection.getWidth(),0),paletteSection.getWidth(),paletteSection.getHeight());
    }

    @Override
    protected void paint(Graphics g) {
        isPaletteHidden = blockrGameFacade.isPaletteHidden();
        paletteSection.draw(g,isPaletteHidden);
        programAreaSection.draw(g,blockrGameFacade.getAllUIBlocksInPA());
        gameWorldSection.draw(g,blockrGameFacade.getGameWorld());

        if (draggedBlock != null) {
            g.setColor(draggedBlock.getColor());
            g.fillPolygon(draggedBlock.getPolygon());
            g.setColor(Color.BLACK);
            g.drawString(draggedBlock.getText(), draggedBlock.getTextPosition().x, draggedBlock.getTextPosition().y);
        }
        g.setColor(Color.BLACK);
        g.drawString("# blocks available: " + (maxBlocks - blockrGameFacade.getNumBlocksInPA()), width - 140, height - 10);
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
                    blockrGameFacade.handleProgramAreaForClickOn(draggedBlock);
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
                        blockrGameFacade.handleReleaseInPA(draggedBlock);
                    } else {
                        blockrGameFacade.handleReleaseOutsidePA(draggedBlock);
                    }
                    draggedBlock = null;
                    repaint();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected mouse event: " + id);
        }
        blockrGameFacade.adjustAllBlockPositions();
        blockrGameFacade.adjustAllStatementBlockGaps();
        blockrGameFacade.reset();
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

        blockrGameFacade.adjustAllBlockPositions();
        blockrGameFacade.adjustAllStatementBlockGaps();
        // TODO: executeProgramHandler.getCorrespondingUiBlockFor(blockrGame.getCurrentActiveBlock()).setHighlightStateOn(true);
    }

    private void executeNext() {
        blockrGameFacade.executeNext();
    }

    private void undo() {
        blockrGameFacade.undo();
    }

    private void redo() {
        blockrGameFacade.redo();
    }

    private void resetProgramExecution() {
        blockrGameFacade.reset();
    }

    /**
     * Checks whether at this moment a block is being dragged.
     */
    private boolean isBlockDragged() {
        return draggedBlock != null;
    }
}
