package com.swop;

import java.awt.*;

public abstract class ViewModel {
    protected Point position;
    protected int width;
    protected int height;

    public ViewModel(Point pos, int width, int height){
        this.position = pos;
        this.width = width;
        this.height = height;
    }

    public void setPosition(Point p) {
        this.position = p;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Point getPosition() {
        return position;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public abstract void HandleMousePress(int x, int y);
    public abstract void HandleMouseRelease(int x, int y);
    public abstract void HandleMouseDrag(int x, int y);
    public abstract void HandleReset();
    public abstract Object getModel();

    /**
     * Check whether the pos represented by the given x and y is within this window.
     * @param x The given x
     * @param y The given y
     * @return Boolean
     */
    protected boolean isWithin(int x, int y) {
        return (x > getPosition().x
                && x < getPosition().x + getWidth()
                && y > getPosition().y
                && y < getPosition().y + getHeight());
    }

}
