package com.swop.scrollbar;

import java.awt.*;

public class ScrollbarHandleModel {
    private Point position;
    private float scroll_position;
    private int height;
    private int width;
    private Color color;

    public ScrollbarHandleModel(Point position, int height, int width) {
        this.position = position;
        this.scroll_position = 0.5f;
        this.height = height;
        this.width = width;
        this.color = Color.gray;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public float getScroll_position() {
        return scroll_position;
    }

    public void setScroll_position(float scroll_position) {
        this.scroll_position = scroll_position;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public Color getColor() {
        return color;
    }

    public Polygon getPolygon(){
        Polygon pol = new Polygon();
        pol.addPoint(position.x, (int) (position.y + height*scroll_position - height/2));
        pol.addPoint(position.x + width, (int) (position.y + height*scroll_position - height/2));
        pol.addPoint(position.x + width, (int) (position.y + height*scroll_position + height/2));
        pol.addPoint(position.x, (int) (position.y + height*scroll_position + height/2));
        return pol;
    }

    /**
     * Check whether the pos represented by the given x and y is within this block.
     * @param x The given x
     * @param y The given y
     * @return Boolean
     */
    public boolean isWithin(int x, int y) {
        return (x > position.x
                && x < position.x + getWidth()
                && y > position.y + height*scroll_position - height/2
                && y < position.y + height*scroll_position + height/2);
    }
}
