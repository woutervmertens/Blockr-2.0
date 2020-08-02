package com.swop;

import java.awt.*;

public abstract class ScrollableViewModel extends ViewModel{
    protected ScrollBarViewModel scrollBarViewModel;
    private int extraClipHeight = 0;
    public ScrollableViewModel(Point pos, int width, int height) {
        super(pos, width, height);
    }

    public void addScrollBar(ScrollBarViewModel viewModel) {
        this.scrollBarViewModel = viewModel;
    }

    public int getClipStart()
    {
        return (int) ((getHeight() + extraClipHeight) * scrollBarViewModel.getNormalizedYPos());
    }

    public int getFullHeight(){
        return getHeight() + extraClipHeight;
    }

    public int offsetScrollPosition(int oldPos){
        return oldPos + getClipStart();
    }

    public void increaseSize(){
        extraClipHeight += getHeight();
        scrollBarViewModel.updateHandleHeight(getFullHeight());
    }

    public boolean isInScrollBuffer(Point pos){
        return (pos.y > getHeight() + extraClipHeight - 40);
    }
}
