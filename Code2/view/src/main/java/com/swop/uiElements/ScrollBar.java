package com.swop.uiElements;

import com.swop.ScrollBarViewModel;
import com.swop.View;

import java.awt.*;

public class ScrollBar extends View {
    public ScrollBar(Point position, int height, int width){
        viewModel = new ScrollBarViewModel(position, height, width);
    }
    @Override
    public void draw(Graphics g) {
        if(((ScrollBarViewModel)viewModel).isActive()){
            //Draw model
        }
        else
        {
            //grey out
        }
    }
    //TODO
}
