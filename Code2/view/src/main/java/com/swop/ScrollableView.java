package com.swop;

import com.swop.uiElements.ScrollBar;

import java.awt.*;

public class ScrollableView extends View{
    private View newView;
    private ScrollBar scrollBar;
    public ScrollableView(View newView){
        this.newView = newView;
        super.viewModel = newView.viewModel;
        instantiateScrollbar();
    }

    private void instantiateScrollbar(){
        Point pos = (Point) viewModel.getPosition().clone();
        pos.x = pos.x + viewModel.getWidth() - 25;
        scrollBar = new ScrollBar(pos,viewModel.getHeight(),25);
        ((ScrollableViewModel) super.viewModel).addScrollBar((ScrollBarViewModel) scrollBar.viewModel);
    }

    @Override
    public void changeProperties(Point position, int height, int width) {
        super.changeProperties(position, height, width);
        Point pos = (Point) viewModel.getPosition().clone();
        pos.x = pos.x + viewModel.getWidth() - 25;
        scrollBar.changeProperties(pos,viewModel.getHeight(),25);
    }

    @Override
    public void draw(Graphics g) {
        newView.draw(g);
        scrollBar.draw(g);
    }
}
