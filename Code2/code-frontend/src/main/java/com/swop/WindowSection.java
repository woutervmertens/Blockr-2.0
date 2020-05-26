package com.swop;

import com.swop.uiElements.UIBlock;

import java.awt.*;
import java.util.Collection;

public abstract class WindowSection {
    protected Point position;
    protected int width;
    protected int height;

    public WindowSection(Point pos, int width, int height){
        this.position = pos;
        this.width = width;
        this.height = height;
    }

    protected void drawBlock(UIBlock block, Graphics g) {
        g.setColor(block.getColor());
        g.fillPolygon(block.getPolygon());
        g.setColor(Color.BLACK);
        g.drawString(block.getText(), block.getTextPosition().x, block.getTextPosition().y);
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
