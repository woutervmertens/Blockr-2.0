import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class MyCanvasWindow extends CanvasWindow {

    private Point pos = new Point(0, 0);
    private UIPalette UIPalette = new UIPalette(super.width/3,super.height,30);
    private UIProgramArea UIProgramArea = new UIProgramArea();
    private UIGameWorld UIGameWorld = new UIGameWorld();
    //Handlers
    private ClickHandler clickHandler = new ClickHandler(UIPalette);

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
        UIPalette.draw(g,new Point(0,0));
        UIProgramArea.draw(g,new Point(200,0));
        UIGameWorld.draw(g,new Point(400,0));
    }

    /**
     * Calls the respective handlers for each supported mouse input.
     * @param id The MouseEvent id.
     * @param x The x position of the mouse action.
     * @param y The y position of the mouse action.
     * @param clickCount The number of clicks associated with this event.
     */
    @Override
    protected void handleMouseEvent(int id, int x, int y, int clickCount) {
        super.handleMouseEvent(id, x, y, clickCount);
        switch (id){
            case MouseEvent.MOUSE_PRESSED:
                break;
            case MouseEvent.MOUSE_CLICKED:
                clickHandler.handleClick(x,y);
                break;
            case MouseEvent.MOUSE_DRAGGED:
                pos.x = x;
                pos.y = y;
                repaint();
                break;
            case MouseEvent.MOUSE_RELEASED:
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + id);
        }
    }

    /**
     * Calls the respective handlers for each supported key input.
     * @param id The KeyEvent id (Pressed or typed).
     * @param keyCode The numerical value of the key.
     * @param keyChar The char value of the key.
     */
    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar) {
        super.handleKeyEvent(id, keyCode, keyChar);
        if(id == KeyEvent.KEY_PRESSED) {
            switch (keyCode) {
                case 116: //F5
                    //TODO: handle step through code
                    break;
                case 27: //Escape
                    //TODO: handle reset
                    break;
            }
        }
    }
}
