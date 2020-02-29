import java.awt.*;

public class MyCanvasWindow extends CanvasWindow {

        /**
         * Initializes a CanvasWindow object.
         *
         * @param title Window title
         */
        protected MyCanvasWindow(String title) {
            super(title);
        }
        int x = 0;
        int add = 1;

    @Override
    protected void paint(Graphics g) {
        g.setColor(Color.GRAY);
        g.fillRect(x,0,60,60);
        x += add;
        if(x > 540 || x < 1) add *= -1;
        repaint();
    }

    @Override
    protected void handleMouseEvent(int id, int x, int y, int clickCount) {
        super.handleMouseEvent(id, x, y, clickCount);
    }

    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar) {
        super.handleKeyEvent(id, keyCode, keyChar);
    }
}
