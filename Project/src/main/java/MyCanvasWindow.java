import java.awt.*;
import java.awt.event.MouseEvent;

public class MyCanvasWindow extends CanvasWindow {

    Point pos = new Point(0, 0);
    Palette palette = new Palette(super.width/3,super.height,30);

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


    }

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

    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar) {
        super.handleKeyEvent(id, keyCode, keyChar);
    }
}
