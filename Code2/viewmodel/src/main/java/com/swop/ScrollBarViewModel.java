package com.swop;

import java.awt.*;

public class ScrollBarViewModel extends ViewModel{
    private boolean active;
    public ScrollBarViewModel(Point pos, int width, int height) {
        super(pos, width, height);
    }

    @Override
    public void HandleMousePress(int x, int y) {

    }

    @Override
    public void HandleMouseRelease(int x, int y) {

    }

    @Override
    public void HandleMouseDrag(int x, int y) {

    }

    @Override
    public void HandleReset() {

    }

    @Override
    public Object getModel() {
        return null;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
