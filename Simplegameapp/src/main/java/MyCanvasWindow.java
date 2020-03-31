import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MyCanvasWindow extends CanvasWindow {
    GameWorldType gameWorldType;
    GameWorld gameWorld;
    /**
     * Initializes a CanvasWindow object.
     *
     * @param title Window title
     */
    protected MyCanvasWindow(String title, GameWorldType gameWorldType) {
        super(title);
        this.gameWorldType = gameWorldType;
        this.gameWorld = gameWorldType.createNewInstance();
    }

    @Override
    protected void paint(Graphics g) {
        gameWorld.paint(g);
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
                break;
            case MouseEvent.MOUSE_CLICKED:
                break;
            case MouseEvent.MOUSE_DRAGGED:
                break;
            case MouseEvent.MOUSE_RELEASED:
                break;
            default:
                throw new IllegalStateException("Unexpected mouse event: " + id);
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

            }
        }
    }
}
