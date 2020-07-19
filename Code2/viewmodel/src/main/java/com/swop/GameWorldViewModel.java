package com.swop;

import com.swop.blocks.Block;

import java.awt.*;

public class GameWorldViewModel extends ViewModel {
    GameWorldModel model;

    public GameWorldViewModel(Point pos, int width, int height) {
        super(pos, width, height);
        model = new GameWorldModel();
    }

    @Override
    public void HandleMousePress(int x, int y) {
        if(!isWithin(x,y)) return;
        //TODO: scrollbar
    }

    @Override
    public void HandleMouseRelease(int x, int y) {
        return;
        //TODO: scrollbar
    }

    @Override
    public void HandleMouseDrag(int x, int y) {
        return;
        //TODO: scrollbar
    }

    @Override
    public void HandleReset() {

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
    public void setGameWorld(GameWorld gw) {model.setGameWorld(gw);}
}
