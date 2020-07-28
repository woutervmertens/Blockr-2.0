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
        //Draw model
        g.setColor(((ScrollBarViewModel)viewModel).getBGColor());
        g.fillPolygon(((ScrollBarViewModel)viewModel).getBGPoly());
        if(((ScrollBarViewModel)viewModel).isActive()){
            g.setColor(((ScrollBarViewModel)viewModel).getHandleColor());
            g.fillPolygon(((ScrollBarViewModel)viewModel).getHandlePoly());
        }
    }
}
