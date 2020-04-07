package com.swop;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;

public class MyCanvasWindow extends CanvasWindow {
    GameWorldType gameWorldType;
    GameWorld gameWorld;
    ArrayList<Button> buttons;
    ClickHandler clickHandler;
    /**
     * Initializes a com.swop.CanvasWindow object.
     *
     * @param title Window title
     */
    protected MyCanvasWindow(String title, GameWorldType gameWorldType) {
        super(title);
        this.gameWorldType = gameWorldType;
        this.gameWorld = gameWorldType.createNewInstance();
        this.clickHandler = new ClickHandler(gameWorld);

        buttons = new ArrayList<Button>(Arrays.asList(Button.values()));
        buttons.get(0).setLocation(0,super.height-30);
        buttons.get(1).setLocation(super.width/3,super.height-30);
        buttons.get(2).setLocation(2*super.width/3,super.height-30);
    }

    @Override
    protected void paint(Graphics g) {
        gameWorld.paint(g, null);
        for (Button button : buttons){
            button.draw(g);
        }
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
                for (Button button : buttons){
                    if(button.isClicked(x,y)) clickHandler.HandleClick(button.getAction());
                }
                repaint();
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
    }
}
