package com.swop;

import com.swop.uiElements.ScrollBar;

import java.awt.*;

/**
 * The ui for a Window with a scrollbar.
 */
public class ScrollableView extends View{
    private View newView;
    private ScrollBar scrollBar;
    public ScrollableView(View newView){
        this.newView = newView;
        super.viewModel = newView.viewModel;
        instantiateScrollbar();
    }

    /**
     * Creates a new Scrollbar
     */
    private void instantiateScrollbar(){
        Point pos = (Point) viewModel.getPosition().clone();
        pos.x = pos.x + viewModel.getWidth() - 10;
        scrollBar = new ScrollBar(pos,viewModel.getHeight(),10);
        ((ScrollableViewModel) super.viewModel).addScrollBar((ScrollBarViewModel) scrollBar.viewModel);
    }

    /**
     * Changes the properties of the window section and its scrollbar
     *
     * @param position the new Position Point
     * @param height the new height
     * @param width the new width
     */
    @Override
    public void changeProperties(Point position, int height, int width) {
        super.changeProperties(position, height, width);
        Point pos = (Point) position.clone();
        pos.x = pos.x + width - scrollBar.viewModel.getWidth();
        scrollBar.changeProperties(pos,height,scrollBar.viewModel.getWidth());
    }

    /**
     * Paints the available buttons
     *
     * @param g Graphics Objects
     */
    @Override
    public void draw(Graphics g) {
        g.setClip(viewModel.getPosition().x,viewModel.getPosition().y, viewModel.getWidth(), viewModel.getHeight());
        g.translate(0,-((ScrollableViewModel) viewModel).getClipStart());
        newView.draw(g);
        g.translate(0,((ScrollableViewModel) viewModel).getClipStart());
        scrollBar.draw(g);
    }
}
