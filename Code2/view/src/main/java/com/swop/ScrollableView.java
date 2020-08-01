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
        pos.x = pos.x + viewModel.getWidth() - 10;
        scrollBar = new ScrollBar(pos,viewModel.getHeight(),10);
        ((ScrollableViewModel) super.viewModel).addScrollBar((ScrollBarViewModel) scrollBar.viewModel);
    }

    @Override
    public void changeProperties(Point position, int height, int width) {
        super.changeProperties(position, height, width);
        Point pos = (Point) position.clone();
        pos.x = pos.x + width - scrollBar.viewModel.getWidth();
        scrollBar.changeProperties(pos,height,scrollBar.viewModel.getWidth());
    }

    @Override
    public void draw(Graphics g) {
        newView.draw(g);
        scrollBar.draw(g);
    }
}
