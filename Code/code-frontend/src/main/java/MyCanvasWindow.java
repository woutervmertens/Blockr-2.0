import UIElements.UIBlock;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MyCanvasWindow extends CanvasWindow {

    private BlockrGame blockrGame = new BlockrGame();
    //Variables
    private UIBlock draggedBlock;
    private Point pos = new Point(0, 0);
    private int maxBlocks = 10;
    //Views
    private UIPalette uiPalette = new UIPalette(new Point(0, 0), super.width / 4, super.height, 30);
    private UIProgramArea uiProgramArea = new UIProgramArea(new Point(uiPalette.getWidth(), 0), super.width / 2, super.height);
    private UIGameWorld uiGameWorld = new UIGameWorld(new Point(uiPalette.getWidth() + uiProgramArea.getWidth(), 0), 30,blockrGame);
    //Handlers
    private LoadDataHandler loadDatahandler = new LoadDataHandler(blockrGame,uiGameWorld);
    private ClickHandler clickHandler = new ClickHandler(uiPalette, uiProgramArea);
    private KeyHandler keyHandler = new KeyHandler(uiGameWorld, uiProgramArea, blockrGame);
    private DisplaceBlockHandler displaceBlockHandler = new DisplaceBlockHandler(uiProgramArea, uiPalette, uiGameWorld, blockrGame);


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
        uiPalette.setHiddenStateAs((maxBlocks - uiProgramArea.getNumBlocks()) <= 0);
        uiPalette.draw(g);
        uiProgramArea.draw(g);
        uiGameWorld.draw(g);
        if (draggedBlock != null) {
            g.setColor(draggedBlock.getColor(false));
            g.fillPolygon(draggedBlock.getPolygon());
            g.setColor(Color.BLACK);
            g.drawString(draggedBlock.getText(), draggedBlock.getTextPosition().x, draggedBlock.getTextPosition().y);
        }
        g.setColor(Color.BLACK);
        g.drawString("# blocks available: " + (maxBlocks - uiProgramArea.getNumBlocks()), width - 140, height - 10);
    }

    /**
     * Checks whether at this moment a block is being dragged.
     */
    private boolean isBlockDragged() {
        return draggedBlock != null;
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
                keyHandler.reset();
                draggedBlock = clickHandler.getUIBlock(x, y);
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
                    displaceBlockHandler.handleRelease(x, y, draggedBlock);
                    draggedBlock = null;
                    repaint();
                }
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
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
        if (id == KeyEvent.KEY_PRESSED) {
            switch (keyCode) {
                case 116: //F5
                    keyHandler.stepThroughCode();
                    repaint();
                    break;
                case 27: //Escape
                    keyHandler.reset();
                    repaint();
                    break;
            }
        }
    }
}
