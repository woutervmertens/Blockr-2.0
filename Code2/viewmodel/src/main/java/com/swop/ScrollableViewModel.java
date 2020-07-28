package com.swop;

import java.awt.*;

public abstract class ScrollableViewModel extends ViewModel{
    protected ScrollBarViewModel scrollBarViewModel;
    public ScrollableViewModel(Point pos, int width, int height) {
        super(pos, width, height);
    }

    public void addScrollBar(ScrollBarViewModel viewModel) {
        this.scrollBarViewModel = viewModel;
    }
}
