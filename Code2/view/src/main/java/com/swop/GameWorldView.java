package com.swop;

import java.awt.*;

/**
 * The ui for the GameWorld Window.
 */
public class GameWorldView extends View{
    public GameWorldView(Point pos, int width, int height) {
        viewModel = new GameWorldViewModel(pos, width, height);
    }

    /**
     * Draws the GameWorld.
     * @param g Graphics object
     */
    public void draw(Graphics g) {
        ((GameWorldViewModel)viewModel).paint(g);
    }
}
