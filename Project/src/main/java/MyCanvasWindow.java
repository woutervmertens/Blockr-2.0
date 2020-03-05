import java.awt.*;
import java.awt.event.MouseEvent;

public class MyCanvasWindow extends CanvasWindow {

    Point pos = new Point(0, 0);

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
        g.setColor(Color.GRAY);

        g.fillRect(pos.x, pos.y, 60, 60);

        g.fillRect(50,50,20,20);


    }

    @Override
    protected void handleMouseEvent(int id, int x, int y, int clickCount) {
        super.handleMouseEvent(id, x, y, clickCount);
        switch (id){
            case MouseEvent.MOUSE_PRESSED:
                break;
            case MouseEvent.MOUSE_CLICKED:
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
