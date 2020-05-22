package com.swop.uiElements;

import java.awt.*;

public abstract class UIBlock {
    protected final String text;
    protected int width;
    protected int height;  // default 30 ?
    protected int step;  // steps in the plugs and sockets
    protected Point position;
    protected Color color, highlightColor;
    private BlockTypes type;
    protected boolean isHighlight;

    public UIBlock(BlockTypes.stdBlockData data){
        this.width = data.getWidth();
        this.height = data.getHeight();
        this.position = data.getPosition();
        this.text = data.getText();
        this.type = data.getBlockTypes();
        this.color = data.getColor();
        this.highlightColor = data.getHighlightColor();
        step = height / 6;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getTextPosition() {
        return new Point(position.x + 10, position.y + 20);
    }

    public String getText() {
        return text;
    }

    public Color getColor() {
        if (isHighlight()) return highlightColor;
        return color;
    }

    public BlockTypes getType() {
        return this.type;
    }

    /**
     * @return the polygon for this type of UIBlock
     */
    public abstract Polygon getPolygon();

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public boolean isHighlight() {
        return this.isHighlight;
    }

    public void setHighlightStateOn(boolean isHighlight) {
        this.isHighlight = isHighlight;
    }
}
