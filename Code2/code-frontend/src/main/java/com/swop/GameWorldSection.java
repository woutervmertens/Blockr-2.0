package com.swop;

import com.swop.uiElements.UIBlock;

import java.awt.*;
import java.util.Collection;

public class GameWorldSection extends WindowSection{
    public GameWorldSection(Point pos, int width, int height) {
        super(pos, width, height);
    }

    void draw(Graphics g, GameWorld gameWorld) {
        gameWorld.paint(g, position);
    }
}
