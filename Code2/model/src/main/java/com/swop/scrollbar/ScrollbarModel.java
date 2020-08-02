package com.swop.scrollbar;

import java.awt.*;

public class ScrollbarModel {
    private Point position;
    private int height;
    private int width;
    private boolean isActive;
    private ScrollbarBGModel background;
    private ScrollbarHandleModel handle;

    public ScrollbarModel(Point position, int height, int width) {
        this.position = position;
        this.height = height;
        this.width = width;
        background = new ScrollbarBGModel(position,height,width);
        handle = new ScrollbarHandleModel(position,height,width);
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
        background.setPosition(position);
        handle.setPosition(position);
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
        background.setHeight(height);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
        background.setWidth(width);
        handle.setWidth(width);
    }

    public float getHandleYPosition(){
        return handle.getScroll_position();
    }

    public void setHandleYPosition(float y){
        if(y < 0) y = 0.0f;
        if(y > 1) y = 1.0f;
        handle.setScroll_position(y);
    }

    public void setHandleHeight(float percentage){
        handle.setHeight((int) (height * percentage));
    }

    public boolean isWithinHandle(int x, int y){
        return handle.isWithin(x,y);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Polygon getBackGroundPoly(){
        return background.getPolygon();
    }

    public Polygon getHandlePoly(){
        return handle.getPolygon();
    }

    public Color getBGColor(){
        return background.getColor();
    }

    public Color getHandleColor(){
        return handle.getColor();
    }


    /**
     * Check whether the pos represented by the given x and y is within this block.
     * @param x The given x
     * @param y The given y
     * @return Boolean
     */
    public boolean isWithin(int x, int y) {
        return (x > getPosition().x
                && x < getPosition().x + getWidth()
                && y > getPosition().y
                && y < getPosition().y + getHeight());
    }
}
