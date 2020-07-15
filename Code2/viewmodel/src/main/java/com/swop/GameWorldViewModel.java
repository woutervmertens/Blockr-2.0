package com.swop;

import java.awt.*;

public class GameWorldViewModel extends WindowSection {
    GameWorldModel model;

    public GameWorldViewModel(Point pos, int width, int height) {
        super(pos, width, height);
    }

    public void paint(Graphics g){
        model.gameWorld.paint(g, position);
    }
}
