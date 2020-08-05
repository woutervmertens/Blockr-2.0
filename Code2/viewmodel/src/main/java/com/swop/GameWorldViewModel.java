package com.swop;

import java.awt.*;

public class GameWorldViewModel extends ScrollableViewModel {
    GameWorldModel model;

    public GameWorldViewModel(Point pos, int width, int height) {
        super(pos, width, height);
        model = new GameWorldModel();
    }

    /**
     * React to a MousePress on (x,y):
     *  Passes the call along to the scrollbar
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
    @Override
    public void HandleMousePress(int x, int y) {
        if(!isWithin(x,y)) return;
        scrollBarViewModel.HandleMousePress(x,y);
    }

    /**
     * React to a MouseRelease on (x,y):
     *  Passes the call along to the scrollbar
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
    @Override
    public void HandleMouseRelease(int x, int y) {
        scrollBarViewModel.HandleMouseRelease(x,y);
    }

    /**
     * React to a MouseDrag on (x,y):
     *  Passes the call along to the scrollbar
     * @param x the x position of the mouse
     * @param y the y position of the mouse
     */
    @Override
    public void HandleMouseDrag(int x, int y) {
        scrollBarViewModel.HandleMouseDrag(x,y);
    }

    /**
     * React to a Reset:
     *  creates a new instance of the GameWorld
     */
    @Override
    public void HandleReset() {
        model.gameWorld = model.gameWorldType.createNewInstance();
    }

    @Override
    public GameWorldModel getModel() {
        return model;
    }

    /**
     * Calls paint in the GameWorld
     * @param g the Graphics object
     */
    public void paint(Graphics g){
        model.gameWorld.paint(g, position);
    }

    public GameWorld getGameWorld() {
        return model.gameWorld;
    }
    public void setGameWorld(GameWorld gw, GameWorldType gwType) {model.setGameWorld(gw, gwType);}
}
