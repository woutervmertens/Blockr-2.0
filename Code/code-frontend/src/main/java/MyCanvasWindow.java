import java.awt.*;
import java.awt.event.MouseEvent;

public class MyCanvasWindow extends CanvasWindow {

    Point pos = new Point(0, 0);
    Palette palette = new Palette(super.width/3,super.height,30);
    ProgramArea programArea = new ProgramArea();
    //Handlers
    ClickHandler clickHandler = new ClickHandler(palette);

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
        palette.draw(g,new Point(0,0));
        programArea.draw(g,new Point(200,0));

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
     * @param id The KeyEvent id.
     * @param keyCode
     * @param keyChar
     */
    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar) {
        super.handleKeyEvent(id, keyCode, keyChar);
    }
}
