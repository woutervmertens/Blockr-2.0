package com.swop;

import java.awt.*;

/**
 * The logic for a View with a ScrollBar.
 */
public abstract class ScrollableViewModel extends ViewModel{
    protected ScrollBarViewModel scrollBarViewModel;
    private int extraClipHeight = 0;
    public ScrollableViewModel(Point pos, int width, int height) {
        super(pos, width, height);
    }

    /**
     * Set a new scrollbar viewmodel.
     * @param viewModel the new scrollbar viewmodel
     */
    public void addScrollBar(ScrollBarViewModel viewModel) {
        this.scrollBarViewModel = viewModel;
    }

    /**
     * @return Where the clip of the scrollable area starts.
     */
    public int getClipStart()
    {
        int totalHeight = (extraClipHeight);
        float scrollPos = scrollBarViewModel.getNormalizedYPos();
        return (int) (totalHeight * scrollPos);
    }

    /**
     * @return the full height of the scrollable area (start + extra).
     */
    public int getFullHeight(){
        return getHeight() + extraClipHeight;
    }

    /**
     * Offset a position with where the clip starts.
     * @param oldPos the old position
     * @return an offset position
     */
    public int offsetScrollPosition(int oldPos){
        return oldPos + getClipStart();
    }

    /**
     * Increases the size of the scrollable area.
     */
    public void increaseSize(){
        int oldHeight = getFullHeight();
        extraClipHeight += getHeight();
        scrollBarViewModel.handleNewMaxHeight(oldHeight,getFullHeight());
        scrollBarViewModel.updateHandleHeight(getFullHeight());
    }

    /**
     * Is the Point in the scroll buffer (the lowest 100 pixels)
     * @param pos The Point to check.
     * @return the boolean answer to the check
     */
    public boolean isInScrollBuffer(Point pos){
        return (pos.y > getFullHeight() - 100);
    }

    @Override
    protected boolean isWithin(int x, int y) {
        return (x > getPosition().x
                && x < getPosition().x + getWidth()
                && y > getPosition().y
                && y < getPosition().y + getHeight());
    }
}
