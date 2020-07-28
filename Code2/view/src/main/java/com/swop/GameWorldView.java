package com.swop;

import java.awt.*;

public class GameWorldView extends View{
    public GameWorldView(Point pos, int width, int height) {
        viewModel = new GameWorldViewModel(pos, width, height);
    }

    public void draw(Graphics g) {
        ((GameWorldViewModel)viewModel).paint(g);
    }
}
