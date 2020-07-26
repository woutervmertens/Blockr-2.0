package com.swop;

import java.awt.*;

public class GameWorldViewModel extends ScrollableViewModel {
    GameWorldModel model;

    public GameWorldViewModel(Point pos, int width, int height) {
        super(pos, width, height);
        model = new GameWorldModel();
    }

    @Override
    public void HandleMousePress(int x, int y) {
        if(!isWithin(x,y)) return;
        scrollBarViewModel.HandleMousePress(x,y);
    }

    @Override
    public void HandleMouseRelease(int x, int y) {
        scrollBarViewModel.HandleMouseRelease(x,y);
    }

    @Override
    public void HandleMouseDrag(int x, int y) {
        scrollBarViewModel.HandleMouseDrag(x,y);
    }

    @Override
    public void HandleReset() {
        model.gameWorld = model.gameWorldType.createNewInstance();
    }

    @Override
    public GameWorldModel getModel() {
        return model;
    }

    public void paint(Graphics g){
        model.gameWorld.paint(g, position);
    }

    public GameWorld getGameWorld() {
        return model.gameWorld;
    }
    public void setGameWorld(GameWorld gw, GameWorldType gwType) {model.setGameWorld(gw, gwType);}
}
