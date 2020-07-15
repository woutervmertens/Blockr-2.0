package com.swop.blocks;

import java.awt.*;
import java.util.HashMap;

public abstract class BlockModel implements Cloneable {
    protected String text;
    protected final int step;
    protected final int width;
    protected final int height;

    protected Point position;
    private Point previousDropPosition;

    protected Color color, highlightColor;
    protected boolean isHighlight = false;

    protected HashMap<Connector,ConnectorType> Connectors;

    /**
     * Creates a block with the given position, width and height.
     */
    protected BlockModel(StdBlockData data) {
        this.setPosition(data.getPosition());
        this.width = data.getWidth();
        this.height = data.getHeight();
        this.text = data.getText();
        step = height / 6;
    }

    /**
     * @return Returns a clone of the given block.
     */
    public BlockModel clone() {
        try {
            return (BlockModel) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    protected Point pointSum(Point a, Point b){
        return new Point(a.x + b.x,a.y + b.y);
    }

    public int getStep() {
        return step;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }


    /**
     * @param x x-position.
     * @param y y-position.
     * @return Returns whether the given position is on this block.
     */
    public boolean isPositionOn(int x, int y) {
        return (x > position.x && x < position.x + width) && (y > position.y && y < position.y + height);
    }

    public Point getPosition() {
        return position;
    }

    public Point getTextPosition() {
        return new Point(position.x + 10, position.y + 20);
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        if (isHighlight) return highlightColor;
        return color;
    }

    /**
     * @return the polygon for this type of UIBlock
     */
    public abstract Polygon getPolygon();

    public void setPosition(Point position) {
        if (position == null) throw new IllegalArgumentException();
        this.position = position;
    }

    public Point getPreviousDropPosition() {
        return previousDropPosition;
    }

    public void setPreviousDropPosition(Point previousDropPosition) {
        this.previousDropPosition = previousDropPosition;
    }

    public int getCount(){return 1;}

    public boolean isHighlight() {
        return this.isHighlight;
    }

    public void setHighlightStateOn(boolean isHighlight) {
        this.isHighlight = isHighlight;
    }

    /**
     * Check whether the pos represented by the given x and y is within this window.
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
