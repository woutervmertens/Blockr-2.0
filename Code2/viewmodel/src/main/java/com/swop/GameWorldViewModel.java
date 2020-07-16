package com.swop;

import java.awt.*;

public class GameWorldViewModel extends ViewModel {
    GameWorldModel model;

    public GameWorldViewModel(Point pos, int width, int height) {
        super(pos, width, height);
    }

    @Override
    public void HandleClick(int x, int y) {
        if(!isWithin(x,y)) return;
        //TODO
    }

    public void paint(Graphics g){
        model.gameWorld.paint(g, position);
    }
}
