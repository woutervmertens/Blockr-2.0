package com.swop.scrollbar;

import java.awt.*;

/**
 * The Background of the scrollbar.
 */
public class ScrollbarBGModel {
    private Point position;
    private int height;
    private int width;
    private Color color;

    public ScrollbarBGModel(Point position, int height, int width) {
        this.position = position;
        this.height = height;
        this.width = width;
        color = Color.LIGHT_GRAY;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
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

    /**
     * Creates and return the polygon for the View to display.
     * @return a Polygon object
     */
    public Polygon getPolygon(){
        Polygon pol = new Polygon();
        pol.addPoint(position.x, position.y);
        pol.addPoint(position.x + width, position.y);
        pol.addPoint(position.x + width, position.y + height);
        pol.addPoint(position.x, position.y + height);
        return pol;
    }
}
