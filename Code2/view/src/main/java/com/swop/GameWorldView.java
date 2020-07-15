package com.swop;

import java.awt.*;

public class GameWorldView {
    GameWorldViewModel viewModel;
    public GameWorldView(Point pos, int width, int height) {

        viewModel = new GameWorldViewModel(pos, width, height);

    }

    void draw(Graphics g) {
        viewModel.paint(g);
    }
}
