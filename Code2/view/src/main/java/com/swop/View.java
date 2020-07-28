package com.swop;

import java.awt.*;

public abstract class View {
    protected ViewModel viewModel;
    public void changeProperties(Point position, int height, int width){
        viewModel.setPosition(position);
        viewModel.setHeight(height);
        viewModel.setWidth(width);
    }
    public abstract void draw(Graphics g);

}
